/*     */ package vu.storm.touch.product;
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Component;
/*     */ import java.awt.LayoutManager;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.ActionListener;
/*     */ import java.awt.event.ComponentEvent;
/*     */ import java.awt.event.ComponentListener;
/*     */ import java.util.Vector;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.JPanel;
/*     */ import vu.storm.administration.IOrder;
/*     */ import vu.storm.touch.gfx.ButtonProvider;
/*     */ import vu.storm.touch.gfx.PageButton;
/*     */ import vu.storm.touch.gfx.TouchRow;
/*     */ 
/*     */ public class OrderStack
/*     */   extends JPanel
/*     */   implements ButtonProvider, ComponentListener, ActionListener {
/*     */   Vector<IOrder> lOrders;
/*     */   TouchRow tRow;
/*     */   ProductScreen parent;
/*     */   PageButton leftButton;
/*     */   PageButton rightButton;
/*     */   
/*     */   public OrderStack(ProductScreen parent) {
/*  28 */     this.leftButton = new PageButton(false, true, true);
/*  29 */     this.rightButton = new PageButton(false, false, true);
/*  30 */     this.parent = parent;
/*  31 */     setBackground(Color.blue);
/*  32 */     this.lOrders = new Vector<IOrder>();
/*  33 */     this.tRow = new TouchRow(this, 0.25D, 1.0D);
/*  34 */     this.tRow.setBackground(new Color(192, 192, 192));
/*  35 */     add((Component)this.tRow);
/*  36 */     addComponentListener(this);
/*  37 */     setLayout((LayoutManager)null);
/*     */   }
/*     */   
/*     */   protected IOrder getFirst() {
/*  41 */     return this.lOrders.elementAt(0);
/*     */   }
/*     */   
/*     */   public int numOrders() {
/*  45 */     return this.lOrders.size();
/*     */   }
/*     */   
/*     */   public void addAndRemoveOrder(IOrder add, IOrder remove) {
/*  49 */     this.lOrders.add(add);
/*  50 */     this.lOrders.remove(remove);
/*  51 */     this.tRow.rereadButtons();
/*  52 */     repaint();
/*     */   }
/*     */   
/*     */   public void addOrder(IOrder order) {
/*  56 */     this.lOrders.add(order);
/*  57 */     this.tRow.rereadButtons();
/*     */   }
/*     */   
/*     */   public void removeOrder(IOrder order) {
/*  61 */     this.lOrders.remove(order);
/*  62 */     this.tRow.rereadButtons();
/*  63 */     repaint();
/*     */   }
/*     */   
/*     */   public JComponent createButton(int index) {
/*  67 */     OrderStackButton btn = new OrderStackButton(this, this.lOrders.elementAt(index));
/*  68 */     btn.addActionListener(this);
/*  69 */     return btn;
/*     */   }
/*     */   
/*     */   public JComponent createPrevPageButton(ActionListener actionListener) {
/*  73 */     this.leftButton.removeActionListener(actionListener);
/*  74 */     this.leftButton.addActionListener(actionListener);
/*  75 */     return (JComponent)this.leftButton;
/*     */   }
/*     */   
/*     */   public JComponent createNextPageButton(ActionListener actionListener) {
/*  79 */     this.rightButton.removeActionListener(actionListener);
/*  80 */     this.rightButton.addActionListener(actionListener);
/*  81 */     return (JComponent)this.rightButton;
/*     */   }
/*     */   
/*     */   public void destroyButton(JComponent button) {
/*  85 */     ((OrderStackButton)button).removeActionListener(this);
/*     */   }
/*     */   
/*     */   public boolean matchesFilter(Object filter, JComponent button) {
/*  89 */     return true;
/*     */   }
/*     */   
/*     */   public int numButtons() {
/*  93 */     return this.lOrders.size();
/*     */   }
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
/* 107 */     this.tRow.setBounds(0, 0, getWidth(), getHeight());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void componentShown(ComponentEvent e) {}
/*     */ 
/*     */   
/*     */   public void actionPerformed(ActionEvent e) {
/* 116 */     System.out.println("SWITCH!");
/* 117 */     if (e.getSource() instanceof OrderStackButton) {
/*     */       
/* 119 */       this.parent.switchOrder(((OrderStackButton)e.getSource()).getOrder());
/*     */     }
/*     */     else {
/*     */       
/* 123 */       System.out.println("WEIRD SWITCH!");
/* 124 */       System.out.println(e.getSource().getClass().getName());
/*     */     } 
/*     */   }
/*     */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/OrderStack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */