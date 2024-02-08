package vu.storm.administration;

public abstract class ICategory {
  public abstract String getName();
  
  public abstract int numCategories();
  
  public abstract ICategory getCategory(int paramInt);
  
  public abstract int numProducts();
  
  public abstract IProduct getProduct(int paramInt);
}


/* Location:              /Users/nicholas/Desktop/old-knt/old-jar/program.jar!/vu/storm/administration/ICategory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */