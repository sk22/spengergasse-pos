package at.spengergasse.kai17521.sem03.lab09;

/**
 * @author Samuel Kaiser
 * @since 12/5/2016
 */
public class OrganicGroceryStore extends Store {
  private int products;

  public OrganicGroceryStore(int products, int employers, boolean city, String name) {
    super(employers, city, name);
    setProducts(products);
  }

  public int getProducts() {
    return products;
  }

  public void setProducts(int products) {
    if (products < 0) {
      throw new IllegalArgumentException("Products must be at least 1");
    }
    this.products = products;
  }

  public float getFunding() {
     return city
       ? products : products * 2;
  }

  @Override
  public String toString() {
    return "OrganicGroceryStore{" +
      "products=" + products +
      '}';
  }
}
