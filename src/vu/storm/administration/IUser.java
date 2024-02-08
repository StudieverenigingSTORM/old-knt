package vu.storm.administration;

public abstract class IUser {
  public abstract String getName();
  
  public abstract String getLogin();
  
  public abstract long getBalance();
  
  public abstract boolean matchesSearch(String paramString);
  
  public abstract boolean isBorrelUser();
  
  public abstract boolean isBorrelCie();
  
  public abstract boolean hasPin();
  
  public abstract boolean authenticatePin(String paramString);
  
  public abstract long totalOrdersPrice();
}


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/IUser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */