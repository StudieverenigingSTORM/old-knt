/*     */ package vu.storm.touch.gfx;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.Point;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import javax.swing.JViewport;
/*     */ 
/*     */ public class TouchRow
/*     */   extends JPanel implements ComponentListener, ActionListener {
/*     */   ButtonProvider buttonProvider;
/*     */   Vector<JComponent> buttons;
/*     */   JComponent prevPageButton;
/*     */   JComponent nextPageButton;
/*     */   JPanel buttonPanel;
/*     */   JViewport viewPort;
/*     */   double buttonWidth;
/*     */   double rowWidth;
/*     */   int buttonsPerRow;
/*     */   int curPage;
/*     */   int numPages;
/*     */   int pageWidth;
/*     */   int numButtons;
/*     */   int numVisibleButtons;
/*  31 */   Object currentFilter = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public TouchRow(ButtonProvider buttonProvider, double buttonWidth, double rowWidth) {
/*  36 */     this.buttonProvider = buttonProvider;
/*  37 */     this.buttonWidth = buttonWidth;
/*  38 */     this.rowWidth = rowWidth;
/*  39 */     this.buttons = new Vector<JComponent>();
/*  40 */     this.buttonPanel = new JPanel();
/*  41 */     this.buttonPanel.setBackground(new Color(128, 154, 205));
/*  42 */     this.viewPort = new JViewport();
/*  43 */     setLayout((LayoutManager)null);
/*  44 */     this.nextPageButton = buttonProvider.createNextPageButton(this);
/*  45 */     this.prevPageButton = buttonProvider.createPrevPageButton(this);
/*  46 */     this.prevPageButton.setEnabled(false);
/*  47 */     readButtons();
/*  48 */     makeLayout();
/*  49 */     setBackground(new Color(128, 154, 205));
/*  50 */     this.viewPort.add(this.buttonPanel);
/*  51 */     this.viewPort.setLayout((LayoutManager)null);
/*  52 */     add(this.nextPageButton);
/*  53 */     add(this.viewPort);
/*  54 */     add(this.prevPageButton);
/*  55 */     this.buttonPanel.setLayout((LayoutManager)null);
/*  56 */     addComponentListener(this);
/*     */   }
/*     */   
/*     */   public void setBackground(Color bg) {
/*  60 */     super.setBackground(bg);
/*  61 */     if (this.buttonPanel != null) this.buttonPanel.setBackground(bg); 
/*  62 */     if (this.prevPageButton != null) this.prevPageButton.setBackground(bg); 
/*  63 */     if (this.nextPageButton != null) this.nextPageButton.setBackground(bg); 
/*  64 */     if (this.buttons != null)
/*     */     {
/*  66 */       for (int i = 0; i < this.buttons.size(); i++)
/*     */       {
/*  68 */         ((JComponent)this.buttons.elementAt(i)).setBackground(bg);
/*     */       }
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFilter(Object filter) {
/*  75 */     this.currentFilter = filter;
/*  76 */     for (int i = 0; i < this.buttons.size(); i++) {
/*     */       
/*  78 */       if (this.buttonProvider.matchesFilter(filter, this.buttons.elementAt(i))) {
/*     */         
/*  80 */         if (!((JComponent)this.buttons.elementAt(i)).isVisible())
/*     */         {
/*  82 */           ((JComponent)this.buttons.elementAt(i)).setVisible(true);
/*  83 */           this.numVisibleButtons++;
/*     */         
/*     */         }
/*     */       
/*     */       }
/*  88 */       else if (((JComponent)this.buttons.elementAt(i)).isVisible()) {
/*     */         
/*  90 */         ((JComponent)this.buttons.elementAt(i)).setVisible(false);
/*  91 */         this.numVisibleButtons--;
/*     */       } 
/*     */     } 
/*     */     
/*  95 */     rescale();
/*     */   }
/*     */ 
/*     */   
/*     */   public void rereadButtons() {
/* 100 */     resetLayout();
/* 101 */     this.numVisibleButtons = 0;
/* 102 */     while (this.buttons.size() > 0) {
/*     */       
/* 104 */       this.buttonPanel.remove(this.buttons.elementAt(0));
/* 105 */       this.buttonProvider.destroyButton(this.buttons.elementAt(0));
/* 106 */       this.buttons.remove(0);
/*     */     } 
/* 108 */     readButtons();
/* 109 */     makeLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   private void readButtons() {
/* 114 */     this.buttonsPerRow = (int)Math.floor(1.0D / this.buttonWidth);
/* 115 */     this.numButtons = this.buttonProvider.numButtons();
/* 116 */     this.numVisibleButtons = 0;
/* 117 */     for (int i = 0; i < this.numButtons; i++) {
/*     */       
/* 119 */       this.buttons.add(this.buttonProvider.createButton(i));
/* 120 */       ((JComponent)this.buttons.elementAt(this.buttons.size() - 1)).setBackground(getBackground());
/* 121 */       this.buttonPanel.add(this.buttons.elementAt(i));
/*     */     } 
/* 123 */     this.numVisibleButtons = this.numButtons;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetLayout() {}
/*     */ 
/*     */ 
/*     */   
/*     */   private void makeLayout() {
/* 133 */     if (this.numVisibleButtons > this.buttonsPerRow) {
/*     */       
/* 135 */       this.numPages = (this.numVisibleButtons + this.buttonsPerRow - 2) / (this.buttonsPerRow - 1);
/* 136 */       this.curPage = 0;
/* 137 */       this.prevPageButton.setVisible(true);
/* 138 */       this.nextPageButton.setVisible(true);
/* 139 */       this.prevPageButton.setEnabled(false);
/* 140 */       this.nextPageButton.setEnabled(true);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 145 */       this.numPages = 1;
/* 146 */       this.curPage = 0;
/* 147 */       this.prevPageButton.setVisible(false);
/* 148 */       this.nextPageButton.setVisible(false);
/*     */     } 
/* 150 */     rescale();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void rescale() {
/* 162 */     int w = getWidth();
/* 163 */     int h = getHeight();
/* 164 */     int bw = (int)Math.floor(this.buttonWidth * w);
/* 165 */     if (this.numVisibleButtons > this.buttonsPerRow) {
/*     */       
/* 167 */       this.prevPageButton.setBounds(w - bw, 0, bw / 2, h);
/* 168 */       this.nextPageButton.setBounds(w - bw + bw / 2, 0, bw / 2, h);
/* 169 */       this.pageWidth = (this.buttonsPerRow - 1) * bw;
/* 170 */       this.buttonPanel.setBounds(0, 0, this.numPages * this.pageWidth, h);
/* 171 */       this.viewPort.setBounds(0, 0, this.pageWidth, h);
/* 172 */       this.viewPort.setViewPosition(new Point(this.pageWidth * this.curPage, 0));
/*     */     }
/*     */     else {
/*     */       
/* 176 */       this.buttonPanel.setBounds(0, 0, w, h);
/* 177 */       this.viewPort.setBounds(0, 0, w, h);
/* 178 */       this.viewPort.setViewPosition(new Point(0, 0));
/* 179 */       this.pageWidth = this.buttonsPerRow * bw;
/*     */     } 
/* 181 */     for (int i = 0, j = 0; i < this.numButtons && j < this.numVisibleButtons; i++) {
/*     */       
/* 183 */       JComponent component = this.buttons.elementAt(i);
/* 184 */       if (component.isVisible()) {
/*     */         
/* 186 */         component.setBounds(j * bw, 0, bw, h);
/* 187 */         j++;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentHidden(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentMoved(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentResized(ComponentEvent e) {
/* 208 */     rescale();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 220 */     if (e.getActionCommand().equals("prev")) {
/*     */       
/* 222 */       if (this.curPage > 0)
/*     */       {
/* 224 */         this.curPage--;
/* 225 */         this.viewPort.setViewPosition(new Point(this.pageWidth * this.curPage, 0));
/* 226 */         if (this.curPage == 0)
/*     */         {
/* 228 */           this.prevPageButton.setEnabled(false);
/*     */         }
/* 230 */         if (this.curPage == this.numPages - 2)
/*     */         {
/* 232 */           this.nextPageButton.setEnabled(true);
/*     */         }
/*     */       }
/*     */     
/* 236 */     } else if (e.getActionCommand().equals("next")) {
/*     */       
/* 238 */       if (this.curPage < this.numPages - 1) {
/*     */         
/* 240 */         this.curPage++;
/* 241 */         this.viewPort.setViewPosition(new Point(this.pageWidth * this.curPage, 0));
/* 242 */         if (this.curPage == this.numPages - 1)
/*     */         {
/* 244 */           this.nextPageButton.setEnabled(false);
/*     */         }
/* 246 */         if (this.curPage == 1)
/*     */         {
/* 248 */           this.prevPageButton.setEnabled(true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/TouchRow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */