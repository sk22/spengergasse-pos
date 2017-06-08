package at.spengergasse.kai17521.sem03.lab10;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;

/**
 * @author Samuel Kaiser
 * @since 12/16/2016
 */
public class SubmissionTest {
  @Test (expected = IllegalArgumentException.class)
  public void throwsExceptionWhenFileInexistent() {
    new Submission(LocalDate.now(), new File("C:\\gibschnix"));
  }

  @Test (expected = IllegalArgumentException.class)
  public void throwsExceptionWhenFileNull() {
    new Submission(LocalDate.now(), null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void throwsExceptionWhenDateNull() {
    new Submission(null, new File("filechen.txt"));
  }
}