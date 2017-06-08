
/**
 * Klasse zur Berechnung von Zeiten
 * 
 * @author  Kaiser Samuel <kai17521@spengergasse.at>
 * @version 11.11.2015
 * @since   11.11.2015
 */
public class Zeit
{
    private int stunde;
    private int minute;
    private int sekunde;
    private int tag;
    
    /** 
     * Objekt wird mit Standardwerten instanziert. (12:00:00)
     */
    public Zeit() {
        stunde  = 12;
        minute  = 0;
        sekunde = 0;
    }
    /** 
     * Objekt mit direkt gesetzten Werten für Stunde, Minute, Sekunde wird instanziert.
     * 
     * @param stunde  Wert für Stunde
     * @param minute  Wert für Minute
     * @param sekunde Wert für Sekunde
     */
    public Zeit(int stunde, int minute, int sekunde) {
        setStunde(stunde);
        setMinute(minute);
        setSekunde(sekunde);
    }
    /**
     * Objekt mit Zeit per gesetzter Sekundenanzahl wird instanziert.
     */
    public Zeit(int sekunden) {
        addiereSekunden(sekunden);
    }
    
    public int getStunde() {
        return this.stunde;
    }
    public int getMinute() {
        return this.minute;
    }
    public int getSekunde() {
        return this.sekunde;
    }
    
    /**
     * Setzt Anzahl der Stunde: Alle ganzen Zahlen von 0 bis 23 sind erlaubt.
     */
    public void setStunde(int stunde) {
        if(stunde >= 0 && stunde < 24) {
            this.stunde = stunde;
        } else {
            System.out.println("Ungültiger Wert:");
            System.out.println("Gültig ist jede ganze Zahl von 0 bis 23!\n\r");
        }
    }
    /**
     * Setzt Anzahl der Minute: Alle ganzen Zahlen von 0 bis 60 sind erlaubt.
     */
    public void setMinute(int minute) {
        if(minute >= 0 && minute < 60) {
            this.minute = minute;
        } else {
            System.out.println("Ungültiger Wert:");
            System.out.println("Gültig ist jede ganze Zahl von 0 bis 59!\n\r");
        }
    }
    /**
     * Setzt Anzahl der Sekunde: Alle ganzen Zahlen von 0 bis 60 sind erlaubt.
     */
    public void setSekunde(int sekunde) {
        if(sekunde >= 0 && sekunde < 60) {
            this.sekunde = sekunde;
        } else {
            System.out.println("Ungültiger Wert:");
            System.out.println("Gültig ist jede ganze Zahl von 0 bis 59!\n\r");
        }
    }
    
    /**
     * Addiert Stundenanzahl zur aktuellen Zeit.
     */
    public void addiereStunden(int anzahl) {
        if(anzahl+stunde < 24 && anzahl > 0) {
            stunde += anzahl;
        } else if(anzahl>0) {
            stunde = (stunde+anzahl) % 24;
            tag += anzahl / 24;
        } else {
            System.out.println("Negative Werte werden nicht unterstützt!\n\r");
        }
    }
    /**
     * Addiert Minutenanzahl zur aktuellen Zeit.
     */
    public void addiereMinuten(int anzahl) {
        if(anzahl+minute < 60 && anzahl > 0) {
            minute += anzahl;
        } else if(anzahl>0) {
            addiereStunden((anzahl+minute)/60);
            minute = (anzahl+minute) % 60;
        } else {
            System.out.println("Negative Werte werden nicht unterstützt!\n\r");
        }
    }
    /**
     * Addiert Sekundenanzahl zur aktuellen Zeit.
     */
    public void addiereSekunden(int anzahl) {
        if(anzahl + sekunde < 60 && anzahl > 0) {
            sekunde += anzahl;
        } else if(anzahl>0) {
            addiereMinuten((anzahl+sekunde)/60);
            sekunde = (anzahl+sekunde) % 60;
        } else {
            System.out.println("Negative Werte werden nicht unterstützt!\n\r");
        }
    }
    
    /**
     * Berechnet Anzahl der Sekunden der aktuellen Zeit.
     */
    public int berechneSekunden() {
        return sekunde + minute*60 + stunde*3600;
    }
    
    /**
     * Gibt die Zeit in der Konsole aus.
     */
    public void printZeit() {
        String sekunde = this.sekunde<10 ? "0" + this.sekunde : "" + this.sekunde;
        String minute  = this.minute <10 ? "0" + this.minute  : "" + this.minute;
        String stunde  = this.stunde <10 ? "0" + this.stunde  : "" + this.stunde;
        System.out.println(stunde + ":" + minute + ":" + sekunde + " an Tag " + tag + "\n\r");
    }
}
