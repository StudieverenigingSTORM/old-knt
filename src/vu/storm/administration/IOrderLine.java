package vu.storm.administration;

public abstract class IOrderLine {
  public abstract IOrder getOrder();
  
  public abstract IProduct getProduct();
  
  public abstract long getAmount();
  
  public abstract boolean setAmount(long paramLong);
  
  public abstract long totalPrice();
}


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/IOrderLine.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */