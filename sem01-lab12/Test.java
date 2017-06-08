
/**
 * Testet die Klassen Personenzug und Personenwagon
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @version 2015-12-16
 * @since 2015-12-16
 */
public class Test {
    public static void main(String[] args) {
        Personenzug zug = new Personenzug(null);
        zug.wagonHinzufuegen(new Personenwagon(100, 2));
        zug.wagonHinzufuegen(new Personenwagon(60, 2));
        zug.printInfo();
        
        System.out.println("\nVerteile 150 Personen auf 2.-Klasse-Züge");
        System.out.println("Übrigbleibende Personen: " + zug.verteilen(150, 2) + "\n");
        
        zug.printInfo();
    }
}
