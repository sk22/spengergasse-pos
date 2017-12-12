package src;

 


public class Sternchen {
  private char zeichen;
  private char leer;
  private char[] plusminus;
  
  public Sternchen() {
    this.zeichen = '#';
    this.plusminus = new char[] {'+', '-'};
    this.leer = ' ';
  }

  
  public Sternchen zeichen(char zeichen) {
    this.zeichen = zeichen;
    return this;
  }
  public Sternchen leer(char leer) {
    this.leer = leer;
    return this;
  }
  public Sternchen plusminus(char[] plusminus) {
    this.plusminus = plusminus;
    return this;
  }
  
  public void zeichneZeile(int breite) {
    for (int i=0; i < breite; i++) {
      System.out.print(this.zeichen);
    }
    System.out.println();
  }
  
  public void zeichneRand(int breite) {
    for (int i=0; i<breite; i++) {
      if(i==0 || i==breite-1) System.out.print(this.zeichen);
      else System.out.print(this.leer);
    }
    System.out.println();
  }
  
  public void zeichnePlusMinus(int breite) {
    for (int i=0; i < breite; i++) {
      System.out.print((i%2 == 0) ? this.plusminus[0] : this.plusminus[1]);
    }
    System.out.println();
  }
  
  public void zeichneRechteckVoll(int hoehe, int breite) {
    for (int i=0; i<breite; i++) this.zeichneZeile(hoehe);
  }
  
  public void zeichneRechteckLeer(int hoehe, int breite) {
    for (int i=0; i<breite; i++) {
      if(i==0 || i==breite-1) this.zeichneZeile(hoehe);
      else this.zeichneRand(hoehe);
    }
  }
  
  public void zeichneRaute(int hoehe) {
    String abstand = "";
    for (int i=0; i<hoehe; i++, abstand+=" ") {
      System.out.print(abstand);
      if(i==0 || i==hoehe-1) this.zeichneZeile(hoehe);
      else this.zeichneRand(hoehe);
    }
  }
  
  public void zeichneDreieck(int hoehe) {
    int h = hoehe;
    for (int i=0; i<h; i++, hoehe--) {
      if(i==0 || i==hoehe-1) this.zeichneZeile(hoehe);
      else this.zeichneRand(hoehe);
    }
  }
  
  public void zeichneDreieckPlusMinus(int hoehe) {
    int h = hoehe; String abstand = "";
    for (int i=0; i<h; i++, hoehe--, abstand+=" ") {
      System.out.print(abstand);
      this.zeichnePlusMinus(hoehe);
    }
  }
  
  public void zeichneBaum(int hoehe) {
    Baum baum = new Baum(hoehe, this.zeichen);
    baum.generate();
  }

  public void zeichneQuadratischeFunktion(int anz) {
    int maxBreite = String.valueOf(anz).length();

    int zaehler;
    int length;
    for(zaehler = anz; zaehler > 0; --zaehler) {
      length = String.valueOf(zaehler).length();
      System.out.print(this.leerzeichen(maxBreite - length) + zaehler);
      System.out.print(this.leerzeichen(zaehler * zaehler - 1));
      System.out.println(this.zeichen);
    }

    zaehler = 0;
    System.out.print(this.leerzeichen(maxBreite - 1));

    for(length = 0; length < anz * anz + 1; ++length) {
      System.out.print(zaehler);
      ++zaehler;
      if(zaehler > 9) {
        zaehler = 0;
      }
    }

    System.out.println();
    length = 0;
    System.out.print(this.leerzeichen(maxBreite - 1));

    for(int i = 0; i < anz * anz + 1; ++i) {
      if(i % 10 == 0) {
        length = String.valueOf(i / 10).length() - 1;
        System.out.print(i / 10);
      } else if(length == 0) {
        System.out.print(" ");
      } else {
        --length;
      }
    }

  }
  
  public String leerzeichen(int anz) {
    String leerzeichen = "";
    for(int i=0; i<anz; i++) leerzeichen += " ";
    return leerzeichen;
  }
}
