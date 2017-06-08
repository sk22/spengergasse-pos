package at.spengergasse.kai17521.sem03.lab09;

import java.util.ArrayList;

/**
 * @author Samuel Kaiser
 * @since 12/5/2016
 */
public class Administration {
  private ArrayList<Store> stores = new ArrayList<>();

  public void print() {
    System.out.println(toString());
  }

  public void addStore(Store store) {
    stores.add(store);
  }

  public float sumFundings() {
    return (float) stores.stream()
      .mapToDouble(Store::getFunding).sum();
  }

  @Override
  public String toString() {
    String str = "";
    for (Store s : stores) {
      str += stores.toString();
    }
    return str;
  }
}
