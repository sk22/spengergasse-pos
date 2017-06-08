package test;

import org.junit.Before;
import org.junit.BeforeClass;
// import static org.junit.Assert.*;
import org.junit.Test;

import daten.Student;
import daten.Students;

public class StudentsTest {
  private Students students;
  
  @Before
  public void init() {
    this.students = new Students(10);
    students.add(
        new Student("Daniel", 21),
        new Student("Gabriel", 12),
        new Student("Basti", 13)
    );
    System.out.println();
  }

  @Test
  public void add() {  
    System.out.println("Adding Max and Xam");
    students.add(new Student("Max", 21), new Student("Xam", 12));
    this.students.print();
  }

  @Test
  public void limit() {
    Student s1 = new Student("1", 15);
    Student s2 = new Student("2", 15);
    Student s3 = new Student("3", 15);
    Student s4 = new Student("4", 15);
    Student s5 = new Student("5", 15);
    Student s6 = new Student("6", 15);
    Student s7 = new Student("7", 15);
    Student s8 = new Student("8", 15);

    
    this.students.add(s1, s2, s3, s4, s5, s6, s7, s8);
    this.students.print();
  }

  @Test
  public void contains() {
    
    System.out.println("Contains Basti: " + this.students.contains("Basti"));
    System.out.println("Contains Basalt: " + this.students.contains("Basalt"));
  }
  
  @Test
  public void sortByAge() {
    System.out.println("Sorting by age");
    students.sortByAge();
    students.print();
  }
  
  @Test
  public void delete() {
    System.out.println("Deleting Basti");
    students.delete("Basti");
    students.print();
  }
}
