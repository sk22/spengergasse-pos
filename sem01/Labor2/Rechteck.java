
/**
 * Stellt Methoden für Rechtecke zur Verfügung
 * 
 * @author Kaiser Samuel (KAI17521@spengergasse.at)
 * @version 5
 */
public class Rechteck {
    private int laenge;
    private int breite;
    
    public Rechteck() {
        setLaenge(10);
        setBreite(10);
    }
    
    public Rechteck(int laenge, int breite) {
        setLaenge(laenge);
        setBreite(breite);
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
    
    public int berechneUmfang() {
        return 2*breite + 2*laenge;
    }
    
    public int berechneFlaeche() {
        return this.breite * this.laenge;
    }
    
    public void printInfo() {
        System.out.println("Rechteck:");
        System.out.println(" Länge = " + laenge + " m");
        System.out.println(" Breite = " + breite + " m");
        System.out.println(" Umfang = " + berechneUmfang() + " m");
        System.out.println(" Fläche = " + berechneFlaeche() + " m²\n");
    }
}
