import java.util.*;
/**
 * @author Kaiser Samuel
 * @version 2016-01-27
 * @since 2016-01-27
 */
public class Zahlenraten {
    private Random generator;
    private int zahl;
    private int versuche;
    
    public Zahlenraten() {
        generator = new Random();
    }
    
    public boolean generiereZufallszahl(int min, int max) {
        if(max<min) {
            int s = min; min = max; max = s;
        }
        if(max>0 && min>0) {
            this.zahl = generator.nextInt(max-min+1)+min;
            return true;
        } else {
            System.out.println("Ungültige Zahl(en)! Nur Werte ab 1 sind erlaubt!");
            return false;   
        }
    }
    
    public int rateZahl(int zahl) {
        this.versuche++;
        if(zahl == this.zahl) { return 0; }
        else if(zahl<this.zahl) { return -1; }
        else { return 1; }
    }
    
    public int getVersuche() {
        return this.versuche;
    }
}
