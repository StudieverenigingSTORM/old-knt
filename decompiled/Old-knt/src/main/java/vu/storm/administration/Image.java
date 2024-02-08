/*    */ package vu.storm.administration;
/*    */ 
/*    */ public class Image extends IImage {
/*    */   long id;
/*    */   String name;
/*    */   byte[] data;
/*    */   
/*    */   Image(long id, String name, byte[] data) {
/*  9 */     this.id = id;
/* 10 */     this.name = name;
/* 11 */     this.data = data;
/*    */   }
/*    */ 
/*    */   
/*    */   public byte[] getData() {
/* 16 */     return this.data;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 21 */     return this.name;
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/Image.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */