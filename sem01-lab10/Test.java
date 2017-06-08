/**
 * @author Kaiser Samuel <kai17521@spengergasse.at> (Original: Gerald Schildberger)
 * @version 2015-12-02
 */
public class Test {
    public void testen() {
        // Die Referenzvariable taxi1 erhält einen Verweis (=Referenz) auf ein neu erzeugtes Objekt
        Taxi taxi1 = new Taxi();
        // . bedeutet: gehe zum referenzierten Objekt (zum zugewiesenen Objekt)
        //   und dort kann man öffentliche Methoden aufrufen
        taxi1.einsteigen(2);
        taxi1.printInfo();
        
        System.out.println("--------------");
        
        // Erzeuge ein Taxiunternehmen mit dem taxi1 zugeordneten Taxi ...
        Taxiunternehmen txu = new Taxiunternehmen(taxi1);
        // ... und gibt die Daten des Taxiunternehmens aus
        txu.printInfo();
    }  
    
    public void testeTaxi() {
        // Teste hier die einsteigen- und aussteigen-Methoden der Klasse Taxi
        
        System.out.println("Taxi mit 30 Plätzen.");
        System.out.println("====================");
        Taxi testtaxi = new Taxi(30);
        testtaxi.printInfo();
        System.out.println();
                
        System.out.println("120 Personen steigen ein.");
        System.out.println("=========================");
        testtaxi.einsteigen(120);
        testtaxi.printInfo();
        System.out.println();
        
        System.out.println("80 Personen steigen aus.");
        System.out.println("========================");
        testtaxi.aussteigen(80);
        testtaxi.printInfo();
        System.out.println();
        
    }    
        
    public void testeTaxiunternehmen() {        
        System.out.println("Taxiuntern null übergeben.");
        System.out.println("==========================");
        Taxiunternehmen txu = new Taxiunternehmen(null);
        txu.printInfo();
        System.out.println();
        
        System.out.println("Neues Taxi mit 10 Plätzen.");
        System.out.println("==========================");
        txu.taxiHinzufuegen(new Taxi(10));
        txu.printInfo();
        System.out.println();
        
        System.out.println("Neues Taxi mit 80 Plätzen.");
        System.out.println("==========================");
        txu.taxiHinzufuegen(new Taxi(80));
        txu.printInfo();
        System.out.println();
        
        System.out.println();
    }
}
