/*    */ package vu.storm.administration;
/*    */ 
/*    */ public class OrderLine
/*    */   extends IOrderLine {
/*    */   private Order order;
/*    */   Product product;
/*    */   long amount;
/*    */   
/*    */   OrderLine(Order order, Product product, long amount) {
/* 10 */     this.order = order;
/* 11 */     this.product = product;
/* 12 */     this.amount = amount;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public long getAmount() {
/* 18 */     return this.amount;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IOrder getOrder() {
/* 24 */     return this.order;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IProduct getProduct() {
/* 30 */     return this.product;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean setAmount(long amount) {
/* 35 */     if (this.amount == amount) return false; 
/* 36 */     if (this.amount > amount) {
/*    */       
/* 38 */       this.amount = amount;
/* 39 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 43 */     if (this.order.canAdd((amount - this.amount) * this.product.getPrice())) {
/*    */       
/* 45 */       this.amount = amount;
/* 46 */       return true;
/*    */     } 
/*    */ 
/*    */     
/* 50 */     return false;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public long totalPrice() {
/* 57 */     return this.amount * this.product.getPrice();
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/OrderLine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */