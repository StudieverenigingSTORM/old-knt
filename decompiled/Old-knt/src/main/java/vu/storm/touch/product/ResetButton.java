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
/*    */ public class ResetButton
/*    */   extends JPanel
/*    */   implements ActionListener, ComponentListener
/*    */ {
/*    */   ProductScreen parent;
/*    */   StyledButton button;
/*    */   
/*    */   protected ResetButton(ProductScreen parent) {
/* 25 */     this.parent = parent;
/* 26 */     this.button = new StyledButton(this);
/* 27 */     add((Component)this.button);
/*    */     
/* 29 */     setBackground(Color.black);
/* 30 */     setLayout((LayoutManager)null);
/* 31 */     this.button.addActionListener(this);
/* 32 */     addComponentListener(this);
/*    */   }
/*    */   
/*    */   private class StyledButton extends StandardStyledButton {
/*    */     Font font;
/*    */     ResetButton parent;
/*    */     
/*    */     public StyledButton(ResetButton parent) {
/* 40 */       super(true);
/* 41 */       this.parent = parent;
/* 42 */       rescale();
/*    */     }
/*    */     
/*    */     protected void rescale() {
/* 46 */       this.font = new Font("Verdana", 1, this.parent.parent.scaleFontSize(20));
/* 47 */       setFont(this.font);
/*    */     }
/*    */     
/*    */     public void paint(Graphics g) {
/* 51 */       super.paint(g);
/* 52 */       String s = "Home";
/* 53 */       g.setFont(GfxUtil.fitFont(g, "Verdana", 1, s, (getHeight() - 6) * 2 / 3, getWidth() - 6));
/* 54 */       FontMetrics fm = g.getFontMetrics();
/* 55 */       g.drawString(s, (getWidth() - fm.stringWidth(s)) / 2, (getHeight() - 6) / 3 + 3 - fm.getHeight() / 2 + fm.getAscent());
/* 56 */       s = "Alles wissen";
/* 57 */       g.setFont(GfxUtil.fitFont(g, "Verdana", 0, s, (getHeight() - 6) / 3, getWidth() - 6));
/* 58 */       fm = g.getFontMetrics();
/* 59 */       g.drawString(s, (getWidth() - fm.stringWidth(s)) / 2, (getHeight() - 6) * 5 / 6 + 2 - fm.getHeight() / 2 + fm.getAscent());
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
/* 73 */     this.parent.cancelOrderClicked();
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
/* 88 */     int w = getWidth();
/* 89 */     int h = getHeight();
/* 90 */     this.button.setBounds(w / 20, h / 10, 9 * w / 10, 8 * h / 10);
/* 91 */     this.button.rescale();
/* 92 */     this.button.repaint();
/*    */   }
/*    */   
/*    */   public void componentShown(ComponentEvent e) {}
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/product/ResetButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */