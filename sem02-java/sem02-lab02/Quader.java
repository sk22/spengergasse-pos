
/**
 * Stellt Methoden für Quader zur Verfügung
 * 
 * @author Kaiser Samuel (KAI17521@spengergasse.at)
 * @version 4
 */
public class Quader {
    private int laenge;
    private int breite;
    private int hoehe;
    private int dichte;
    
    public Quader() {
        setLaenge(10);
        setBreite(10);
        setHoehe(10);
        setDichte(100);
    }
    
    public Quader(int laenge, int breite, int hoehe, int dichte) {
        setLaenge(laenge);
        setBreite(breite);
        setHoehe(hoehe);
        setDichte(dichte);
    }
    
    public Quader(int laenge, int breite, int hoehe) {
        setLaenge(laenge);
        setBreite(breite);
        setHoehe(hoehe);
    }
    
    public int getLaenge() {
        return laenge;
    }
    
    public void setLaenge(int laenge) {
        if(laenge>0 && laenge<=200) {
            this.laenge = laenge;
        } else {
            System.out.println("Ungültige Länge");
            this.laenge = 10;
            System.out.println("Standardwert gesetzt: " + this.laenge);
        }
    }
    
    public int getBreite() {
        return breite;
    }
    
    public void setBreite(int breite) {
        if(breite>0 && breite<=200) {
            this.breite = breite;
        } else {
            System.out.println("Ungültige Breite");
            this.breite = 10;
            System.out.println("Standardwert gesetzt: " + this.breite);
        }
    }
    
    public int getHoehe() {
        return breite;
    }
    
    public void setHoehe(int hoehe) {
        if(hoehe>0 && hoehe<=200) {
            this.hoehe = hoehe;
        } else {
            System.out.println("Ungültige Breite");
            this.hoehe = 10;
            System.out.println("Standardwert gesetzt: " + this.hoehe);
        }
    }
    
    public int getDichte() {
        return dichte;
    }
    
    public void setDichte(int dichte) {
        if(dichte>0 && dichte<=3000) {
            this.dichte = dichte;
        } else {
            System.out.println("Ungültige Breite");
            this.dichte = 100;
            System.out.println("Standardwert gesetzt:" + this.dichte + " 100 kg/m³");
        }
    }
    
    public int berechneOberflaeche() {
        return laenge*breite*2 + laenge*hoehe*2 + breite*hoehe*2;
    }
    
    public int berechneVolumen() {
        return laenge*breite*hoehe;
    }
    
    public int berechneMasse() {
        return dichte*berechneVolumen();
    }
    
    public void printInfo() {
        System.out.println("Quader:");
        System.out.println(" Länge = " + laenge + " m");
        System.out.println(" Breite = " + breite + " m");
        System.out.println(" Oberfläche = " + berechneOberflaeche() + " m²");
        System.out.println(" Volumen = " + berechneVolumen() + " m³");
        System.out.println(" Masse = " + berechneMasse() + " kg\n");
    }
}
