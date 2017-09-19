package test;

import data.Student;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 09/16/2016
 */
public class StudentTest {
  private static Student stefan;
  private static Student basti;

  @org.junit.BeforeClass
  public static void setUp() throws Exception {
    stefan = new Student("Stefan");
    basti = new Student("Basti");
  }

  @org.junit.Test
  public void getName() throws Exception {
    assertTrue(stefan.getName().equals("Stefan"));
    assertTrue(basti.getName().equals("Basti"));
  }

  @org.junit.Test
  public void getId() throws Exception {
    assertEquals(stefan.getId(), 1);
    assertEquals(basti.getId(), 2);
  }

  @org.junit.Test
  public void testToString() throws Exception {
    System.out.println(stefan);
    System.out.println(basti);
  }

}