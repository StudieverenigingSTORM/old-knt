package vu.storm.administration;

public abstract class IOrder {
  public abstract IUser getUser();
  
  public abstract int numLineItems();
  
  public abstract IOrderLine getLineItem(int paramInt);
  
  public abstract void removeLineItem(int paramInt);
  
  public abstract void removeLineItem(IOrderLine paramIOrderLine);
  
  public abstract boolean addLineItem(IProduct paramIProduct, long paramLong);
  
  public abstract boolean hasProduct(IProduct paramIProduct);
  
  public abstract int productIndex(IProduct paramIProduct);
  
  public abstract long totalPrice();
}


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/IOrder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */