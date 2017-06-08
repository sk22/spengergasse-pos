import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 1/10/2017
 */
public class SculptureTest {
  @Test
  public void transportCosts() throws Exception {
    Sculpture sculpture = new Sculpture("Sculptu", "John Though",
      LocalDate.now(), Sculpture.Material.PLASTER, 123, 321);
    assertEquals(674.1, sculpture.transportCosts(), 0.01);
  }

}