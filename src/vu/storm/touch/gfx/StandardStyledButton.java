/*    */ package vu.storm.touch.gfx;
/*    */ 
/*    */ import java.awt.AWTEventMulticaster;
/*    */ import java.awt.Color;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.event.ActionListener;
/*    */ import java.awt.event.MouseEvent;
/*    */ import java.awt.event.MouseListener;
/*    */ import javax.swing.JComponent;
/*    */ 
/*    */ 
/*    */ public abstract class StandardStyledButton
/*    */   extends JComponent
/*    */   implements MouseListener
/*    */ {
/* 16 */   protected ActionListener lst = null;
/*    */   
/*    */   protected boolean pressed = false;
/*    */   
/*    */   protected Color normalBack;
/*    */   
/*    */   protected Color normalBorder;
/*    */   
/*    */   protected Color normalFront;
/*    */   
/*    */   protected Color pressedBack;
/*    */   protected Color pressedBorder;
/*    */   protected Color pressedFront;
/*    */   
/*    */   public StandardStyledButton(boolean inverted) {
/* 31 */     if (inverted) {
/*    */       
/* 33 */       this.normalBack = Color.white;
/* 34 */       this.normalFront = Color.black;
/* 35 */       this.normalBorder = Color.black;
/* 36 */       this.pressedBack = Color.black;
/* 37 */       this.pressedFront = Color.white;
/* 38 */       this.pressedBorder = Color.white;
/*    */     }
/*    */     else {
/*    */       
/* 42 */       this.normalBack = Color.black;
/* 43 */       this.normalFront = Color.white;
/* 44 */       this.normalBorder = Color.white;
/* 45 */       this.pressedBack = Color.white;
/* 46 */       this.pressedFront = Color.black;
/* 47 */       this.pressedBorder = Color.black;
/*    */     } 
/* 49 */     addMouseListener(this);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void paint(Graphics g) {
/* 55 */     g.setColor(this.pressed ? this.pressedBorder : this.normalBorder);
/* 56 */     g.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, getHeight() / 4, getHeight() / 4);
/* 57 */     g.setColor(this.pressed ? this.pressedBack : this.normalBack);
/* 58 */     g.fillRoundRect(2, 2, getWidth() - 5, getHeight() - 5, getHeight() / 4, getHeight() / 4);
/* 59 */     g.setColor(this.pressed ? this.pressedFront : this.normalFront);
/*    */   }
/*    */ 
/*    */   
/*    */   public void addActionListener(ActionListener l) {
/* 64 */     this.lst = AWTEventMulticaster.add(this.lst, l);
/*    */   }
/*    */   
/*    */   public void removeActionListener(ActionListener l) {
/* 68 */     this.lst = AWTEventMulticaster.remove(this.lst, l);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseClicked(MouseEvent arg0) {}
/*    */ 
/*    */ 
/*    */   
/*    */   public void mouseEntered(MouseEvent arg0) {}
/*    */ 
/*    */   
/*    */   public void mouseExited(MouseEvent arg0) {}
/*    */ 
/*    */   
/*    */   public void mousePressed(MouseEvent arg0) {
/* 84 */     this.pressed = true;
/* 85 */     repaint();
/*    */   }
/*    */ 
/*    */   
/*    */   public void mouseReleased(MouseEvent arg0) {
/* 90 */     this.pressed = false;
/* 91 */     press();
/* 92 */     repaint();
/*    */   }
/*    */   
/*    */   protected abstract void press();
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/StandardStyledButton.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */