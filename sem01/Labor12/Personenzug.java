
/**
 * Simulation des Management der Wagons an einem Personenzug
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @version 2015-12-16
 * @since 2015-12-16
 */
public class Personenzug
{
    private Personenwagon wagon1;
    private Personenwagon wagon2;
    private Personenwagon wagon3;
    
    public Personenzug(Personenwagon wagon) {
        wagonHinzufuegen(wagon);
    }
    
    public void wagonHinzufuegen(Personenwagon wagon) {
        if(wagon == null) {
            wagon = new Personenwagon();
        }
             if(wagon1 == null && wagon != wagon1) wagon1 = wagon;
        else if(wagon2 == null && wagon != wagon2) wagon2 = wagon;
        else if(wagon3 == null && wagon != wagon3) wagon3 = wagon;
        else System.out.println("Kein Platz mehr für weitere Wagons!");
    }
    
    public int getAnzahlFreieSitzplaetze(int klasse) {
        int anzahl = 0;
        switch(klasse) {
            case 0:
                if(wagon1!=null) anzahl += wagon1.getFrei();
                if(wagon2!=null) anzahl += wagon2.getFrei();
                if(wagon3!=null) anzahl += wagon3.getFrei();
                break;
                
            case 1:
                if(wagon1!=null && wagon1.getKlasse() == 1)
                    anzahl += wagon1.getFrei();
                if(wagon2!=null && wagon1.getKlasse() == 1)
                    anzahl += wagon2.getFrei();
                if(wagon3!=null && wagon1.getKlasse() == 1)
                    anzahl += wagon3.getFrei();
                break;
                
            case 2:
                if(wagon1!=null && wagon1.getKlasse() == 2)
                    anzahl += wagon1.getFrei();
                if(wagon2!=null && wagon1.getKlasse() == 2)
                    anzahl += wagon2.getFrei();
                if(wagon3!=null && wagon1.getKlasse() == 2)
                    anzahl += wagon3.getFrei();
                break;  
        }
        return anzahl;
    }
    
    /**
     * Lässt Personen einsteigen.
     * @param anzahl Anzahl der einsteigenden Menschen
     * @param klasse Klasse der Wagons, in die die Personen einsteigen sollen. 0 -> Beliebige Klasse
     */
    public int verteilen(int anzahl, int klasse) {
        if(anzahl>0) {
            if(klasse == 0) {
                if(wagon1!=null) anzahl = wagon1.einsteigen(anzahl);
                if(wagon2!=null) anzahl = wagon2.einsteigen(anzahl);
                if(wagon3!=null) anzahl = wagon3.einsteigen(anzahl);
            } else {
                if(wagon1!=null && wagon1.getKlasse() == klasse) anzahl = wagon1.einsteigen(anzahl);
                if(wagon2!=null && wagon2.getKlasse() == klasse) anzahl = wagon2.einsteigen(anzahl);
                if(wagon3!=null && wagon3.getKlasse() == klasse) anzahl = wagon3.einsteigen(anzahl);
                if(klasse>2 && klasse<0) System.out.println("Ungültige Klasse! Es gibt nur Klasse 1 und 2. Klasse 0, um alle Klassen zu akzepzieren.");
            }
        }
        return anzahl;
    }
    
    
    public int getAnzahlWagons() {
        int anzahl = 0;
        if(wagon1!=null) anzahl++;
        if(wagon2!=null) anzahl++;
        if(wagon3!=null) anzahl++;
        return anzahl;
    }
    
    public void printInfo() {
        String text1 = "", text2 = "", text3 = "";
        String text = ""
         + "Anzahl Wagons: " + getAnzahlWagons()+"\n"
         + "-=-=-=-=-=-=-=-=-\n";
        if(wagon1!=null) {
            text1 = ""
             + "Wagon 1:\n"
             + "  - Klasse: " + wagon1.getKlasse() + "\n"
             + "  - Sitzplätze: " + wagon1.getMaximum() + "\n"
             + "    davon: " + wagon1.getFrei() + " frei, "
             + wagon1.getBelegt() + " belegt\n";
        }
        if(wagon2!=null) {
            text2 = ""
             + "Wagon 2:\n"
             + "  - Klasse: " + wagon2.getKlasse() + "\n"
             + "  - Sitzplätze: " + wagon2.getMaximum() + "\n"
             + "    davon: " + wagon2.getFrei() + " frei, "
             + wagon2.getBelegt() + " belegt\n";
        }
        if(wagon3!=null){
            text3 = ""
             + "Wagon 3:\n"
             + "  - Klasse: " + wagon3.getKlasse() + "\n"
             + "  - Sitzplätze: " + wagon3.getMaximum() + "\n"
             + "    davon: " + wagon3.getFrei() + " frei, "
             + wagon3.getBelegt() + " belegt\n";
        }
        
        System.out.println(text+text1+text2+text3);
    }
}











