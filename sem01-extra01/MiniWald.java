
/**
 * Extraaufgabe "Wald" (Schildberger)
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at> 
 * @version 2015-12-09
 * @since 2015-12-09
 */
public class MiniWald
{
    private String name;
    private Baum b0;
    private Baum b1;
    private Baum b2;
    
    public MiniWald(String name, Baum b0, Baum b1, Baum b2) {
        setName(name);
        setBaum0(b0);
        setBaum1(b1);
        setBaum2(b2);
    }
    public MiniWald() {
        setName(null);
        setBaum0(new Baum());
        setBaum1(new Baum());
        setBaum2(new Baum());
    }
    
    public String getName() { return name; }
    public void setName(String name) {
        if(name == null || name == "") name = "Waldi";
        this.name = name;
    }
    
    public Baum getBaum0() { return b0; }
    public void setBaum0(Baum baum) {
        if(baum == null) baum = new Baum();
        b0 = baum;
    }
    
    public Baum getBaum1() { return b1; }
    public void setBaum1(Baum baum) {
        if(baum == null) baum = new Baum();
        b1 = baum;
    }
    
    public Baum getBaum2() { return b2; }
    public void setBaum2(Baum baum) {
        if(baum == null) baum = new Baum();
        b2 = baum;
    }
    
    public void print() {
        if(b0 != null) {
            System.out.println("- Baum 0:");
            b0.print();
        }
        if(b1 != null) {
            System.out.println("- Baum 1:");
            b1.print();
        }
        if(b2 != null) {
            System.out.println("- Baum 2:");
            b2.print();
        }
    }
    
    public Baum faellen() {
        Baum baum = null;
             if(b2 != null && b2.zuFaellen()) { baum = b2; b2 = null; }
        else if(b1 != null && b1.zuFaellen()) { baum = b1; b1 = null; }
        else if(b0 != null && b0.zuFaellen()) { baum = b0; b0 = null; }
        else System.out.println("Es muss kein Baum gefällt werden!");
        return baum;
    }
    
    public Baum entfernen() {
        Baum baum = null;
             if(b2 != null) { baum = b2; b2 = null; }
        else if(b1 != null) { baum = b1; b1 = null; }
        else if(b0 != null) { baum = b0; b0 = null; }
        else System.out.println("Es ist kein Baum vorhanden!");
        return baum;
    }
    
    public void pflanzen(Baum baum) {
        if(baum == null) System.out.println("Kein Baum übergeben!");
        else if(b0 == null) b0 = baum;
        else if(b1 == null) b1 = baum;
        else if(b2 == null) b2 = baum;
        else System.out.println("Kein Platz frei!");
    }
    public void pflanzen(String name, short alter, double hoehe, boolean krank, double durchmesser) {
        pflanzen(new Baum(name, alter, hoehe, krank, durchmesser));
    }
    
    public int anzahlKrank() {
        int anz = 0;
        if(b0.getKrank()) anz++;
        if(b1.getKrank()) anz++;
        if(b2.getKrank()) anz++;
        return anz;
    }
}
