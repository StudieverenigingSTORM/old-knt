/*    */ package vu.storm.touch.gfx;
/*    */ 
/*    */ import java.awt.FontMetrics;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.LayoutManager;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.awt.event.ComponentEvent;
/*    */ import java.awt.event.ComponentListener;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class StandardButton
/*    */   extends JPanel
/*    */   implements ComponentListener
/*    */ {
/*    */   StyledButton button;
/*    */   
/*    */   public StandardButton(String text) {
/* 22 */     this.button = new StyledButton(this, text);
/* 23 */     add(this.button);
/* 24 */     setLayout((LayoutManager)null);
/* 25 */     addComponentListener(this);
/*    */   }
/*    */   
/*    */   private class StyledButton extends StandardStyledButton {
/*    */     StandardButton parent;
/*    */     String text;
/*    */     
/*    */     public StyledButton(StandardButton parent, String text) {
/* 33 */       super(false);
/* 34 */       this.parent = parent;
/* 35 */       this.text = text;
/*    */     }
/*    */     
/*    */     public void paint(Graphics g) {
/* 39 */       super.paint(g);
/* 40 */       g.setFont(GfxUtil.fitFont(g, "Verdana", 1, this.text, getHeight() - 6, getWidth() - 6));
/* 41 */       FontMetrics fm = g.getFontMetrics();
/* 42 */       g.drawString(this.text, (getWidth() - fm.stringWidth(this.text)) / 2, getHeight() / 2 - fm.getHeight() / 2 + fm.getAscent());
/*    */     }
/*    */     
/*    */     protected void press() {
/* 46 */       if (this.lst != null) {
/*    */         
/* 48 */         ActionEvent event = new ActionEvent(this.parent, 1001, "normal");
/* 49 */         this.lst.actionPerformed(event);
/*    */       } 
/*    */     }
/*    */   }
/*    */   
/*    */   public void addActionListener(ActionListener l) {
/* 55 */     this.button.addActionListener(l);
/*    */   }
/*    */   
/*    */   public void removeActionListener(ActionListener l) {
/* 59 */     this.button.removeActionListener(l);
/*    */   }
/*    */ 
/*    */   
/*    */   public void componentHidden(ComponentEvent e) {}
/*    */ 
/*    */   
/*    */   public void componentMoved(ComponentEvent e) {}
/*    */   
/*    */   public void componentResized(ComponentEvent e) {
/* 69 */     int w = getWidth();
/* 70 */     int h = getHeight();
/* 71 */     this.button.setBounds(w / 20, h / 10, 9 * w / 10, 8 * h / 10);
/* 72 */     this.button.repaint();
/*    */   }
/*    */   
/*    */   public void componentShown(ComponentEvent e) {}
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/StandardButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */