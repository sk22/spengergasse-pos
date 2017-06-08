package at.spengergasse.kai17521.sem03.lab10.exam;

import at.spengergasse.kai17521.sem03.lab10.Student;
import at.spengergasse.kai17521.sem03.lab10.Submission;

import java.io.*;
import java.time.LocalDate;

/**
 * @author Samuel Kaiser
 * @since 12/15/2016
 */
public class MultipleChoiceExam extends Exam {
  /**
   * Creates a new Multiple Choice Exam based on the given arguments.
   * Pass two files into the submissions constructor: First the answers, then
   * the student's file
   * @param student Student object
   * @param submission Must contain two files: 1. answers, 2. student's version
   */
  public MultipleChoiceExam(Student student, Submission submission) {
    super(student, submission);
    if (submission.getFiles().length != 2) {
      throw new IllegalArgumentException("There should be 2 submission files " +
        "for multiple choice exams. Student \"" + student.getName() + "\" " +
        "passed " + submission.getFiles().length + ".");
    }
  }

  /**
   * Parses the Multiple Choice Exam's String representation
   * @see #parse(String)
   */
  public MultipleChoiceExam(String representation) {
    super(representation);
  }

  /**
   * Parses the representation if it matches the following pattern: <br>
   * <code>{answers file name}, {student name}, {student school class},
   * {YYYY-mm-dd}, {student file name}</code>
   * @param representation String containing the parameters as stated above
   * @throws IllegalArgumentException Amount of parameters is not valid
   */
  @Override
  public void parse(String representation) throws IllegalArgumentException {
    String[] parts = split(representation);
    if (parts.length != 5) {
      throw new IllegalArgumentException("Amount of parameters is not valid");
    }

    submission = new Submission(
      LocalDate.parse(parts[3]),
      new File(parts[0]),
      new File(parts[4])
    );
    student = new Student(parts[1], parts[2]);
  }

  /**
   * Compares the actual lines with the target lines in order to determine
   * a score.
   * @return Actual score, equal to amount of equal lines
   * @throws IOException File was not found or lines couldn't be read
   */
  @Override
  public int getActual() throws IOException {
    BufferedReader actualReader =
      new BufferedReader(new FileReader(submission.getFiles()[0]));

    BufferedReader targetReader =
      new BufferedReader(new FileReader(submission.getFiles()[1]));

    int points = 0;

    String targetLine, actualLine;
    String regex = "\\s*:\\s*";
    while ((targetLine = targetReader.readLine()) != null
      && !targetLine.isEmpty()
      && (actualLine = actualReader.readLine()) != null) {
      String[] targetParts = targetLine.split(regex);
      String[] actualParts = actualLine.split(regex);
      if (targetParts.length == 2
        && targetParts[0].equals(actualParts[0])
        && targetParts[1].equals(actualParts[1])) {
        points++;
      }
    }

    return points;
  }

  /**
   * Determines the target score based on the file's amount of lines
   * @return Target score, equal to the amount of lines
   * @throws FileNotFoundException File was not found
   */
  @Override
  public int getTarget() throws FileNotFoundException {
    BufferedReader reader =
      new BufferedReader(new FileReader(submission.getFiles()[1]));

    return (int) reader.lines().count();
  }

  @Override
  public String toString() {
    return String.format(
      "Multiple Choice: %s, %s, %s, %s, %s",
      submission.getFiles()[0].getName(),
      student.getName(),
      student.getSchoolClass(),
      submission.getDate().toString(),
      submission.getFiles()[1].getName()
    );
  }
}
