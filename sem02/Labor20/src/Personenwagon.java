
/**
 * Simulation der Anzahl der Menschen in einem Personenwagon
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @version 2016-05-04
 * @since 2015-11-04
 */
public class Personenwagon {
    private int maximum = 150;
    private int klasse = 2;
    private int belegt;
    
    public Personenwagon() {
        setMaximum(150);
        setKlasse(1);
    }
    
    public Personenwagon(int maximum, int klasse) {
        setMaximum(maximum);
        setKlasse(klasse);
    }
    
    /** Setze Maximum-Wert der möglichen Passagiere */
    public void setMaximum(int maximum) {
        //kein Defaultwert wenn das Attribut maximum 0 ist
        if(maximum>=100 && maximum<=200) {
            this.maximum = maximum;
        } else {
            System.out.println("Ungültiger Wert für Maximum!");
            System.out.println("Erlaubt sind alle ganzen Zahlen von 100 bis 200.");
            System.out.println("Wert für Maximum wurde nicht verändert: " + getMaximum() + "\n");
        }
    }
    public int getMaximum() {
        return this.maximum;
    }
    
    /** Setze Klasse des Passagierwagons */
    public void setKlasse(int klasse) {
        //kein Defaultwert wenn das Attribut klasse 0 ist
        if(klasse>=1 && klasse<=2) {
            this.klasse = klasse;
        } else {
            System.out.println("Klasse kann nur entweder 1 oder 2 sein.");
            System.out.println("Wert für Klasse wurde nicht verändert: " + getKlasse() + "\n");
        }
    }
    public int getKlasse() {
        return this.klasse;
    }
    
    /** Setze Anzahl der belegten Sitzplätze */
    public void setBelegt(int belegt) {
        if(belegt<=getMaximum() && belegt >= 0) {
            this.belegt = belegt;
        } else {
            System.out.println("Ungültiger Wert der Sitzplätze!");
            System.out.println("Wert muss mindestens 0 und maximal " + getMaximum() + " sein!\n");
        }
    }
    public int getBelegt() {
        return this.belegt;
    }
    
    public int getFrei() {
        return maximum-getBelegt();
    }
    
    /**
     * Bestimmte Anzahl von Menschen einsteigen lassen
     * @param anzahl Anzahl der einsteigenden Menschen
     * @return Anzahl der Menschen, die keinen Platz gefunden haben
     */
    public int einsteigen(int anzahl) {
        if(anzahl>0) {
            if(getBelegt()+anzahl<=getMaximum()) {
                setBelegt(getBelegt()+anzahl);
                return 0;
            } else {
                int verbleibend = getBelegt()+anzahl-getMaximum();
                setBelegt(getMaximum());
                return verbleibend;
            }
        } else {
            System.out.println("Ungültige Anzahl der einsteigenden Personen!\n");
            return 0;
        }
    }
    
    /**
     * Bestimmte Anzahl von Menschen aussteigen lassen
     * @param anzahl Anzahl der aussteigenden Menschen
     * @return Anzahl der tatsächlich ausgestiegenen Menschen
     */
    public int aussteigen(int anzahl) {
        if(anzahl>0) {
            if(anzahl<=getBelegt()) {
                setBelegt(getBelegt()-anzahl);
                return anzahl;
            } else {
                int aussteigend = getBelegt();
                setBelegt(0);
                return aussteigend;
            }
        } else {
            System.out.println("Ungültige Anzahl der aussteigenden Personen!\n");
            return 0;
        }
    }
}
