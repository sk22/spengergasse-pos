
/**
 * Schleifen
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at> 
 * @since 2016-01-13
 * @version 2016-01-20
 */
public class Schleife {
    public static void folge(int anzahl) {
        int z = 1,
            n = 3;
        boolean lock = true;
        for(int i=0; i<anzahl; i++) {
            if(!lock)  System.out.print(", "); else lock = false;
            if(z % 3 == 0) System.out.print("-");
            System.out.print(z + "/" + n);            
            z++; n*=3;
        }
    }
    
    public static int ggT(int zahl1, int zahl2) {
        int kZahl = (zahl1<zahl2) ? zahl1 : zahl2;
// ist ok, aber es reicht eigentlich bis zur kleineren Zahl zu laufen        
        int speicher = 1;
        for(int t=1; t<kZahl; t++) {
            if(zahl1%t == 0 && zahl2%t == 0) speicher = t;
        }
        return speicher;
    }
}
