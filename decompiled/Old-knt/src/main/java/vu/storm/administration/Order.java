/*    */ package vu.storm.administration;
/*    */ 
/*    */ import java.util.Vector;
/*    */ 
/*    */ public class Order extends IOrder {
/*    */   User user;
/*  7 */   Vector<OrderLine> lineItems = new Vector<OrderLine>();
/*    */   
/*    */   Order(User user) {
/* 10 */     this.user = user;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   boolean canAdd(long price) {
/* 16 */     if (this.user.isBorrelUser() && !this.user.borrelHasMax()) return true; 
/* 17 */     return (totalPrice() + price <= this.user.getBalance());
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean addLineItem(IProduct product, long amount) {
/* 22 */     if (!canAdd(product.getPrice() * amount)) {
/* 23 */       return false;
/*    */     }
/* 25 */     if (hasProduct(product))
/*    */     {
/* 27 */       return ((OrderLine)this.lineItems.elementAt(productIndex(product))).setAmount(((OrderLine)this.lineItems.elementAt(productIndex(product))).getAmount() + amount);
/*    */     }
/*    */ 
/*    */     
/* 31 */     this.lineItems.add(new OrderLine(this, (Product)product, amount));
/* 32 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IOrderLine getLineItem(int index) {
/* 38 */     return this.lineItems.elementAt(index);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public IUser getUser() {
/* 44 */     return this.user;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasProduct(IProduct product) {
/* 49 */     return (productIndex(product) >= 0);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int numLineItems() {
/* 55 */     return this.lineItems.size();
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public int productIndex(IProduct product) {
/* 61 */     for (int i = 0; i < this.lineItems.size(); i++) {
/*    */       
/* 63 */       if (((OrderLine)this.lineItems.elementAt(i)).getProduct() == product) return i; 
/*    */     } 
/* 65 */     return -1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeLineItem(int index) {
/* 70 */     this.lineItems.remove(index);
/*    */   }
/*    */ 
/*    */   
/*    */   public void removeLineItem(IOrderLine lineItem) {
/* 75 */     this.lineItems.remove(lineItem);
/*    */   }
/*    */ 
/*    */   
/*    */   public long totalPrice() {
/* 80 */     long sum = 0L;
/* 81 */     for (int i = 0; i < this.lineItems.size(); i++)
/*    */     {
/* 83 */       sum += ((OrderLine)this.lineItems.elementAt(i)).totalPrice();
/*    */     }
/* 85 */     return sum;
/*    */   }
/*    */ }


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/Order.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */