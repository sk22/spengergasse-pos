package at.spengergasse.kai17521.sem03.lab10.exam;

import at.spengergasse.kai17521.sem03.lab10.Student;
import at.spengergasse.kai17521.sem03.lab10.Submission;

import java.io.*;
import java.time.LocalDate;

import static java.lang.Math.*;

/**
 * @author Samuel Kaiser
 * @since 12/12/2016
 */
public class GermanExam extends Exam {
  /** Target amount of words */
  protected int target;

  /**
   * Creates a new German Exam based on the given arguments.
   * @param target Target amount of words
   * @param student Student object
   * @param submission Submission containing one file
   */
  public GermanExam(int target, Student student, Submission submission) {
    super(student, submission);
    if (submission.getFiles().length != 1) {
      throw new IllegalArgumentException("There should be one submission " +
        "file. Student \"" + student.getName() + "\" " +
        "passed " + submission.getFiles().length + ".");
    }
    if (target <= 0) {
      throw new IllegalArgumentException("Target must be greater than 0");
    }
    this.target = target;
  }

  /**
   * Parses the German Exam's String representation
   * @see #parse(String)
   */
  public GermanExam(String representation) {
    super(representation);
  }

  /**
   * Gets the amount of words
   * @return Amount of words
   * @throws FileNotFoundException File was not found
   */
  public int getActual() throws FileNotFoundException {
    return countWords();
  }

  /**
   * @return Target score (perfect amount of words)
   */
  public int getTarget() {
    return target;
  }

  /**
   * Counts the words in the file
   * @return Amount of words
   * @throws FileNotFoundException File was not found
   */
  public int countWords() throws FileNotFoundException {
    return new BufferedReader(new FileReader(submission.getFiles()[0])).lines()
      .mapToInt(GermanExam::countWords)
      .sum();
  }

  /**
   * Counts the words in a line, including special German alphabetical chars
   * @param line One line of the text
   * @return Amount of words
   */
  public static int countWords(String line) {
    return line.split("[A-ZÄÖÜa-zäöüß]\\S+", - 1).length - 1;
  }

  /**
   * Parses the representation if it matches the following pattern: <br>
   * <code>{target word amount}, {student name}, {student school class},
   * {YYYY-mm-dd}, {file name}</code>
   * @param representation String containing the parameters as stated above
   * @throws IllegalArgumentException Amount of parameters is not valid
   */
  @Override
  public void parse(String representation) throws IllegalArgumentException {
    String[] parts = split(representation);
    if (parts.length != 5) {
      throw new IllegalArgumentException("Amount of parameters is not valid");
    }

    target = Integer.parseInt(parts[0]);
    student = new Student(parts[1], parts[2]);
    submission = new Submission(LocalDate.parse(parts[3]), new File(parts[4]));
  }

  @Override
  public String toString() {
    return String.format(
      "German: %s, %s, %s, %s, %s",
      target,
      student.getName(),
      student.getSchoolClass(),
      submission.getDate().toString(),
      submission.getFiles()[0].getName()
    );
  }
}
