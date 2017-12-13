import java.util.Random;
/**
 * Methoden zum Prüfen der Funktionsweise von Arrays
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @since 22.02.2016
 * @version 2016-02-24
 */

public class Zahlenarray {
  private int[] zahlen;

  public Zahlenarray(int groesse) {
    this.zahlen = new int[Math.abs(groesse)];
  }

  public void init(int... wert) {
    for(int i=0, z=0; i<zahlen.length; i++, z++) {
      if(z==wert.length) z=0;
      zahlen[i] = wert[z];
    }
  }

  public void zuweisen(int index, int wert) {
    if(index<=this.zahlen.length) this.zahlen[index] = wert;
    else System.out.println("Ungültiger Index! Maximum: "+this.zahlen.length);
  }

  public void zufall(int min, int max) {
    if(min>max) { int x = max; max = min; min = x; }
    Random rand = new Random();
    for(int i=0; i<this.zahlen.length; i++) {
      int zahl = rand.nextInt(max-min+1) + min;
      this.zahlen[i] = zahl;
    }
  }

  public void print() {
    for(int i=0; i<this.zahlen.length; i++) {
      System.out.print(this.zahlen[i] + ((i == this.zahlen.length-1)?"":", "));
    } System.out.println();
  }

  public int maximum() {
    int max = Integer.MIN_VALUE;
    for(int i : this.zahlen) {
      if(i>max) max = i;
    } return max;
  }
  public int minimum() {
    int min = Integer.MAX_VALUE;
    for(int i : this.zahlen) {
      if(i<min) min = i;
    } return min;
  }

  public void ausgabe() {
    ausgabe(0, this.zahlen.length-1);
  }
  public void ausgabe(int min, int max) {
    min = Math.abs(min); max = Math.abs(max);
    if(min>max) { int x = max; max = min; min = x; }
    for(int i=min; i<this.zahlen.length && i<=max; i++)
      System.out.println("zahlen["+i+"] = "+this.zahlen[i]);
  }

  public static boolean istPrimzahl(int zahl) {
    boolean result = true;
    zahl = Math.abs(zahl);
    double wurzel = Math.sqrt(zahl);
    if(zahl<1) return false;
    for(int i=2; i<=wurzel; i++) {
      if(zahl%i == 0) {
        result = false;
        break;
      }
    }
    return result;
  }

  /**
   * @param max Definiert das Maximum des Wertes der zu errechnenden Primzahlen.
   *            -1 füllt das ganze Array mit Primzahlen.
   */
  public void berechnePrimzahlen(int max) {
    for(int i=0, z=0; ((max<0) || i<=max) && z<this.zahlen.length; i++) {
      if(istPrimzahl(i)) {
        this.zahlen[z] = i;
        z++;
      }
    }
  }

  /**
   * Testmethode
   */
  public static void main(String[] args) {
    Zahlenarray arr = new Zahlenarray(8);
    arr.init(2, 4, 8);
    System.out.println("Init 2, 4, 8:");
    arr.print();

    int randMin=5, randMax=6;
    System.out.println("Zufall (Minimum: "+randMin+", Maximum: "+randMax+"):");
    arr.zufall(randMin, randMax);
    arr.print();

    int i = 4, value = 8;
    System.out.println("Zuweisen: Index "+i+" -> "+value+":");
    arr.zuweisen(i, value);
    arr.print();

    System.out.println("Maximum: " + arr.maximum());
    System.out.println("Minimum: " + arr.minimum());

    System.out.println("Ausgabe:");
    arr.ausgabe();

    int outMin=3, outMax=6;
    System.out.println("Ausgabe (Minimum "+outMin+", Maximum "+outMax+"):");
    arr.ausgabe(outMin, outMax);

    int primBis=7;
    System.out.println("Primzahlen bis "+primBis);
    arr.init(0);
    arr.berechnePrimzahlen(primBis); arr.print();
  }
}
