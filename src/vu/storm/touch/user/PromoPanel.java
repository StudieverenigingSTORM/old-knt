/*    */ package vu.storm.touch.user;
/*    */ 
/*    */ import java.awt.Graphics;
/*    */ import java.awt.Image;
/*    */ import javax.swing.JPanel;
/*    */ 
/*    */ public class PromoPanel
/*    */   extends JPanel
/*    */ {
/*    */   boolean hasImage = false;
/*    */   Image currentImage;
/*    */   Image realCurrentImage;
/* 13 */   int imgHeight = 0, imgWidth = 0;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void setImage(Image img) {
/* 21 */     if (this.currentImage == this.realCurrentImage)
/*    */     {
/* 23 */       this.realCurrentImage = img;
/*    */     }
/* 25 */     this.currentImage = img;
/* 26 */     if (img == null) {
/*    */       
/* 28 */       this.hasImage = false;
/* 29 */       this.imgHeight = 0;
/* 30 */       this.imgWidth = 0;
/*    */     }
/*    */     else {
/*    */       
/* 34 */       this.hasImage = true;
/* 35 */       this.imgHeight = img.getHeight(null);
/* 36 */       this.imgWidth = img.getWidth(null);
/*    */     } 
/*    */   }
/*    */   
/*    */   public void paint(Graphics g) {
/* 41 */     super.paint(g);
/* 42 */     if (this.realCurrentImage != null) {
/*    */       
/* 44 */       this.imgHeight = this.realCurrentImage.getHeight(this);
/* 45 */       this.imgWidth = this.realCurrentImage.getWidth(this);
/* 46 */       int w = getWidth();
/* 47 */       int h = getHeight();
/*    */       
/* 49 */       int cw = h * this.imgWidth / this.imgHeight;
/* 50 */       int ch = w * this.imgHeight / this.imgWidth;
/* 51 */       if (cw <= w) {
/*    */         
/* 53 */         g.drawImage(this.realCurrentImage, (w - cw) / 2, 0, cw, h, this);
/*    */       }
/* 55 */       else if (ch <= h) {
/*    */         
/* 57 */         g.drawImage(this.realCurrentImage, 0, (h - ch) / 2, w, ch, this);
/*    */       }
/*    */       else {
/*    */         
/* 61 */         g.drawImage(this.realCurrentImage, 0, 0, getWidth(), getHeight(), this);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/touch/user/PromoPanel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */