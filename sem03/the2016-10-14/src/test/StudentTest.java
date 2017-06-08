package test;

import static org.junit.Assert.*;

import org.junit.Test;

import at.spengergasse.kai17521.sem03.the20161014.enums.Grade;
import at.spengergasse.kai17521.sem03.the20161014.enums.Student;

public class StudentTest {
  
  @Test
  public void setNameWithValidValue() {
    Student student = new Student();
    String name = "Hans";
    student.setName(name);
    assertEquals(student.getName(), name);
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void setNameWithInvalidValue() {
    Student student = new Student();
    student.setName(null);
  }
  
  @Test
  public void setGradeWithValidValue() {
    Student student = new Student();
    student.setGrade(Grade.GUT);
    assertEquals(student.getGrade(), Grade.GUT);
  }
 
  @Test (expected = IllegalArgumentException.class)
  public void setGradeWithInvalidValue() {
    Student student = new Student();
    student.setGrade(null);
  }

}
