package at.spengergasse.kai17521.sem03.lab06;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 10/24/2016
 */
public class SubjectTest {
  @Test (expected = IllegalArgumentException.class)
  public void test() throws Exception {
    Subject subject = new Subject("Mathematics");
    assertEquals("Subject name was set", "Mathematics", subject.getName());
    subject.setGrade(6);
  }
}