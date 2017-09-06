package at.spengergasse.kai17521.sem03.lab06;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Samuel Kaiser
 * @since 10/25/2016
 */
public class School {
  private int id;
  private Map<String, Student> students = new HashMap<>();
  private short idLength = 5;

  public School() {}

  /**
   * Constructs a School with a custom length for the Students' IDs
   * @param idLength Length
   */
  public School(short idLength) {
    this.idLength = idLength;
  }

  private String newID(Student student) {
    String[] nameParts = student.getName().split("\\s+");
    String format = "%s%0" + idLength + "d";
    return String.format(format, nameParts[nameParts.length - 1]
      .substring(0, 3).toUpperCase(), ++id);
  }

  /**
   * Adds a Student to the school and gives them a unique identifier for
   * this School
   * @param student Student to be added
   * @return Student's ID
   */
  public String add(Student student) {
    String id = newID(student);
    this.students.put(id, student);
    return id;
  }

  /**
   * Returns the Student that has the given ID
   * @param id Student's ID
   * @return Student with the given ID
   */
  public Student get(String id) {
    return students.get(id);
  }

  /**
   * Removes a Student from the School
   * @param id Student's ID
   * @return Removed Student
   */
  public Student remove(String id) {
    return students.remove(id);
  }

  /**
   * Returns the Map that holds all students
   * @return Map object
   */
  Map getStudents() {
    return students;
  }
}
