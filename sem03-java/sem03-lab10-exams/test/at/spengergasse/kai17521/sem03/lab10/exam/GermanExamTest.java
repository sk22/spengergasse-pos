package at.spengergasse.kai17521.sem03.lab10.exam;

import at.spengergasse.kai17521.sem03.lab10.Student;
import at.spengergasse.kai17521.sem03.lab10.Submission;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 12/12/2016
 */
public class GermanExamTest {
  private GermanExam exam;

  @Test
  public void countWords() throws IOException {
    assertEquals(26, getExam(1).countWords());
  }

  @Test
  public void grade() throws Exception {
    assertEquals(1, getExam(26).grade());
    assertEquals(2, getExam(30).grade());
    assertEquals(5, getExam(13).grade());
    assertEquals(4, getExam(14).grade());
    assertEquals(5, getExam(52).grade());
  }

  private GermanExam getExam(int target) {
    return new GermanExam(
      target,
      new Student("nobody", "0Z"),
      new Submission(LocalDate.now(), new File("german-26-words.txt"))
    );
  }
}

// 26 ... 1
//
// 13 ... 5
