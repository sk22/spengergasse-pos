package at.spengergasse.kai17521.sem03.lab10.exam;

import at.spengergasse.kai17521.sem03.lab10.Student;
import at.spengergasse.kai17521.sem03.lab10.Submission;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;

/**
 * @author Samuel Kaiser
 * @since 12/12/2016
 */

public abstract class Exam {
  protected Student student;
  protected Submission submission;

  protected static Map<String, Class<? extends Exam>> types = new HashMap<>();

  static {
    types.put("german", GermanExam.class);
    types.put("multiple choice", MultipleChoiceExam.class);
  }

  public static Class<? extends Exam> getClassByName(String name) {
    return types.get(name.toLowerCase());
  }

  protected static short GRADING_BASIS = 5;
  protected static short STARTING_AT = 1;

  public Exam(Student student, Submission submission) {
    this.student = student;
    this.submission = submission;
  }

  /**
   * Parses the Exam's String representation
   * @see #parse(String)
   */
  public Exam(String representation) {
    parse(representation);
  }

  public abstract void parse(String representation)
    throws IllegalArgumentException;

  public static void setGradingBasis(short gradingBasis) {
    GRADING_BASIS = gradingBasis;
  }

  /**
   * Grades the submission and returns a grade conforming to the GRADING_BASIS
   * @return A grade from STARTING_AT to GRADING_BASIS, by default 1 to 5
   * @throws IOException e.g. {@link FileNotFoundException} if the file
   *                     containing the exam was not found
   */
  public short grade() throws IOException {
    int target = getTarget(), actual = getActual();

    double percentage = target > actual
      ? (double) actual / target
      : (double) target / actual;
    double deviation = abs(percentage - 1); // distance from target
    double step = 1.0 / (GRADING_BASIS - 1) / 2; // segmenting 1 into steps
    double fits = deviation / step; // how often does it fit?
    double grade = fits + STARTING_AT; // adding the best (smallest) grade
    return (short) (grade > GRADING_BASIS ? GRADING_BASIS : grade);
  }

  /**
   * Gets or/and calculates the actual score the student reached.
   * Used to calculate the grade.
   * @return Score
   * @throws IOException e.g. {@link FileNotFoundException} if the file
   *                     was not found
   */
  public abstract int getActual() throws IOException;

  /**
   * Gets the target score the student is supposed to reach.
   * Used to calculate the grade.
   * @return Target score
   * @throws IOException e.g. {@link FileNotFoundException} if the target file
   *                     (if used) was not found
   */
  public abstract int getTarget() throws IOException;

  public Student getStudent() {
    return student;
  }

  public Submission getSubmission() {
    return submission;
  }

  /**
   * Splits the exam's representation
   * @param representation Representation without type of Exam, delimited by
   *                       commas
   * @return String array containing the individual parts
   */
  static String[] split(String representation) {
    return representation.split("\\s*,\\s*");
  }

  /**
   * Creates a one-line representation of the Exam
   * @return Representation String
   */
  @Override
  public abstract String toString();
}
