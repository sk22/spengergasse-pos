package at.spengergasse.kai17521.sem03.lab10;

import at.spengergasse.kai17521.sem03.lab10.exam.Exam;
import at.spengergasse.kai17521.sem03.lab10.exam.GermanExam;
import at.spengergasse.kai17521.sem03.lab10.exam.MultipleChoiceExam;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 12/16/2016
 */
public class ExamAdministrationTest {
  private ExamAdministration admin = new ExamAdministration();

  @Before
  public void setUp() {
    admin.add(new GermanExam(100, new Student("X", "0X"),
      new Submission(LocalDate.now().minusDays(10),
        new File("german-26-words.txt"))));
    admin.add(new MultipleChoiceExam(new Student("V", "0Y"),
      new Submission(LocalDate.now().minusDays(5),
        new File("answers-mc.txt"), new File("positive-mc.txt"))));
    admin.add(new GermanExam(140, new Student("I", "0Z"),
      new Submission(LocalDate.now().minusDays(1),
        new File("german-26-words.txt"))));
  }

  @Test
  public void load() throws Exception {
    ExamAdministration a = new ExamAdministration();
    a.load(new File("filechen.txt"));
    System.out.println(a);
  }

  @Test
  public void save() throws Exception {
    admin.save(new File("filechen.txt"));
  }

  @Test
  public void sortedByDate() {
    List<Exam> sorted = admin.sortedByDate();
    LocalDate first = sorted.get(0).getSubmission().getDate();
    LocalDate last = sorted.get(sorted.size() - 1).getSubmission().getDate();
    assertTrue(first.isBefore(last) || first.isEqual(last));
    assertTrue(first.isBefore(sorted.get(1).getSubmission().getDate())
      || first.isEqual(sorted.get(1).getSubmission().getDate()));
  }

  @Test
  public void sortedBySchoolClass() {
    List<Exam> sorted = admin.sortedBySchoolClass();
    String first = sorted.get(0).getStudent().getSchoolClass();
    String last = sorted.get(sorted.size() - 1).getStudent().getSchoolClass();
    System.out.printf("First: %s, Last: %s\n", first, last);
    assertTrue(first
      .compareTo(sorted.get(1).getStudent().getSchoolClass()) <= 0);
    assertTrue(first.compareTo(last) <= 0);
  }

  @Test
  public void getExamsByStudent() {
    assertEquals(1, admin.getExamsByStudent(new Student("X", "0X")).size());
  }
}