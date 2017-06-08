package at.spengergasse.kai17521.sem04.the20160310.data;

import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Comparator;

/**
 * @author Samuel Kaiser
 * @since 3/10/2017
 */
public class Contact implements Serializable {
  private static int basedOnNull(Object o1, Object o2) {
    if (o1 == null) return 1;
    if (o2 == null) return -1;
    return 0;
  }

  public static final Comparator<Contact> SORT_BY_NAME = (c1, c2) -> (
    c1.getName() == null || c2.getName() == null
      ? basedOnNull(c1.getName(), c2.getName())
      : c2.getName().compareTo(c1.getName())
  );
  public static final Comparator<Contact> SORT_BY_RELATION = (c1, c2) -> (
    c1.getRelation() == null || c2.getRelation() == null
      ? basedOnNull(c1.getRelation(), c2.getRelation())
      : c2.getRelation().compareTo(c1.getRelation())
  );

  public static final Comparator<Contact> SORT_BY_DATE_OF_BIRTH = (c1, c2) -> (
    c1.getDateOfBirth() == null || c2.getDateOfBirth() == null
      ? basedOnNull(c1.getDateOfBirth(), c2.getDateOfBirth())
      : c2.getDateOfBirth().compareTo(c1.getDateOfBirth())
  );

  private String name;
  private Gender gender;
  private Relation relation;
  private LocalDate dateOfBirth;

  public Contact(
    String name, Gender gender, Relation relation, LocalDate dateOfBirth
  ) {
    this.name = name;
    this.gender = gender;
    this.relation = relation;
    this.dateOfBirth = dateOfBirth;
  }

  public String getName() {
    return name;
  }

  public Gender getGender() {
    return gender;
  }

  public Relation getRelation() {
    return relation;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  @Override
  public String toString() {
    return "Name: " + name + '\n' +
      (gender == null ? "" : "Gender: " + gender + '\n') +
      (relation == null ? "" : "Relation: " + relation + '\n') +
      (dateOfBirth == null ? "" : "Date of Birth: " + dateOfBirth);
  }
}
