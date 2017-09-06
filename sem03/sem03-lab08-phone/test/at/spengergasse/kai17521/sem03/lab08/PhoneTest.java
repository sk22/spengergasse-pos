package at.spengergasse.kai17521.sem03.lab08;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 11/28/2016
 */
public class PhoneTest {
  private Phone phone;

  @Before
  public void setUp() throws Exception {
    phone = new Phone(
      "John Doe", "0800 644 24 12",
      LocalDate.now(), 5
    );
  }

  @Test (expected = IllegalArgumentException.class)
  public void setOwnerNull() throws Exception {
    phone.setOwner(null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void setOwnerEmpty() throws Exception {
    phone.setOwner("");
  }

  @Test
  public void setOwner() throws Exception {
    phone.setOwner("Jane Doe");
    assertEquals("Jane Doe", phone.getOwner());
  }


  @Test (expected = IllegalArgumentException.class)
  public void setNumberInvalid() throws Exception {
    phone.setNumber("asdf");
  }

  @Test
  public void setNumber() throws Exception {
    phone.setNumber("+43 123 321123");
    assertEquals("+43 123 321123", phone.getNumber());
  }

  @Test
  public void setPurchaseDate() throws Exception {
    phone.setPurchaseDate(LocalDate.now());
    assertEquals(LocalDate.now(), phone.getPurchaseDate());
  }

  @Test (expected = IllegalArgumentException.class)
  public void setDurationInvalid() throws Exception {
    phone.setDuration(0);
  }

  @Test
  public void setDuration() throws Exception {
    phone.setDuration(3);
    assertEquals(3, phone.getDuration());
  }

  @Test
  public void sincePurchase() throws Exception {
    assertEquals(0, phone.sincePurchase().getDays());
  }

  @Test
  public void timeLeft() throws Exception {
    assertEquals(Period.ofYears(5), phone.timeLeft());
  }

  @Test
  public void parse() throws Exception {
    Phone phone = Phone.parse(
      "\"Dr. Hans Müller\";\"+43 (0)1 798 40 98\";2015/10/10;\"gesperrt\";2"
    );
    assertEquals("Dr. Hans Müller", phone.getOwner());
    assertEquals("+43 (0)1 798 40 98", phone.getNumber());
    assertEquals(LocalDate.of(2015, 10, 10), phone.getPurchaseDate());
    assertTrue(phone.isLocked());
    assertEquals(2, phone.getDuration());
  }

  @Test
  public void toStringTest() throws Exception {
    System.out.println(phone);
  }

}