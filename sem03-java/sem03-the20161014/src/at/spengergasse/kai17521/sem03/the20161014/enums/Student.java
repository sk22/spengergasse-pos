package at.spengergasse.kai17521.sem03.the20161014.enums;

public class Student {
  private Grade grade;
  private String name;
  
  public Student() {}

  public Student(String name, Grade grade) {
    setName(name);
    setGrade(grade);
  }
  
  public Grade getGrade() {
    return grade;
  }

  public void setGrade(Grade grade) {
    if (grade == null) {
      throw new IllegalArgumentException("Grade must not be null!");
    }
    this.grade = grade;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("Name must not be null and contain text!");
    }
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name + ": " + this.grade.label;
  }
}
