package at.spengergasse.kai17521.sem03.lab08;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 12/2/2016
 */
public class ParseAndWriteTest {
  @Test
  public void test() {
    Provider provider = new Provider();
    provider.load("phones.txt");
    provider.save("phones.out.txt");
  }
}