/*    */ package vu.storm.touch.gfx;
/*    */ 
/*    */ import java.awt.Color;
/*    */ import java.awt.FontMetrics;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.event.ComponentEvent;
/*    */ import java.awt.event.ComponentListener;
/*    */ import java.util.Calendar;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ClockPanel
/*    */   extends JPanel
/*    */   implements ComponentListener
/*    */ {
/*    */   public ClockPanel() {
/* 18 */     setBackground(Color.black);
/*    */   }
/*    */ 
/*    */   
/*    */   public void paint(Graphics g) {
/* 23 */     super.paint(g);
/* 24 */     g.setColor(Color.white);
/* 25 */     Calendar c = Calendar.getInstance();
/* 26 */     String time = String.format("%02d:%02d", new Object[] { Integer.valueOf(c.get(11)), Integer.valueOf(c.get(12)) });
/* 27 */     g.setFont(GfxUtil.fitFont(g, "Courier New", 1, time, getHeight(), getWidth()));
/* 28 */     FontMetrics fm = g.getFontMetrics();
/* 29 */     g.drawString(time, getWidth() - fm.stringWidth(time), fm.getAscent());
/* 30 */     final int x = (60 - c.get(13)) % 60;
/* 31 */     Thread t = new Thread() {
/*    */         public void run() {
/*    */           
/* 34 */           try { Thread.sleep(x); } catch (InterruptedException interruptedException) {}
/* 35 */           ClockPanel.this.repaint();
/*    */         }
/*    */       };
/* 38 */     t.start();
/*    */   }
/*    */   
/*    */   public void componentHidden(ComponentEvent e) {}
/*    */   
/*    */   public void componentMoved(ComponentEvent e) {}
/*    */   
/*    */   public void componentResized(ComponentEvent e) {}
/*    */   
/*    */   public void componentShown(ComponentEvent e) {}
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/ClockPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */