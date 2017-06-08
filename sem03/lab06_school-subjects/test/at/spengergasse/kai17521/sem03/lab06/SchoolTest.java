package at.spengergasse.kai17521.sem03.lab06;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 10/25/2016
 */
public class SchoolTest {
  private School school;
  @Test
  public void add() {
    school = new School();
    school.add(new Student("Stefan Kormann - 2DHIF - Programming: 1"));
    assertTrue(school.getStudents().containsKey("KOR00001"));
  }

  @Test
  public void get() {
    add();
    Optional<Subject> matching = school.get("KOR00001").getSubjects().stream()
      .filter(subject -> "Programming".equals(subject.getName())).findFirst();
    if (matching.isPresent()) assertEquals(1, matching.get().getGrade());
  }

  @Test
  public void generateID() {
    school = new School();
    String id = school.add(new Student("Peter Pan", "3CIFS", null));
    assertEquals("ID was generated properly", "PAN00001", id);
  }

  @Test
  public void alternateIDLength() {
    school = new School((short) 3);
    String id = school.add(new Student("Benjamin Blümchen", "1A", null));
    assertEquals("ID has a length of 3", "BLÜ001", id);
  }
}