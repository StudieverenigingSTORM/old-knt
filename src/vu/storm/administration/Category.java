/*    */ package vu.storm.administration;
/*    */ 
/*    */ import java.util.Vector;
/*    */ 
/*    */ public class Category extends ICategory {
/*    */   long id;
/*    */   private String name;
/*    */   private boolean visible;
/*  9 */   private Vector<Product> products = new Vector<Product>();
/*    */   
/*    */   Category(long id, String name, boolean visible) {
/* 12 */     this.id = id;
/* 13 */     this.name = name;
/* 14 */     this.visible = visible;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public ICategory getCategory(int index) {
/* 20 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 25 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public IProduct getProduct(int index) {
/* 30 */     return this.products.elementAt(index);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int numCategories() {
/* 36 */     return 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public int numProducts() {
/* 41 */     return this.products.size();
/*    */   }
/*    */ 
/*    */   
/*    */   void addProduct(Product product) {
/* 46 */     this.products.add(product);
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/Category.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */