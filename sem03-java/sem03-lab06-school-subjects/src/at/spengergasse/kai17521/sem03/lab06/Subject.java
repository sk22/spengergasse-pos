package at.spengergasse.kai17521.sem03.lab06;

/**
 * @author Samuel Kaiser
 * @since 10/24/2016
 */
public class Subject {
  private int grade;
  private String name;

  public Subject(String name, int grade) {
    this(name);
    setGrade(grade);
  }

  public Subject(String name) {
    setName(name);
  }

  public void setName(String name) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("Label must be a String and have a " +
        "length greater than 0");
    }
    this.name = name;
  }

  public String getName() {
    return this.name;
  }

  public void setGrade(int grade) {
    if (grade < 0 || grade > 5) {
      throw new IllegalArgumentException("Grade must be between 0 and 5");
    }
    this.grade = grade;
  }

  public int getGrade() {
    return this.grade;
  }

  @Override
  public String toString() {
    return name + ": " + grade;
  }

  /** Equals if the names match */
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Subject subject = (Subject) o;

    return name != null ? name.equals(subject.name) : subject.name == null;
  }

  @Override
  public int hashCode() {
    return name != null ? name.hashCode() : 0;
  }
}
