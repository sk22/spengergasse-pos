package at.spengergasse.kai17521.sem03.lab06;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author Samuel Kaiser
 * @since 10/24/2016
 */
public class Student {
  private String name;
  private String className;
  Set<Subject> subjects = new HashSet<>();

  /**
   * Constructs a Student based on the passed properties
   * @param name
   * @param className
   * @param subjects
   */
  public Student(String name, String className, Set<Subject> subjects) {
    setName(name);
    setClassName(className);
    setSubjects(subjects);
  }

  /**
   * Constructs a Student based on the given representation.
   * The representation must be in a format like the following,
   * while spacing between the delimiters does not matter:
   * <pre>
   *   John Doe - 1A - Programming: 1 Maths: 2 English: 3
   * </pre>
   * @param representation String representing the Student
   */
  public Student(String representation) {
    subjects = new HashSet<>();
    String[] parts = representation.split("\\s*-\\s*");
    if (parts.length < 2) {
      throw new IllegalArgumentException("Representation has too few parts. " +
        "At least name and class are required");
    }
    setName(parts[0]);
    setClassName(parts[1]);
    if (parts.length < 3) return;

    Pattern pattern = Pattern.compile("\\w.*?:\\s*\\d*", 0);
    Matcher matcher = pattern.matcher(parts[2]);

    while (matcher.find()) {
      parseSubject(matcher.group());
    }
  }

  /**
   * Parses a String containing the information about a Subject and adds it
   * into the subjects list
   * @param subject Subject string
   * @return Was the Subject parsed properly?
   */
  private boolean parseSubject(String subject) {
    String[] split = subject.split("\\s*:\\s*");
    if (split.length == 1) return false;
    subjects.add(new Subject(split[0], Integer.parseInt(split[1])));
    return true;
  }

  public String getName() {
    return this.name;
  }

  /**
   * Sets the Student's name. Requires at least two separate words.
   * @param name Student's name
   * @throws IllegalArgumentException Thrown if name consists of only one word
   *  or is null
   */
  public void setName(String name) throws IllegalArgumentException {
    if (name == null || name.split("\\s+").length < 2) {
      throw new IllegalArgumentException("Name must be a String and contain " +
        "a minimum of 2 words");
    }
    this.name = name;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) throws IllegalArgumentException {
    if (className == null || className.length() < 1) {
      throw new IllegalArgumentException("Class name must not be null and " +
        "have a length greater than 0");
    }
    this.className = className;
  }

  public Set<Subject> getSubjects() {
    return subjects;
  }

  /**
   * Sets the Student's subjects or clears the Set if null is passed
   * @param subjects Set of Subjects or null
   */
  public void setSubjects(Set<Subject> subjects) {
    if (subjects == null) {
      this.subjects.clear();
      return;
    }
    this.subjects = subjects;
  }

  /**
   * Returns a subset of all subjects in which the student was graded
   * @return Subset of graded subjects
   */
  public List<Subject> gradedSubjects() {
    return subjects.stream()
      .filter(subject -> subject.getGrade() != 0)
      .collect(Collectors.toList());
  }

  /**
   * Returns the average of all graded (grade != 0) Subjects' grades
   * @return Calculated average
   */
  public double average() {
    OptionalDouble od = gradedSubjects().stream()
      .mapToInt(Subject::getGrade)
      .average();
    return od.isPresent() ? od.getAsDouble() : 0;
  }

  /**
   * Returns a list of the Student's best subjects,
   * i.e. Subjects that have the Student's best grade.
   * @return Student's best Subjects
   */
  public List<Subject> bestSubjects() {
    OptionalInt oi = gradedSubjects().stream()
      .mapToInt(Subject::getGrade)
      .min();
    int min = oi.isPresent() ? oi.getAsInt() : 0;
    if (min == 0) return new ArrayList<>();
    return subjects.stream()
      .filter(subject -> subject.getGrade() == min)
      .collect(Collectors.toList());
  }

  /**
   * Removes a Subject by name
   * @param name Subject's name
   */
  public void removeSubject(String name) {
    subjects.removeIf(subject -> subject.getName().equals(name));
  }

  /**
   * Returns a String containing all Subjects' information
   * @return Generated String
   */
  public String joinGrades() {
    List<String> grades = subjects.stream()
      .map(subject -> subject.getName() + ": " + subject.getGrade())
      .collect(Collectors.toList());
    return String.join(" ", grades);
  }

  /**
   * Returns a String containing the Student's information incl. Subjects
   * @return Generated String
   */
  @Override
  public String toString() {
    return name + " - " + className +
      (subjects.size() > 0 ? " - " + joinGrades() : "");
  }
}
