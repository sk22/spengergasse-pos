
/**
 * Simulation eines Kopierer-Shops
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @since 2016-01-13
 * @version 2016-01-20
 */
public class CopyShop {
    private Kopierer geraet1;
    private Kopierer geraet2;
    private Kopierer geraet3;
    
    public CopyShop() { } // Nur, damit er da steht. Tut ja nix. Siehe Angabe.
    
    public int anzahlKopierer() {
        int anzahl = 0;
        if(geraet1!=null) anzahl++;
        if(geraet2!=null) anzahl++;
        if(geraet3!=null) anzahl++;
        return anzahl;
    }
    
    public void kopiererAufstellen(Kopierer geraet) {
        if(geraet == null) {
            System.out.println("Kein Kopierer übergeben. Parameter darf nicht null sein!\n");
            return;
        }
        if(geraet.equals(geraet1) ||  geraet.equals(geraet2) || geraet.equals(geraet3)) {
            System.out.println("Kopierer ist bereits vorhanden!\n");
            return;
        }
        if(geraet1 == null) {
            geraet1 = geraet;
        } else if(geraet2 == null) {
            geraet2 = geraet;
        } else if(geraet3 == null) {
            geraet3 = geraet;
        } else {
            System.out.println("Es ist kein Platz vorhanden!\n");
        }
    }
    
    public int berechneMoeglicheKopien() {
        int summe = 0;
// mögliche Kopien sollte nur die funktionsfähigen Kopierer berücksichtigen        
        if(geraet1 != null && !geraet1.getDefekt()) summe += geraet1.getPapier();
        if(geraet2 != null && !geraet2.getDefekt()) summe += geraet2.getPapier();
        if(geraet3 != null && !geraet3.getDefekt()) summe += geraet3.getPapier();
        return summe;
    }
    
    public void printInfo() {
        System.out.println("Anzahl der Kopierer: " + anzahlKopierer());
        System.out.println(" -");
        System.out.println("Kopierer 1:");
        if(geraet1 == null) System.out.println("nicht vorhanden");
        else geraet1.printInfo();
        
        System.out.println("Kopierer 2:");
        if(geraet2 == null) System.out.println("nicht vorhanden");
        else geraet2.printInfo();
        
        System.out.println("Kopierer 3:");
        if(geraet3 == null) System.out.println("nicht vorhanden");
        else geraet3.printInfo();
        
        System.out.println();
    }
}
