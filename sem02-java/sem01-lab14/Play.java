import java.util.NoSuchElementException;
/**
 * @author Kaiser Samuel
 * @version 2016-01-27
 * @since 2016-01-27
 */
public class Play {
    public static void main(String[] args) {
        Konsole konsole;
        try {
            do {
                if(args.length==1 && isInteger(args[0])) {
                    konsole = new Konsole(Integer.parseInt(args[0]));
                    System.out.println("Zahlenbereich: 1 bis "+konsole.getMaximum());
                } else if(args.length==2 && isInteger(args[0]) && isInteger(args[1])) {
                    konsole = new Konsole(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
                    System.out.println("Zahlenbereich: "+konsole.getMinimum()+" bis "+konsole.getMaximum());
                } else {
                    konsole = new Konsole();
                    System.out.println("Zahlenbereich: "+konsole.getMinimum()+" bis "+konsole.getMaximum());
                }
            } while(konsole.spielen());
            System.out.println("\nDanke f\u00FCrs Spielen!");
        } catch(NoSuchElementException e) {
            System.out.println();
            System.out.println("\nDanke f\u00FCrs Spielen!");
        }
    }
    
    public static boolean isInteger(String s) {
        try { 
            Integer.parseInt(s); 
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }
}
