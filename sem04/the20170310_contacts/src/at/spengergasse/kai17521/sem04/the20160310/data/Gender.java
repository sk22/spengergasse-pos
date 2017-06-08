package at.spengergasse.kai17521.sem04.the20160310.data;

/**
 * @author Samuel Kaiser
 * @since 3/10/2017
 */
public enum Gender {
  FEMALE("Female"),
  MALE("Male"),
  OTHER("Other");

  String name;

  Gender(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return name;
  }
}
