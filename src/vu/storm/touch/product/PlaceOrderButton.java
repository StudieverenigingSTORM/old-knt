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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PlaceOrderButton
/*    */   extends JPanel
/*    */   implements ActionListener, ComponentListener
/*    */ {
/*    */   OrderPane parent;
/*    */   StyledButton button;
/*    */   
/*    */   protected PlaceOrderButton(OrderPane parent) {
/* 30 */     this.parent = parent;
/* 31 */     this.button = new StyledButton(this);
/* 32 */     add((Component)this.button);
/* 33 */     setBackground(new Color(128, 154, 205));
/* 34 */     setLayout((LayoutManager)null);
/* 35 */     this.button.addActionListener(this);
/* 36 */     addComponentListener(this);
/*    */   }
/*    */   
/*    */   private class StyledButton extends StandardStyledButton {
/*    */     Font font;
/*    */     PlaceOrderButton parent;
/*    */     
/*    */     public StyledButton(PlaceOrderButton parent) {
/* 44 */       super(false);
/* 45 */       this.parent = parent;
/* 46 */       rescale();
/*    */     }
/*    */     
/*    */     protected void rescale() {
/* 50 */       this.font = new Font("Verdana", 1, this.parent.parent.parent.scaleFontSize(20));
/* 51 */       setFont(this.font);
/*    */     }
/*    */     
/*    */     public void paint(Graphics g) {
/* 55 */       super.paint(g);
/* 56 */       g.setFont(this.font);
/* 57 */       FontMetrics fm = g.getFontMetrics(this.font);
/* 58 */       String placeOrder = "Bestellen";
/* 59 */       g.drawString(placeOrder, (getWidth() - fm.stringWidth(placeOrder)) / 2, getHeight() / 2 + fm.getDescent());
/*    */     }
/*    */     
/*    */     protected void press() {
/* 63 */       if (this.lst != null) {
/*    */         
/* 65 */         ActionEvent event = new ActionEvent(this, 1001, "normal");
/* 66 */         this.lst.actionPerformed(event);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {
/* 73 */     this.parent.parent.placeOrderClicked();
/* 74 */     if (!e.getActionCommand().equals("normal"))
/*    */     {
/*    */       
/* 77 */       e.getActionCommand().equals("long");
/*    */     }
/*    */   }
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
/*    */   public void componentResized(ComponentEvent e) {
/* 94 */     int w = getWidth();
/* 95 */     int h = getHeight();
/* 96 */     this.button.setBounds(w / 20, h / 10, 9 * w / 10, 8 * h / 10);
/* 97 */     this.button.rescale();
/* 98 */     this.button.repaint();
/*    */   }
/*    */   
/*    */   public void componentShown(ComponentEvent e) {}
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/PlaceOrderButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */