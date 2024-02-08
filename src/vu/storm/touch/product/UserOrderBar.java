/*    */ package vu.storm.touch.product;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.LayoutManager;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.awt.event.ComponentEvent;
/*    */ import java.awt.event.ComponentListener;
/*    */ import javax.swing.JPanel;
/*    */ import vu.storm.touch.gfx.ClockPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class UserOrderBar
/*    */   extends JPanel
/*    */   implements ActionListener, ComponentListener
/*    */ {
/*    */   ProductScreen parent;
/*    */   ResetButton resetButton;
/*    */   ClockPanel clock;
/*    */   
/*    */   public UserOrderBar(ProductScreen parent) {
/* 34 */     setBackground(Color.black);
/* 35 */     this.parent = parent;
/* 36 */     setLayout((LayoutManager)null);
/* 37 */     this.clock = new ClockPanel();
/* 38 */     this.resetButton = new ResetButton(parent);
/* 39 */     add(this.resetButton);
/* 40 */     add((Component)this.clock);
/* 41 */     addComponentListener(this);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void componentHidden(ComponentEvent e) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void componentMoved(ComponentEvent e) {}
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void componentResized(ComponentEvent e) {
/* 59 */     int w = getWidth();
/* 60 */     int h = getHeight();
/* 61 */     this.resetButton.setBounds(0, 0, w / 5, h);
/* 62 */     this.clock.setBounds(w * 4 / 5, 0, w / 5, h);
/*    */   }
/*    */   
/*    */   public void componentShown(ComponentEvent e) {}
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {}
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/UserOrderBar.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */