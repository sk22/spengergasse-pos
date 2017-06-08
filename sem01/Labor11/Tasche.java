
/**
 * Aufgabe 11 "Fahrrad" (Schildberger)
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @version 2015-12-09
 * @since 2015-12-02
 */
public class Tasche
{
    private int gewicht;
    private int maxGewicht = 20000; // 20.000g = 20 kg
    
    public Tasche() {
        gewicht = 0;
    }
    
    public Tasche(int gewicht) {
        setGewicht(gewicht);
    }
    
    public int getGewicht() {
        return gewicht;
    }
    
    public void setGewicht(int gewicht) {
        if (gewicht>=0 && gewicht<=maxGewicht) {
            this.gewicht = gewicht;
        }
    }
    
    public void einpacken(int gewicht) {
        if (this.gewicht+gewicht<=maxGewicht && gewicht>0) {
            setGewicht(this.gewicht+gewicht);  
        } else {
            setGewicht(maxGewicht);
        }
    }
    
    public void auspacken(int gewicht) {
        if(this.gewicht-gewicht>=0 && gewicht>0) {
            setGewicht(this.gewicht-gewicht);
        } else {
            setGewicht(0);
        }
    }
}
