package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import at.spengergasse.kai17521.sem03.the20161028.Taetigkeit;

public class TaetigkeitTest {
  @Test
  public void testeGueltigeTaetigkeit() {
    Taetigkeit t = new Taetigkeit("Programmdesign", 2000, 500);
    assertEquals("Programmdesign", t.getBeschreibung());
  }
  
  @Test (expected = IllegalArgumentException.class)
  public void testeUngueltigeTaetigkeit() {
    new Taetigkeit("Programmdesign", 100, 500);
  }
}
