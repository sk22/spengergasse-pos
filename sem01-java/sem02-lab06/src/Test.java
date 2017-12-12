
/**
 * Testet die Klassen Personenzug und Personenwagon
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @version 2016-05-04
 * @since 2015-12-16
 */
public class Test {
  public static void main(String[] args) {
    Personenzug zug = new Personenzug(4);
    zug.wagonHinzufuegen(new Personenwagon(100, 2));
    zug.wagonHinzufuegen(new Personenwagon(60, 1));
    for(int i=0; i<6; i++) zug.wagonHinzufuegen(new Personenwagon(120, 2));
    zug.printInfo();

    System.out.println("Entferne Wagon #0");
    zug.entferneWagon(0);
    zug.printInfo();

    System.out.println("\nVerteile 150 Personen auf 2.-Klasse-Züge");
    System.out.println("Übrigbleibende Personen: " + zug.verteilen(1200, 2) + "\n");

    System.out.println("Freie Sitzplätze: "+zug.getAnzahlFreieSitzplaetze(0));
    zug.printInfo();

    System.out.println("Verteile 430 weitere Personen auf beliebige Wagons. Nicht Platz gefunden: "
      +zug.verteilen(430, 0));

    zug.printInfo();
  }
}
