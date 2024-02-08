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
/*     */ public class TouchColumn
/*     */   extends JPanel implements ComponentListener, ActionListener {
/*     */   ButtonProvider buttonProvider;
/*     */   Vector<JComponent> buttons;
/*     */   JComponent prevPageButton;
/*     */   JComponent nextPageButton;
/*     */   JPanel buttonPanel;
/*     */   JViewport viewPort;
/*     */   double buttonHeight;
/*     */   double columnHeight;
/*     */   int buttonsPerColumn;
/*     */   int curPage;
/*     */   int numPages;
/*     */   int pageHeight;
/*     */   int numButtons;
/*     */   int numVisibleButtons;
/*  31 */   Object currentFilter = null;
/*     */ 
/*     */ 
/*     */   
/*     */   public TouchColumn(ButtonProvider buttonProvider, double buttonHeight, double columnHeight) {
/*  36 */     this.buttonProvider = buttonProvider;
/*  37 */     this.buttonHeight = buttonHeight;
/*  38 */     this.columnHeight = columnHeight;
/*  39 */     this.buttons = new Vector<JComponent>();
/*  40 */     this.buttonPanel = new JPanel();
/*  41 */     this.viewPort = new JViewport();
/*  42 */     setLayout((LayoutManager)null);
/*  43 */     this.nextPageButton = buttonProvider.createNextPageButton(this);
/*  44 */     this.prevPageButton = buttonProvider.createPrevPageButton(this);
/*  45 */     this.prevPageButton.setEnabled(false);
/*  46 */     readButtons();
/*  47 */     rereadFilter();
/*  48 */     makeLayout();
/*  49 */     this.buttonPanel.setBackground(new Color(128, 154, 205));
/*  50 */     setBackground(new Color(128, 154, 205));
/*  51 */     this.viewPort.add(this.buttonPanel);
/*  52 */     this.viewPort.setLayout((LayoutManager)null);
/*  53 */     add(this.nextPageButton);
/*  54 */     add(this.viewPort);
/*  55 */     add(this.prevPageButton);
/*  56 */     this.buttonPanel.setLayout((LayoutManager)null);
/*  57 */     addComponentListener(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public void rereadFilter() {
/*  62 */     for (int i = 0; i < this.buttons.size(); i++) {
/*     */       
/*  64 */       if (this.buttonProvider.matchesFilter(this.currentFilter, this.buttons.elementAt(i))) {
/*     */         
/*  66 */         if (!((JComponent)this.buttons.elementAt(i)).isVisible())
/*     */         {
/*  68 */           ((JComponent)this.buttons.elementAt(i)).setVisible(true);
/*  69 */           this.numVisibleButtons++;
/*     */         
/*     */         }
/*     */       
/*     */       }
/*  74 */       else if (((JComponent)this.buttons.elementAt(i)).isVisible()) {
/*     */         
/*  76 */         ((JComponent)this.buttons.elementAt(i)).setVisible(false);
/*  77 */         this.numVisibleButtons--;
/*     */       } 
/*     */     } 
/*     */     
/*  81 */     makeLayout();
/*     */   }
/*     */   
/*     */   public void setFilter(Object filter) {
/*  85 */     this.currentFilter = filter;
/*  86 */     rereadFilter();
/*     */   }
/*     */ 
/*     */   
/*     */   public void rereadButtons() {
/*  91 */     resetLayout();
/*  92 */     this.numVisibleButtons = 0;
/*  93 */     while (this.buttons.size() > 0) {
/*     */       
/*  95 */       this.buttonPanel.remove(this.buttons.elementAt(0));
/*  96 */       this.buttonProvider.destroyButton(this.buttons.elementAt(0));
/*  97 */       this.buttons.remove(0);
/*     */     } 
/*  99 */     readButtons();
/* 100 */     rereadFilter();
/* 101 */     makeLayout();
/*     */   }
/*     */ 
/*     */   
/*     */   private void readButtons() {
/* 106 */     this.buttonsPerColumn = (int)Math.floor(1.0D / this.buttonHeight);
/* 107 */     this.numButtons = this.buttonProvider.numButtons();
/* 108 */     this.numVisibleButtons = 0;
/* 109 */     for (int i = 0; i < this.numButtons; i++) {
/*     */       
/* 111 */       this.buttons.add(this.buttonProvider.createButton(i));
/* 112 */       this.buttonPanel.add(this.buttons.elementAt(i));
/*     */     } 
/* 114 */     this.numVisibleButtons = this.numButtons;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private void resetLayout() {}
/*     */ 
/*     */ 
/*     */   
/*     */   private void makeLayout() {
/* 124 */     if (this.numVisibleButtons > this.buttonsPerColumn) {
/*     */       
/* 126 */       this.numPages = (this.numVisibleButtons + this.buttonsPerColumn - 2) / (this.buttonsPerColumn - 1);
/* 127 */       this.curPage = 0;
/* 128 */       this.prevPageButton.setVisible(true);
/* 129 */       this.nextPageButton.setVisible(true);
/* 130 */       this.prevPageButton.setEnabled(false);
/* 131 */       this.nextPageButton.setEnabled(true);
/*     */     
/*     */     }
/*     */     else {
/*     */       
/* 136 */       this.numPages = 1;
/* 137 */       this.curPage = 0;
/* 138 */       this.prevPageButton.setVisible(false);
/* 139 */       this.nextPageButton.setVisible(false);
/*     */     } 
/* 141 */     rescale();
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
/* 153 */     int w = getWidth();
/* 154 */     int h = getHeight();
/* 155 */     int bh = (int)Math.floor(this.buttonHeight * h);
/* 156 */     if (this.numVisibleButtons > this.buttonsPerColumn) {
/*     */       
/* 158 */       this.prevPageButton.setBounds(0, h - bh, w / 2, bh);
/* 159 */       this.nextPageButton.setBounds(w / 2, h - bh, w / 2, bh);
/* 160 */       this.pageHeight = (this.buttonsPerColumn - 1) * bh;
/* 161 */       this.buttonPanel.setBounds(0, 0, w, this.numPages * this.pageHeight);
/* 162 */       this.viewPort.setBounds(0, 0, w, this.pageHeight);
/* 163 */       this.viewPort.setViewPosition(new Point(0, this.pageHeight * this.curPage));
/*     */     }
/*     */     else {
/*     */       
/* 167 */       this.buttonPanel.setBounds(0, 0, w, h);
/* 168 */       this.viewPort.setBounds(0, 0, w, h);
/* 169 */       this.viewPort.setViewPosition(new Point(0, 0));
/* 170 */       this.pageHeight = this.buttonsPerColumn * bh;
/*     */     } 
/* 172 */     for (int i = 0, j = 0; i < this.numButtons && j < this.numVisibleButtons; i++) {
/*     */       
/* 174 */       JComponent component = this.buttons.elementAt(i);
/* 175 */       if (component.isVisible()) {
/*     */         
/* 177 */         component.setBounds(0, j * bh, w, bh);
/* 178 */         j++;
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
/* 199 */     rescale();
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
/* 211 */     if (e.getActionCommand().equals("prev")) {
/*     */       
/* 213 */       if (this.curPage > 0)
/*     */       {
/* 215 */         this.curPage--;
/* 216 */         this.viewPort.setViewPosition(new Point(0, this.pageHeight * this.curPage));
/* 217 */         if (this.curPage == 0)
/*     */         {
/* 219 */           this.prevPageButton.setEnabled(false);
/*     */         }
/* 221 */         if (this.curPage == this.numPages - 2)
/*     */         {
/* 223 */           this.nextPageButton.setEnabled(true);
/*     */         }
/*     */       }
/*     */     
/* 227 */     } else if (e.getActionCommand().equals("next")) {
/*     */       
/* 229 */       if (this.curPage < this.numPages - 1) {
/*     */         
/* 231 */         this.curPage++;
/* 232 */         this.viewPort.setViewPosition(new Point(0, this.pageHeight * this.curPage));
/* 233 */         if (this.curPage == this.numPages - 1)
/*     */         {
/* 235 */           this.nextPageButton.setEnabled(false);
/*     */         }
/* 237 */         if (this.curPage == 1)
/*     */         {
/* 239 */           this.prevPageButton.setEnabled(true);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/TouchColumn.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */