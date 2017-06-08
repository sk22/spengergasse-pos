
/**
 * Extraaufgabe "Wald" (Schildberger)
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at> 
 * @version 2015-12-09
 * @since 2015-12-09
 */
public class Baum
{
    private String name;
    private short alter;
    private double hoehe;
    private boolean krank;
    private double durchmesser;
    
    public Baum(String name, short alter, double hoehe, boolean krank, double durchmesser) {
        setName(name);
        setAlter(alter);
        setHoehe(hoehe);
        setKrank(krank);
        setDurchmesser(durchmesser);
    }
    public Baum() {
        setName("Baumi");
        setAlter((short)20);
        setHoehe(75);
        setKrank(false);
        setDurchmesser(0.5);
    }
    
    public String getName() { return name; }
    public void setName(String name) {
        if(name == null) {
            name = "Baumi";
        }
        this.name = name;
    }
    
    public short getAlter() { return alter; }
    public void setAlter(short alter) {
        if(alter>0 && alter<40) {
            this.alter = alter;
        } else {
            System.out.println("Alter ungültig! Muss größer als 0 und kleiner als 40 sein!");
        }
    }
    
    public double getHoehe() { return hoehe; }
    public void setHoehe(double hoehe) {
        if(hoehe>0 && hoehe<150) {
            this.hoehe = hoehe;
        } else {
            System.out.println("Höhe ungültig! Muss größer als 0 und kleiner als 150 sein!");
        }
    }
    
    public boolean getKrank() { return krank; }
    public void setKrank(boolean krank) { this.krank = krank; }
    
    public double getDurchmesser() { return durchmesser; }
    public void setDurchmesser(double durchmesser) {
        if(durchmesser>0 && durchmesser<(hoehe/10)) {
            this.durchmesser = durchmesser;
        } else {
            System.out.println("Durchmesser ungültig! Muss größer als 0 und kleiner als 1/10 der Höhe sein!");
        }
    }
    
    public void print() {
        System.out.println("Name: " + name);
        System.out.println("Alter: " + alter);
        System.out.println("Höhe: " + hoehe + " m");
        System.out.println("Krank: " + (krank ? "Ja!" : "Nein."));
        System.out.println("Durchmesser: " + durchmesser + " m");
    }
    
    public boolean zuFaellen() {
        if(krank || hoehe>150 || alter>40) return true;
        else return false;
    }
    
    public double volumen() {
        return durchmesser*Math.PI*hoehe;
    }
}
