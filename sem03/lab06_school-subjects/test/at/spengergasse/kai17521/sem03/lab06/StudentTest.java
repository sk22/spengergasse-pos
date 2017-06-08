package at.spengergasse.kai17521.sem03.lab06;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 10/24/2016
 */
public class StudentTest {
  private Student student;

  @Before
  public void before() {
    this.student = new Student("Hans Peter", "3HDIF",
      new HashSet<>(Arrays.asList(new Subject[] {
        new Subject("Mathematics", 5),
        new Subject("English", 2),
        new Subject("German", 2),
        new Subject("Invalid", 0)
      }))
    );
  }

  @Test
  public void parse() {
    student = new Student("Hans Peter - 3HDIF - Mathematics:4 English:2");
    assertEquals("Name was set", "Hans Peter", student.getName());
    assertEquals("Class was set", "3HDIF", student.getClassName());
    assertTrue("Grades were set", student.getSubjects().stream()
      .anyMatch(subject -> "Mathematics".equals(subject.getName())));
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalid() {
    student = new Student("alf malf -aa- mep:  1 aarags: 2 wtf:3");
    assertEquals(3, student.getSubjects().size());
    student = new Student("", "", null);
  }

  @Test
  public void irregularSpacing() {
    student = new Student("Rolf Rüdiger - 1CDCC - " +
      "German:1 English : 1 Mathematics  :2");
    assertEquals(3, student.getSubjects().size());
    assertEquals(2, student.getSubjects().stream()
      .filter(subject -> "Mathematics".equals(subject.getName()))
      .collect(Collectors.toList()).get(0).getGrade());
  }

  @Test
  public void withoutGrades() {
    student = new Student("Hans Peter Moritz - 7ACDS");
    assertTrue("Subject list's size is 0", student.getSubjects().size() == 0);
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidName() {
    student = new Student("Alf - 1A");
  }

  @Test (expected = IllegalArgumentException.class)
  public void withoutClass() {
    student = new Student("John Doe");
  }

  @Test
  public void average() {
    assertEquals("Average is 3", 3, student.average(), 0.1);
  }

  @Test
  public void graded() {
    assertEquals("Count of graded subjects is 3", 3,
      student.gradedSubjects().size());
  }

  @Test
  public void bestSubjects() {
    List<Subject> best = student.bestSubjects();
    assertEquals("Count of best subjects is 2", 2, best.size());
    assertTrue("English is one of the student's best subjects",
      best.stream().anyMatch(subject -> "English".equals(subject.getName())));
    assertTrue("German is one of the student's best subjects",
      best.stream().anyMatch(subject -> "German".equals(subject.getName())));
  }

  @Test
  public void removeSubject() {
    student.removeSubject("Mathematics");
    assertFalse("There is no subject with the name 'Mathematics'",
      student.getSubjects().stream().anyMatch(subject ->
        "Mathematics".equals(subject.getName())));
  }

  @Test
  public void justOneSubjectIfMultipleWithSameNameAreAdded() {
    student.setSubjects(new HashSet<>(Arrays.asList(new Subject[] {
      new Subject("Maths", 5),
      new Subject("Maths", 3)
    })));
    assertEquals("There is just one Subject called 'Maths'", 1,
      student.getSubjects().stream().filter(subject ->
        "Maths".equals(subject.getName())).count());
  }
}