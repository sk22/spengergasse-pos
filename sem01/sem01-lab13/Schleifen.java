
/**
 * Übungen zu Schleifen
 * 
 * @author Kaiser Samuel
 * @version 2016/01/17
 */
public class Schleifen {
    public static float folge1(int anzahl) {
        float sum = 0;
        for(int n = 1, i = anzahl; i>0; i--, n+=n) {
            sum += (float)1/n;
        }
        return sum;
    }
    
    public static void folge2(int anzahl) {
        int z = -2,
            n = 10,
            v = 5;
        for(int i = 0; i<anzahl;i++) {
            if(i != anzahl && z > 0) System.out.print("+");
            System.out.print(z+"/"+n);
            z*=-2;
            int zwsp = n;
            n = v+n;
            v = zwsp;
        }
    }
    
    public static boolean istPrimzahl(int zahl) {
        boolean result = true;
        zahl = Math.abs(zahl);
        double wurzel = Math.sqrt(zahl);
        
        for(int i=2; i<=wurzel; i++) {
            if(zahl%i == 0) {
                result = false;
                break;
            }
        }
        return result;
    }
    
    public static void berechnePrimzahlen(int start, int ende) {
        if(start>ende) { int x = start; start = ende; ende = x; }
        boolean first = true;
        for(int i=start; i<=ende; i++) {
            if(istPrimzahl(i)) {
                if(!first) System.out.print(", ");
                System.out.print(i);
                first = false;
            }
        }
        System.out.println("\n");
    }
    
    public static int berechneQuersummeStr(int zahl) {
        String str = zahl + "";
        int summe = 0;
        for(int i=0; i<str.length(); i++) {
            summe += Character.getNumericValue(str.charAt(i));
        }
        return summe;
    }
    
    public static int berechneQuersumme(int zahl) {
        int summe = 0;
        while(zahl!=0) {
            summe+=zahl%10;
            zahl/=10;
        }
        return summe;
    }
    
    public static int gewinn(int jahre, float prozent) {
        float betrag = 1000;
        for(int i=jahre; i>0; i--) {
            betrag+=betrag*2/100;
        }
        return (int)betrag;
    }
    
    public static void falling(int startHoehe, int zeit) {
        double g = 9.80665;
        
        String abstandZeit = "", abstandHoehe = "";
        String dashesZeit  = "", dashesHoehe  = "";
        int stellenZeit = (zeit+"").length();
        int stellenHoehe = (startHoehe+"").length();
        for(int i=0;i<stellenZeit;i++) abstandZeit+=" ";
        for(int i=0;i<stellenZeit;i++) dashesZeit+="-";
        for(int i=0;i<stellenHoehe;i++) abstandHoehe+=" ";
        for(int i=0;i<stellenHoehe;i++) dashesHoehe+="-";
        
        System.out.print(" Sekunden"+abstandZeit);
        System.out.print(" -> Entfernung (Abwurfhöhe: "+startHoehe+")\n");
        System.out.print(" --------"+abstandZeit);
        System.out.print(" -> -------------------------"+dashesHoehe);
        System.out.println();
        
        // s = g/2 * t²
        
        double s = startHoehe;
        for(int t=0; t<=zeit && s<=startHoehe; t++) {
            int stellen = stellenZeit-(t+"").length();
            String abstand = "";
            for(int i=0;i<stellen;i++) abstand+=" ";
            
            s = g/2*(t*t);
            System.out.print("    "+t+abstand+"     ");
            System.out.println(" ->   "+ s);
            
            if(s>=startHoehe) System.out.println(" Das Objekt ist auf dem Boden aufgeschlagen!");
            else if(t>=zeit)  System.out.println(" Verbleibende Resthöhe: "+(startHoehe-s)+" m");
        }
        System.out.println();
    }
    
    public static void test() {
        double d = 0.0;
        while(d <= 1.0) { // Solange d ungleich 1 -> kleiner als 1
            d += 0.1; // erhöhe d um 0.1
            System.out.println(d);
        }
        System.out.println();
    }

    
}
