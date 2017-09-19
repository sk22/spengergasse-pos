package at.spengergasse.kai17521.sem03.the20161007;

public class Book {
  private String name;
  private int grade;
  
  public Book(String name, int grade) {
    setName(name);
    setGrade(grade);
  }

  public String getName() {
    return name;
  }
  
  public int getNote() {
    return grade;
  }
  
  public void setName(String name) {
    if (name == null) {
      throw new IllegalArgumentException("Name must be given!");
    }
    this.name = name;
  }
  
  public void setGrade(int grade) {
    if (grade < 1 || grade > 5) {
      throw new IllegalArgumentException("Note must be greater than 1 and less than 5!");
    }
    this.grade = grade;
  }

  @Override
  public String toString() {
    return "Book [name=" + name + ", grade=" + grade + "]";
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Book other = (Book) obj;
    if (grade != other.grade)
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }

  
  
  
}
