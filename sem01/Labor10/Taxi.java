/**
 * @author Kaiser Samuel <kai17521@spengergasse.at> (Original: Gerald Schildberger)
 * @version 2015-12-02
 */
public class Taxi {
    private boolean frei;     // Taxi verfügbar
    private int fahrgaeste;
    private int maxPlatz;
    
    public Taxi() {        
        setFrei(true);
        setMaxPlatz(3);      // Reihenfolge beachten!
        setFahrgaeste(0);    //
    }

    public Taxi(int maxPlatz) {        
        setFrei(true);
        setMaxPlatz(maxPlatz);   // Reihenfolge beachten!
        setFahrgaeste(0);        //
    }

    public void setFrei(boolean frei) {
        this.frei=frei;
        if (frei/*==true*/) fahrgaeste = 0;
    }
    
    public void setFahrgaeste(int fahrgaeste) {
        if (fahrgaeste<0 || fahrgaeste>maxPlatz) { // Fehlerfall: 
            System.out.println("ungültiger Wert für fahrgaeste");   
        } else {                                   // alles ok:
            this.fahrgaeste = fahrgaeste;
        }
        frei = fahrgaeste==0;
    }
    
    public int getFahrgaeste() {
        return fahrgaeste;
    }
    
    // private, wird nur vom Konstruktor verwendet, 
    // spätere Änderungen von "außen" nicht möglich
    private void setMaxPlatz(int maxPlatz) {
        if (maxPlatz>=1 && maxPlatz<=10) {
            this.maxPlatz = maxPlatz;
        } else {
            this.maxPlatz = 3;
            System.out.println("Wert für Sitzplätze ungültig -> Default-Wert " + this.maxPlatz);
        }
    }
    
    public void einsteigen(int anz)
    {
        if (anz > 0) {
            if (anz <= freiePlaetze()) {
                fahrgaeste += anz;
            } else {
                System.out.println("nicht Platz für: " + (anz-freiePlaetze()) + " Person(en)");
                fahrgaeste = maxPlatz;
            }
            frei = fahrgaeste==0;
        }
        else
        {
            System.out.println("ungültiger Wert für Anzahl");
        }
    }    
    
    public void aussteigen(int anz)
    {
        if (anz<=0) {
            System.out.println("ungültiger Wert für Anzahl");
        } else {
            if (anz>fahrgaeste) {
                fahrgaeste = 0;
            } else {
                fahrgaeste -= anz;
            }
            frei = fahrgaeste==0;
        }
    }  
    
    public void printInfo() {
        System.out.println("Taxi frei: " + (frei?"ja":"nein"));
        System.out.println("Anzahl der Fahrgäste: " + fahrgaeste);
        System.out.println("maximale Anzahl der Fahrgäste: " + maxPlatz);
    }
    
    public int freiePlaetze() {
        return maxPlatz-fahrgaeste;
    }
}
