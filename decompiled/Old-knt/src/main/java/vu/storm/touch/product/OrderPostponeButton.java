/*    */ package vu.storm.touch.product;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.Component;
/*    */ import java.awt.Font;
/*    */ import java.awt.FontMetrics;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.LayoutManager;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.awt.event.ComponentEvent;
/*    */ import java.awt.event.ComponentListener;
/*    */ import javax.swing.JPanel;
/*    */ import vu.storm.touch.gfx.StandardStyledButton;
/*    */ 
/*    */ 
/*    */ public class OrderPostponeButton
/*    */   extends JPanel
/*    */   implements ActionListener, ComponentListener
/*    */ {
/*    */   OrderPane parent;
/*    */   StyledButton button;
/*    */   
/*    */   protected OrderPostponeButton(OrderPane parent) {
/* 25 */     this.parent = parent;
/* 26 */     this.button = new StyledButton(this);
/* 27 */     add((Component)this.button);
/* 28 */     setBackground(new Color(128, 154, 205));
/* 29 */     setLayout((LayoutManager)null);
/* 30 */     this.button.addActionListener(this);
/* 31 */     addComponentListener(this);
/*    */   }
/*    */   
/*    */   private class StyledButton extends StandardStyledButton {
/*    */     Font font;
/*    */     OrderPostponeButton parent;
/*    */     
/*    */     public StyledButton(OrderPostponeButton parent) {
/* 39 */       super(false);
/* 40 */       this.parent = parent;
/* 41 */       rescale();
/*    */     }
/*    */     
/*    */     protected void rescale() {
/* 45 */       this.font = new Font("Verdana", 1, this.parent.parent.parent.scaleFontSize(20));
/* 46 */       setFont(this.font);
/*    */     }
/*    */     
/*    */     public void paint(Graphics g) {
/* 50 */       super.paint(g);
/* 51 */       g.setFont(this.font);
/* 52 */       FontMetrics fm = g.getFontMetrics(this.font);
/* 53 */       String postponeOrder = "Naar wachtrij";
/* 54 */       g.drawString(postponeOrder, (getWidth() - fm.stringWidth(postponeOrder)) / 2, getHeight() / 2 + fm.getDescent());
/*    */     }
/*    */     
/*    */     protected void press() {
/* 58 */       if (this.lst != null) {
/*    */         
/* 60 */         ActionEvent event = new ActionEvent(this, 1001, "normal");
/* 61 */         this.lst.actionPerformed(event);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {
/* 68 */     this.parent.parent.postponeOrderClicked();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void componentHidden(ComponentEvent e) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void componentMoved(ComponentEvent e) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void componentResized(ComponentEvent e) {
/* 83 */     int w = getWidth();
/* 84 */     int h = getHeight();
/* 85 */     this.button.rescale();
/* 86 */     this.button.setBounds(w / 20, h / 10, 9 * w / 10, 8 * h / 10);
/* 87 */     this.button.repaint();
/*    */   }
/*    */   
/*    */   public void componentShown(ComponentEvent e) {}
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/OrderPostponeButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */