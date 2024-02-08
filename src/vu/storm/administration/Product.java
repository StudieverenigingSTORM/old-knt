/*    */ package vu.storm.administration;
/*    */ 
/*    */ public class Product extends IProduct {
/*    */   private long id;
/*    */   private String name;
/*    */   private String description;
/*    */   private long price;
/*    */   
/*    */   Product(long id, String name, String description, long price) {
/* 10 */     this.id = id;
/* 11 */     this.name = name;
/* 12 */     this.description = description;
/* 13 */     this.price = price;
/*    */   }
/*    */   long getId() {
/* 16 */     return this.id;
/*    */   }
/*    */   
/*    */   public String getDescription() {
/* 20 */     return this.description;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 25 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public long getPrice() {
/* 30 */     return this.price;
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/Product.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */