package at.spengergasse.kai17521.sem03.lab10;

/**
 * @author Samuel Kaiser
 * @since 12/12/2016
 */
public class Student {
  private String name;
  private String schoolClass;

  public Student(String name, String schoolClass) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Name must not be empty");
    }
    if (schoolClass == null || name.isEmpty()) {
      throw new IllegalArgumentException("School class must not be empty");
    }
    this.name = name;
    this.schoolClass = schoolClass;
  }

  public String getName() {
    return name;
  }

  public String getSchoolClass() {
    return schoolClass;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Student student = (Student) o;

    if (!name.equals(student.name)) return false;
    return schoolClass.equals(student.schoolClass);

  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + schoolClass.hashCode();
    return result;
  }
}
