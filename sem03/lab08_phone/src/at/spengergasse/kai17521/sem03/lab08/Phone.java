package at.spengergasse.kai17521.sem03.lab08;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

/**
 * @author Samuel Kaiser
 * @since 11/28/2016
 */
public class Phone {
  private static DateTimeFormatter DATE_FORMATTER =
    DateTimeFormatter.ofPattern("yyyy/MM/dd");
  private String owner;
  private String number;
  private LocalDate purchaseDate;
  private boolean locked;
  private int duration;

  /**
   *
   * @param owner Phone's owner
   * @param number Phone number
   * @param purchaseDate Date the phone was purchased on
   * @param duration Duration for the tariff to be active
   */
  public Phone(String owner, String number, LocalDate purchaseDate,
               int duration) {
    this(owner, number, purchaseDate, false, duration);
  }

  /**
   * Constructor for the parse method using Strings
   */
  private Phone(String owner, String number, String purchaseDate, String locked,
        String duration) {
    setOwner(owner);
    setNumber(number);
    setPurchaseDate(purchaseDate);
    setLocked(locked);
    setDuration(duration);
  }

  /**
   * Constructor with the locked attribute
   */
  private Phone(String owner, String number, LocalDate purchaseDate,
                boolean locked, int duration) {
    setOwner(owner);
    setNumber(number);
    setPurchaseDate(purchaseDate);
    setLocked(locked);
    setDuration(duration);
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    if (owner == null || owner.isEmpty()) {
      throw new IllegalArgumentException("Owner must not be empty");
    }
    this.owner = owner;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    if (number == null) {
      throw new IllegalArgumentException("Number must not be null");
    }
    // can contain digit, whitespace, parentheses, plus
    if (!number.matches("^\\+?[\\d\\s()]{9,}$")) {
      throw new IllegalArgumentException("Number must have at least 9 digits " +
        "and contain only digits, whitespaces, parentheses, and either a " +
        "plus sign or a leading 0");
    }
    this.number = number;
  }

  public LocalDate getPurchaseDate() {
    return purchaseDate;
  }

  public void setPurchaseDate(LocalDate purchaseDate) {
    this.purchaseDate = purchaseDate;
  }

  private void setPurchaseDate(String purchaseDate) {
    try {
      setPurchaseDate(
        LocalDate.parse(purchaseDate, Phone.DATE_FORMATTER)
      );
    } catch (DateTimeParseException e) {
      throw new IllegalArgumentException("Purchase Date could not be parsed. " +
        "Use the \"" + DATE_FORMATTER.toString() + "\" format");
    }
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  private void setLocked(String locked) {
    setLocked(Boolean.parseBoolean(locked)
      || Arrays.asList("locked", "gesperrt").contains(locked));
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    if (duration <= 0) {
      throw new IllegalArgumentException("Duration must be greater than 0");
    }
    this.duration = duration;
  }

  private void setDuration(String duration) {
    try {
      setDuration(Integer.parseInt(duration));
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Duration must be a valid integer");
    }
  }

  public Period sincePurchase() {
    return Period.between(purchaseDate, LocalDate.now());
  }

  public Period timeLeft() {
    return LocalDate.now().until(purchaseDate.plus(Period.ofYears(duration)));
  }

  @Override
  public String toString() {
    return "\"" + owner + "\"; " +
      "\"" + number + "\"; " +
      purchaseDate.format(Phone.DATE_FORMATTER) + "; " +
      (locked ? "locked" : "unlocked") + "; " +
      duration;
  }

  public static Phone parse(String representation) {
    // `"asdf"; "asdf"` = `"asdf";"asdf"` = `"asdf  "  ; asdf`
    String[] parts = representation.split("^\"|\\s*\"?\\s*;\\s*\"?\\s*|\"$");

    // "Dr. Hans Müller";"+43 (0)1 798 40 98";2015/10/10;"gesperrt";2
    return new Phone(
      parts[1], // "Dr. Hans Müller"
      parts[2], // "+43 (0)1 798 40 98"
      parts[3],
      parts[4],
      parts[5]
    );
  }

  public static void setDateFormatter(DateTimeFormatter formatter) {
    DATE_FORMATTER = formatter;
  }
}
