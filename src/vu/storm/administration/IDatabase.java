package vu.storm.administration;

public interface IDatabase {
  IUser[] getAllUsers();
  
  IUser[] getUsers(String paramString);
  
  IOrder beginOrder(IUser paramIUser);
  
  boolean processOrder(IOrder paramIOrder);
  
  ICategory[] getCategories();
  
  IImage[] getPromotionalImages();
  
  boolean hasUpdatedProducts();
  
  boolean hasUpdatedUsers();
  
  boolean hasRestartRequest();
}


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/IDatabase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */