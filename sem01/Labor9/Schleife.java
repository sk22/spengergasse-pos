
/**
 * Verschiedene Funktionen mit Schleifen
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @version 2015-11-25
 */
public class Schleife
{
    private int limit;
    
    public Schleife() {
        setLimit(10);
    }
    public Schleife(int limit) {
        setLimit(limit);
    }
    
    public int getLimit() {
        return limit;
    }
    /**
     * Setzt limit. Erlaubt sind alle ganzen Zahlen von -20 bis 20.
     */
    public void setLimit(int limit) {
        if(limit >= -20 && limit <= 20) {
            this.limit = limit;
        } else {
            System.out.println("Ungültiger Wert für Limit:");
            System.out.println("Es werden nur Werte von -20 bis 20 akzeptiert!");
        }
    }
    
    /**
     * Gibt alle Zahlen von 0 bis limit aus.
     */
    public void schleife1() {
        if(limit>0) for(int i=0; i<=limit; i++) System.out.println(i);
        else if(limit<0) for(int i=0; i>=limit; i--) System.out.println(i);
    }
    
    /**
     * Gibt alle Zahlen von start bis limit aus.
     */
    public void schleife2(int start) {
        if((start>20 || start<-20)) {
            System.out.println("Für start wurde ein ungültiger Wert übergeben!");
            System.out.println("Es werden nur Werte von -20 bis 20 akzeptiert!");
        } else if (start<limit) for(int i=start; i<=limit; i++) System.out.println(i);
        else for(int i=start; i>=limit; i--) System.out.println(i);
    }
    
    /**
     * Gibt alle Zahlen von p1 bis p2, oder von p2 bis p1 aus.
     */
    public void schleife3(int p1, int p2) {
        if(p1>20 || p1<-20) {
            System.out.println("Für p1 wurde ein ungültiger Wert übergeben!");
            System.out.println("Es werden nur Werte von -20 bis 20 akzeptiert!");
        }
        else if(p2>20 || p2<-20) {
            System.out.println("Für p2 wurde ein ungültiger Wert übergeben!");
            System.out.println("Es werden nur Werte von -20 bis 20 akzeptiert!");
        } else if(p2<p1) {
            for(int i=p2; i<=p1; i++) System.out.println(i);
        } else {
            for(int i=p1; i<=p2; i++) System.out.println(i);
        }
    }
    
    /**
     * Gibt alle ungeraden Zahlen von start bis limit aus.
     */
    public void schleife4(int start) {
        if(start<limit) for(int i=start; i<=limit; i++) {
            if(i%2==1 || i%2==-1) System.out.println(i);
        } else for(int i=start; i>=limit; i--) {
            if(i%2==1 || i%2==-1) System.out.println(i);
        }
    }
    
    /**
     * Gibt gegebene Zeichenkette so oft aus, wie angegeben.
     */
    public void schleife5(String zeichen, int anzahl) {
        for(int i = 0; i<=anzahl; i++) System.out.print(zeichen);
    }
    
    /**
     * Gibt die n-Potenz der Zahl a zurück.
     */
    public int a_hoch_n(int a, int n) {
        int prod = 1;
        for(int i=0; i<n; i++) prod*=a;
        return prod;
    }
    
    /**
     * Gibt die Zahlen in gegebener Reihenfolge aus.
     */
    public void folge(int anzahl) {
        int zaehler = 1; int nenner = 1; int i = 0;
        do {
            System.out.print(zaehler+"/"+nenner+", ");
            zaehler *= -3;
            nenner += i+1;
            i++;
        } while (i<anzahl);
    }
}
