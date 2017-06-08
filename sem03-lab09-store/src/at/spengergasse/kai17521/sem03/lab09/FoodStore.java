package at.spengergasse.kai17521.sem03.lab09;

/**
 * @author Samuel Kaiser
 * @since 12/5/2016
 */
public class FoodStore extends Store {
  private Category category;

  public FoodStore(Category category, int employers, boolean city, String name) {
    super(employers, city, name);
    setCategory(category);
  }

  @Override
  public float getFunding() {
    // 100 € * 150% ^ employers
    float initial = (float) (100 * Math.pow(1.5, employers - 1));
    if (city) {
      float more = initial + 200;
      return more < 500 ? more : 500;
    }
    return initial;
  }

  @Override
  public String toString() {
    return "FoodStore{" +
      "category=" + category +
      '}';
  }

  public void setCategory(Category category) {
    this.category = category;
  }

  public enum Category {
    SNACK,
    DELICATESSEN,
    SUPERMARKET;
  }
}
