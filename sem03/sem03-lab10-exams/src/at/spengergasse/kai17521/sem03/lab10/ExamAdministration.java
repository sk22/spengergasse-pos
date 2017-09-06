package at.spengergasse.kai17521.sem03.lab10;

import at.spengergasse.kai17521.sem03.lab10.exam.Exam;
import at.spengergasse.kai17521.sem03.lab10.exam.GermanExam;
import at.spengergasse.kai17521.sem03.lab10.exam.MultipleChoiceExam;

import java.io.*;
import java.lang.reflect.Constructor;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Samuel Kaiser
 * @since 12/16/2016
 */
public class ExamAdministration extends HashSet<Exam> {
  public List<Exam> getExamsByStudent(Student student) {
    return stream()
      .filter(e -> e.getStudent().equals(student))
      .collect(Collectors.toList());
  }

  public int add(String entry) {
    String[] parts = entry.split("\\s*:\\s*");
    if (parts.length < 2) {
      throw new IllegalArgumentException("The entry must have a " +
        "subject name and a content, delimeted by a colon. E.g. German: ...");
    }
    String className = parts[0].toLowerCase();
    try {
      Constructor<? extends Exam> constructor = Exam.getClassByName(className)
        .getDeclaredConstructor(String.class);
      Exam exam = constructor.newInstance(parts[1]);
      add(exam);
    } catch (ReflectiveOperationException e) {
      return 0;
    } catch (NullPointerException npe) {
      return 0;
    }
    return 1;
  }

  /**
   * Creates a multiline string containing all Exam's representations
   * @return All Exams represented by a String
   */
  @Override
  public String toString() {
    Optional<String> optional = stream()
      .map(Exam::toString)
      .reduce((a, b) -> a + "\n" + b);
    return optional.isPresent() ? optional.get() : "";
  }

  /**
   * Loads Exams from a file into the object
   * @param file File to be loaded from
   * @return Amount of loaded entries
   * @throws IOException Could not load from the file
   */
  public int load(File file) throws IOException {
    BufferedReader reader = new BufferedReader(new FileReader(file));
    return reader.lines().mapToInt(this::add).sum();
  }

  /**
   * Writes the Exams into the given file
   * @param file File to be written to
   * @return Amount of saved entries
   * @throws IOException Could not write to the file
   */
  public int save(File file) throws IOException {
    PrintWriter writer = new PrintWriter(new FileWriter(file));
    stream().map(Exam::toString).forEach(writer::println);
    writer.close();
    return size();
  }

  /**
   * @return List of Exams sorted by the {@link Submission}'s date field
   */
  public List<Exam> sortedByDate() {
    List<Exam> list = new ArrayList<>(this);
    list.sort((o1, o2) -> o1.getSubmission().getDate()
      .compareTo(o2.getSubmission().getDate()));
    return list;
  }

  /**
   * @return List of Exams sorted by the {@link Student}'s school class field
   */
  public List<Exam> sortedBySchoolClass() {
    List<Exam> list = new ArrayList<>(this);
    list.sort((o1, o2) -> o1.getStudent().getSchoolClass()
      .compareTo(o2.getStudent().getSchoolClass()));
    return list;
  }
}
