/*     */ package vu.storm.touch.product;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import vu.storm.touch.GUIPanel;
/*     */ import vu.storm.touch.gfx.ButtonProvider;
/*     */ import vu.storm.touch.gfx.PageButton;
/*     */ import vu.storm.touch.gfx.TouchColumn;
/*     */ 
/*     */ 
/*     */ public class OrderPane
/*     */   extends JPanel
/*     */   implements ButtonProvider, ComponentListener
/*     */ {
/*     */   ProductScreen parent;
/*     */   TouchColumn touchColumn;
/*     */   PageButton upButton;
/*     */   PageButton downButton;
/*     */   OrderPostponeButton orderPostponeButton;
/*     */   PlaceOrderButton placeOrderButton;
/*     */   PlaceBorrelOrderButton placeBorrelOrderButton;
/*     */   OrderUserButton orderUserButton;
/*     */   boolean inBorrelMode = false;
/*     */   
/*     */   public OrderPane(ProductScreen parent) {
/*  32 */     this.parent = parent;
/*  33 */     this.upButton = new PageButton(true, true, true);
/*  34 */     this.downButton = new PageButton(true, false, true);
/*  35 */     this.placeOrderButton = new PlaceOrderButton(this);
/*  36 */     this.orderPostponeButton = new OrderPostponeButton(this);
/*  37 */     this.placeBorrelOrderButton = new PlaceBorrelOrderButton(this);
/*  38 */     this.orderUserButton = new OrderUserButton(this);
/*  39 */     this.touchColumn = new TouchColumn(this, 0.125D, 0.9166666666666666D);
/*  40 */     add((Component)this.touchColumn);
/*  41 */     add(this.orderUserButton);
/*  42 */     add(this.placeOrderButton);
/*  43 */     add(this.placeBorrelOrderButton);
/*  44 */     add(this.orderPostponeButton);
/*  45 */     setLayout((LayoutManager)null);
/*  46 */     this.touchColumn.setBounds(0, 0, getWidth(), getHeight() * 8 / 10);
/*  47 */     this.orderUserButton.setBounds(0, getHeight() * 8 / 10, getWidth(), getHeight() / 10);
/*  48 */     this.orderPostponeButton.setBounds(0, getHeight() * 8 / 10, getWidth(), getHeight() / 10);
/*  49 */     this.placeOrderButton.setBounds(0, getHeight() - 1 + getHeight() / 10, getWidth(), getHeight() / 10);
/*  50 */     this.placeBorrelOrderButton.setBounds(0, getHeight() - 1 + getHeight() / 10, getWidth(), getHeight() / 10);
/*  51 */     setBackground(new Color(128, 154, 205));
/*  52 */     addComponentListener(this);
/*     */   }
/*     */   
/*     */   public GUIPanel getMasterPanel() {
/*  56 */     return this.parent.getMasterPanel();
/*     */   }
/*     */   
/*     */   protected void setBorrelMode(boolean enabled) {
/*  60 */     if (enabled != this.inBorrelMode)
/*     */     {
/*  62 */       realSetBorrelMode(enabled);
/*     */     }
/*     */   }
/*     */   
/*     */   private void realSetBorrelMode(boolean enabled) {
/*  67 */     this.placeBorrelOrderButton.setVisible(enabled);
/*  68 */     this.orderPostponeButton.setVisible(enabled);
/*  69 */     this.orderUserButton.setVisible(!enabled);
/*  70 */     this.placeOrderButton.setVisible(!enabled);
/*  71 */     this.inBorrelMode = enabled;
/*     */   }
/*     */   
/*     */   protected void repaintButtons() {
/*  75 */     this.touchColumn.repaint();
/*  76 */     if (!this.inBorrelMode) this.orderUserButton.repaint(); 
/*  77 */     if (this.inBorrelMode) this.placeBorrelOrderButton.repaint(); 
/*     */   }
/*     */   
/*     */   protected void rereadButtons() {
/*  81 */     this.touchColumn.rereadButtons();
/*  82 */     repaintButtons();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public JComponent createButton(int index) {
/*  89 */     return new LineItemButton(this, this.parent.getOrder().getLineItem(index));
/*     */   }
/*     */   
/*     */   public JComponent createNextPageButton(ActionListener actionListener) {
/*  93 */     this.downButton.addActionListener(actionListener);
/*  94 */     return (JComponent)this.downButton;
/*     */   }
/*     */   
/*     */   public JComponent createPrevPageButton(ActionListener actionListener) {
/*  98 */     this.upButton.addActionListener(actionListener);
/*  99 */     return (JComponent)this.upButton;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void destroyButton(JComponent button) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public int numButtons() {
/* 109 */     if (this.parent.getOrder() == null) return 0; 
/* 110 */     return this.parent.getOrder().numLineItems();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentHidden(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentMoved(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentResized(ComponentEvent e) {
/* 125 */     this.touchColumn.setBounds(0, 0, getWidth(), getHeight() * 8 / 10);
/* 126 */     this.orderUserButton.setBounds(0, getHeight() * 8 / 10, getWidth(), getHeight() / 10);
/* 127 */     this.orderPostponeButton.setBounds(0, getHeight() * 8 / 10, getWidth(), getHeight() / 10);
/* 128 */     this.placeOrderButton.setBounds(0, getHeight() - 1 + getHeight() / 10, getWidth(), getHeight() / 10);
/* 129 */     this.placeBorrelOrderButton.setBounds(0, getHeight() - 1 + getHeight() / 10, getWidth(), getHeight() / 10);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean matchesFilter(Object filter, JComponent button) {
/* 139 */     return true;
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/OrderPane.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */