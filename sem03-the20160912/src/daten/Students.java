package daten;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Students {
  private List<Student> students;
  private int limit;

  public Students(int limit, Student... students) {
    this.limit = limit;
    this.students = new ArrayList<>(Arrays.asList(students));
  }
  
  public void add(Student... students) {
    for (Student student : students) {
      if (this.students.size() >= this.limit) {
        System.out.println("Limit reached!");
        return;
      }
      this.students.add(student);
    }
  }
  
  public boolean contains(Student student) {
    return this.students.contains(student);
  }
  
  public List<Student> filter(Predicate<Student> lambda) {
    return this.students.stream().filter(lambda)
        .collect(Collectors.toList());
  }
  
  public boolean contains(String name) {
    List<Student> students = this.filter(p -> p.getName() == name);
    if (students.size() < 1) return false;
    return true;
  }
  
  public void delete(String name) {
    List<Student> students = this.filter(p -> p.getName() == name);
    if (students.size() < 1) return;
    this.students.remove(students.get(0));
  }
  
  public void print() {
    System.out.println("There are " + this.students.size() + " students in this class.");
    for (Student student : this.students) {
      System.out.println("- " + student);
    }
  }
  
  public void sortByAge() {
    for (int i = 0; i < this.students.size() - 1; i++) {
      for (int j = 0; j < this.students.size() - 1 - i; j++) {
        if (students.get(j).getAge() > students.get(j+1).getAge()) {
          Student change = students.get(i);
          this.students.set(i, students.get(j));
          students.set(j, change);
        }
      }
    }
  }
}
