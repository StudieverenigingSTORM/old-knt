/*    */ package vu.storm.touch.gfx;
/*    */ 
/*    */ import java.awt.Font;
/*    */ import java.awt.FontMetrics;
/*    */ import java.awt.Graphics;
/*    */ import javax.swing.JComponent;
/*    */ 
/*    */ 
/*    */ public class GfxUtil
/*    */ {
/*    */   public static Font fitFont(JComponent comp, String fontFamily, int fontStyle, String text) {
/* 12 */     return fitFont(comp, fontFamily, fontStyle, text, comp.getHeight(), comp.getWidth());
/*    */   }
/*    */   
/*    */   public static Font fitFont(JComponent comp, String fontFamily, int fontStyle, String text, int height) {
/* 16 */     return fitFont(comp, fontFamily, fontStyle, text, height, comp.getWidth());
/*    */   }
/*    */   
/*    */   public static Font fitFont(JComponent comp, String fontFamily, int fontStyle, String text, int height, int width) {
/* 20 */     return fitFont(comp.getGraphics(), fontFamily, fontStyle, text, height, width);
/*    */   }
/*    */   
/*    */   public static Font fitFont(Graphics g, String fontFamily, int fontStyle, String text, int height, int width) {
/* 24 */     if (g == null) return null;
/*    */     
/* 26 */     FontMetrics fm = g.getFontMetrics(new Font(fontFamily, fontStyle, 100));
/* 27 */     int w = fm.stringWidth(text);
/* 28 */     int hSize = 100 * height / fm.getHeight();
/* 29 */     int wSize = 100 * width / ((w == 0) ? 1 : w);
/* 30 */     return new Font(fontFamily, fontStyle, (wSize < hSize) ? wSize : hSize);
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/GfxUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */