package at.spengergasse.kai17521.sem03.lab08;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 11/29/2016
 */
public class ProviderTest {
  private Provider provider;

  @Before
  public void setUp() throws Exception {
    provider = new Provider();
    provider.add(new Phone(
      "John Doe", "0800 644 24 12",
      LocalDate.now().minus(Period.ofDays(2)), 5
    ));
  }

  @Test
  public void add() throws Exception {
    Phone jane = new Phone(
      "Jane Doe", "0800 644 24 13",
      LocalDate.now().minus(Period.ofDays(2)), 3
    );
    provider.add(jane);
    assertEquals(jane, provider.get(1));
  }

  @Test
  public void remove() throws Exception {
    provider.remove(0);
    assertEquals(0, provider.size());
  }

  @Test
  public void idRegistered() throws Exception {
    assertTrue(provider.idRegistered(0));
  }

  @Test
  public void load() throws Exception {
    provider.load("phones.txt");
  }

  @Test
  public void save() throws Exception {
    add();
    provider.save("phones.out.txt");
  }

  @Test
  public void toStringTest() throws Exception {
    add();
    System.out.println(provider.toString());
  }

  @Test
  public void expiredPhones() throws Exception {
    provider.add(new Phone(
      "Johnnie Dat", "0800 644 24 13",
      LocalDate.now().minus(Period.ofYears(3)), 4
    ));
    provider.add(new Phone(
      "Jane Dat", "0800 644 24 13",
      LocalDate.now().minus(Period.ofYears(3)), 2
    ));
    assertEquals(1, provider.expiredPhones().size());
  }
}