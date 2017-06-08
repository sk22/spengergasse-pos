package at.spengergasse.kai17521.sem03.lab10.exam;

import at.spengergasse.kai17521.sem03.lab10.Student;
import at.spengergasse.kai17521.sem03.lab10.Submission;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 12/15/2016
 */
public class MultipleChoiceExamTest {
  @Test
  public void grade() throws Exception {
    MultipleChoiceExam exam = getExam(true);
    assertEquals(5, getExam(false).grade());
    assertEquals(1, getExam(true).grade());
  }

  private MultipleChoiceExam getExam(boolean positive) {
    return new MultipleChoiceExam(
      new Student("nobody", "0Z"),
      new Submission(
        LocalDate.now(),
        new File("answers-mc.txt"),
        new File(positive ? "positive-mc.txt" : "negative-mc.txt")
      )
    );
  }
}