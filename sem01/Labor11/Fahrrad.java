
/**
 * Aufgabe 11 "Fahrrad" (Schildberger)
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @version 2015-12-09
 * @since 2015-12-02
 */
public class Fahrrad
{
    private Person fahrer;
    private Tasche tasche1;
    private Tasche tasche2;
    
    public Fahrrad(Person fahrer) {
      //  if(fahrer == null) fahrer = new Person();
        setFahrer(fahrer);
    }
    
    public Fahrrad() {
        fahrer = new Person();
    }
    
    
    public void setFahrer(Person fahrer) {
        this.fahrer = fahrer;
    }
    
    public void befestigeTasche(Tasche tasche) {
        if(tasche1 == null) {
            if(tasche != null) {
                tasche1 = tasche;
            } else {
                tasche1 = new Tasche();
            }
        } else if(tasche2 == null) {
            if(tasche != null) {
                tasche2 = tasche;
            } else {
                tasche2 = new Tasche();
            }
        }
    }
    
    public float berechneFahrzeit(float strecke) {
        if(fahrer ==  null || strecke <= 0) {
            return -1;
        }
        int gewicht = 0;
        int geschwindigkeit = 25; // km/h
        if(tasche1 != null) {
            gewicht+=tasche1.getGewicht();
        }
        if(tasche2 != null) {
            gewicht+=tasche2.getGewicht();
        }
        float verminderung = gewicht/1000 * 0.5f;
        geschwindigkeit -= verminderung;
        // h = km * km/h
        float fahrzeit = strecke / geschwindigkeit;
        return fahrzeit*60;
    }
}
