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
/*    */ import vu.storm.touch.gfx.GfxUtil;
/*    */ import vu.storm.touch.gfx.StandardStyledButton;
/*    */ 
/*    */ 
/*    */ public class PlaceBorrelOrderButton
/*    */   extends JPanel
/*    */   implements ActionListener, ComponentListener
/*    */ {
/*    */   OrderPane parent;
/*    */   StyledButton button;
/*    */   
/*    */   protected PlaceBorrelOrderButton(OrderPane parent) {
/* 26 */     this.parent = parent;
/* 27 */     this.button = new StyledButton(this);
/* 28 */     add((Component)this.button);
/* 29 */     setBackground(new Color(128, 154, 205));
/* 30 */     setLayout((LayoutManager)null);
/* 31 */     this.button.addActionListener(this);
/* 32 */     addComponentListener(this);
/*    */   }
/*    */   
/*    */   private class StyledButton extends StandardStyledButton {
/*    */     Font font;
/*    */     PlaceBorrelOrderButton parent;
/*    */     
/*    */     public StyledButton(PlaceBorrelOrderButton parent) {
/* 40 */       super(false);
/* 41 */       this.parent = parent;
/* 42 */       rescale();
/*    */     }
/*    */     
/*    */     protected void rescale() {
/* 46 */       this.font = new Font("Verdana", 1, this.parent.parent.parent.scaleFontSize(20));
/* 47 */       setFont(this.font);
/*    */     }
/*    */     
/*    */     public void paint(Graphics g) {
/* 51 */       super.paint(g);
/*    */ 
/*    */       
/* 54 */       String priceString = String.format("€ %03d", new Object[] { Long.valueOf(this.parent.parent.parent.getOrder().totalPrice()) });
/* 55 */       String placeOrder = "Bestellen:  ";
/* 56 */       priceString = String.valueOf(priceString.substring(0, priceString.length() - 2)) + "." + priceString.substring(priceString.length() - 2);
/* 57 */       g.setFont(GfxUtil.fitFont(g, "Verdana", 1, String.valueOf(placeOrder) + priceString, getHeight() - 6, getWidth() - 6));
/* 58 */       FontMetrics fm = g.getFontMetrics();
/* 59 */       g.drawString(priceString, getWidth() - fm.stringWidth(priceString) - 6, getHeight() / 2 - fm.getHeight() / 2 + fm.getAscent());
/* 60 */       g.drawString(placeOrder, 6, getHeight() / 2 - fm.getHeight() / 2 + fm.getAscent());
/*    */     }
/*    */     
/*    */     protected void press() {
/* 64 */       if (this.lst != null) {
/*    */         
/* 66 */         ActionEvent event = new ActionEvent(this, 1001, "normal");
/* 67 */         this.lst.actionPerformed(event);
/*    */       } 
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void actionPerformed(ActionEvent e) {
/* 74 */     this.parent.parent.placeOrderClicked();
/* 75 */     if (!e.getActionCommand().equals("normal"))
/*    */     {
/*    */       
/* 78 */       e.getActionCommand().equals("long");
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
/* 95 */     int w = getWidth();
/* 96 */     int h = getHeight();
/* 97 */     this.button.rescale();
/* 98 */     this.button.setBounds(w / 20, h / 10, 9 * w / 10, 8 * h / 10);
/* 99 */     this.button.repaint();
/*    */   }
/*    */   
/*    */   public void componentShown(ComponentEvent e) {}
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/PlaceBorrelOrderButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */