/*    */ package vu.storm.touch.gfx;
/*    */ 
/*    */ import java.awt.FontMetrics;
/*    */ import java.awt.Graphics;
/*    */ 
/*    */ public class WordWrap
/*    */ {
/*    */   public static int drawString(Graphics g, String text, int x, int y, int w) {
/*  9 */     FontMetrics fm = g.getFontMetrics();
/* 10 */     String[] words = text.split(" ");
/* 11 */     int cx = x;
/*    */     
/* 13 */     int lines = 1;
/*    */     
/* 15 */     for (int i = 0; i < words.length; i++) {
/*    */       
/* 17 */       String word = (i == words.length - 1) ? words[i] : (String.valueOf(words[i]) + " ");
/* 18 */       int sw = fm.stringWidth(word);
/* 19 */       if (sw > w - cx && cx != x) {
/*    */         
/* 21 */         cx = x;
/* 22 */         y += fm.getHeight();
/* 23 */         lines++;
/*    */       } 
/* 25 */       g.drawString(word, cx, y);
/* 26 */       cx += sw;
/*    */     } 
/* 28 */     return lines;
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/gfx/WordWrap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */