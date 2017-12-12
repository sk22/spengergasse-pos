/**
 * @author Kaiser Samuel <kai17521@spengergasse.at> (Original: Gerald Schildberger)
 * @version 2015-12-02
 */
public class Taxiunternehmen
{   
    private Taxi t1;      // t1 ... Referenz / Verweis auf ein Taxi-Objekt
    private Taxi t2;      // t2 ... Referenz / Verweis auf ein Taxi-Objekt
    private Taxi t3;
    
    public Taxiunternehmen()
    {
        t1 = new Taxi();   // erzeugt ein Taxi mit den Standardwerten und weist es t1 zu
        t2 = new Taxi(9);  // erzeugt ein Taxi für 9 Fahrgäste und weist es t2 zu
        t3 = new Taxi(5);
    }
    
    public Taxiunternehmen(Taxi taxi)
    {
        // wenn eine Null-Referenz übergeben wird, erzeuge ein Standardtaxi für t1
        if (taxi == null) {
            System.out.println("Null übergeben: Standardtaxi wird verwendet.");
            t1 = new Taxi();
        } else {
            t1 = taxi;
        }
        // Achtung!  t2 erhält kein Objekt zugewiesen, d.h. bleibt null (Nullreferenz)
    }
    
    public void printInfo()
    {
        // t1 ist in diesem Beispiel IMMER ein Objekt zugewiesen, daher hier keine Prüfung
        System.out.println("Taxis: " + anzahlTaxis());
        System.out.println("Freie Plätze: " + freiePlaetze());
        System.out.println();
        System.out.println("Taxi 1: ");
        t1.printInfo();
        // t2 kann eine Nullreferenz enthalten, daher Prüfung erforderlich, 
        // sonst riskiert man eine NullPointerException und einen Programmabbruch!
        System.out.println("Taxi 2: ");
        if (t2!=null) t2.printInfo(); else System.out.println("Es gibt kein zweites Taxi!");
        System.out.println("Taxi 3: ");
        if (t3!=null) t3.printInfo(); else System.out.println("Es gibt kein drittes Taxi!");
    }

    // Weitere Methoden / Aufgaben:
    
    // public int anzahlTaxis()  ... Wieviele Taxis hat das Unternehmen aktuell?  
    public int anzahlTaxis() {
        return (t1 == null && t2 == null && t3 == null)
            ? 0 : ((t2 == null && t3 == null)
            ? 1 : ((t3 == null)
            ? 2 : 3));
    }
    // public int freiePlaetze() ... Wieviele Personen könnten mit allen freien Taxis transportiert werden?
    public int freiePlaetze() {
        return ((t1 != null) ? t1.freiePlaetze() : 0)
             + ((t2 != null) ? t2.freiePlaetze() : 0)
             + ((t3 != null) ? t3.freiePlaetze() : 0);
        /* if (t1 != null && t2 != null) return t1.freiePlaetze() + t2.freiePlaetze();
        else if (t1 != null && t2 == null) return t1.freiePlaetze();
        else return 0; */
    }
    // public void taxiHinzufuegen(Taxi taxi) ... füge ein Taxi hinzu (nur wenn t2 noch nicht belegt ist)
    public void taxiHinzufuegen(Taxi taxi) {
             if (t1 == null) t1 = taxi;
        else if (t2 == null) t2 = taxi;
        else if (t3 == null) t3 = taxi;
    }
    // Erweitere das Taxiunternehmen so, dass ein drittes Taxi hinzugefügt werden kann.
}
