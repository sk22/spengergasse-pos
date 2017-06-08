
/**
 * Simulation eines Kopierers
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @since 2016-01-13
 * @version 2016-01-20
 */
public class Kopierer {
    private int papierMaximum;
    private int papier;
    private boolean defekt;
    
    public Kopierer() {
        this(500, 200, false);
    }
    public Kopierer(int papierMaximum, int papier, boolean defekt) {
        setPapierMaximum(papierMaximum);
        setPapier(papier);
        setDefekt(defekt);
    }
    
    public int getPapierMaximum() {
        return this.papierMaximum;
    }
    private void setPapierMaximum(int papierMaximum) {
// fehlt: setPapiermaximum soll nur beim ersten Aufruf den Wert ändern können 
// Lösung: entweder diese Methode private machen - wird ohnehin nur im Konstruktor benötigt oder
//         den (noch nicht) gesetzten Wert abfragen       
        if(papierMaximum<=2000 && papierMaximum>=500) {
            this.papierMaximum = papierMaximum;
        } else {
            Kopierer standard = new Kopierer();
            System.out.println("Ungültiger Wert für Papier-Maximum! (Erlaubt: 500 bis 2000)");
            System.out.println("Standardwert: " + standard.getPapierMaximum() + "\n");
            this.papierMaximum = standard.getPapierMaximum();
        }
    }
    
    public int getPapier() {
        return this.papier;
    }
    
    public void setPapier(int papier) {
        if(papier<=papierMaximum && papier>=0) {
            this.papier = papier;
        } else {
            Kopierer standard = new Kopierer();
            System.out.println("Ungültiger Wert für Papier! (Erlaubt: 0 bis Papier-Maximum)");
            System.out.println("Standardwert: " + standard.getPapier() + "\n");
            this.papier = standard.getPapier();
        }
    }
    
    public boolean getDefekt() {
        return this.defekt;
    }
    public void setDefekt(boolean defekt) {
        this.defekt = defekt;
    }
    
    public void kopieren(int anzahl) {
        if(this.papier-anzahl >= 0 && !this.defekt && anzahl>0) {
            setPapier(this.papier-anzahl);
            System.out.println("Es wird kopiert...\n");
        } else {
            System.out.print("Fehler aufgetreten: ");
                 if(defekt)               System.out.println("Kopierer defekt");
            else if(this.papier-anzahl<0) System.out.println("Kann nicht " + anzahl + " Seiten drucken: Zu wenig Papier vorhanden!");
            else if(anzahl<=0)            System.out.println("Ungültige Anzahl!");
            System.out.println();
        }
    }
    
    public int papierNachfuehllen(int blatt) {
        if(blatt<0) {
            System.out.println("Ungültiger Wert für Blatt-Anzahl!\n");
            return 0;
        }
        if(this.papier+blatt < this.papierMaximum) {
            setPapier(this.papier+blatt);
            return this.papier+blatt;
        } else {
            int uebrig = this.papier+blatt - this.papierMaximum;
            setPapier(this.papierMaximum);
// einfach auf Maximum setzen (papier + blatt - uebrig = papierMaximum)            
            return blatt-uebrig;
// es sollte die tatsächlich nachgefüllte Menge returniert werden, nicht die übrig gebliebene            
        }
    }
    
    public void printInfo() {
        System.out.println("maximaler Papiervorrat: " +this.papierMaximum);
        System.out.println("aktueller Papiervorrat: " +this.papier
            + " (" + (int)((float)this.papier / this.papierMaximum * 100) + "%)");
        System.out.println("defekt: " + (defekt?"ja":"nein"));
        System.out.println();
    }
}
