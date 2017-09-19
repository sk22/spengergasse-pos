import java.util.Scanner;
/**
 * @author Kaiser Samuel
 * @version 2016-01-27
 * @since 2016-01-27
 */
public class Konsole {
    private Scanner eingabe;
    private Zahlenraten spiel;
    private int maximum = 100;
    private int minimum = 1;
    
    public Konsole() {
        System.out.println();
        spiel = new Zahlenraten();
        eingabe = new Scanner(System.in);
        spiel.generiereZufallszahl(this.minimum, this.maximum);
    }
    public Konsole(int max) {
        System.out.println();
        spiel = new Zahlenraten();
        if(!spiel.generiereZufallszahl(this.minimum, max)) {
            spiel.generiereZufallszahl(this.minimum, this.maximum);
        } else {
            this.maximum = max;
        }
    }
    public Konsole(int min, int max) {
        System.out.println();
        spiel = new Zahlenraten();
        if(!spiel.generiereZufallszahl(min, max)) {
            spiel.generiereZufallszahl(this.minimum, this.maximum);
        } else {
            this.maximum = max;
            this.minimum = min;
        }
    }
    
    public boolean spielen() {
        boolean stop = false;
        while(!stop) {
            System.out.print("Bitte Zahl eingeben: ");
            switch(zahlEinlesen()) {
                case 0:
                    System.out.println("Richtig!");
                    System.out.println("Versuche: "+spiel.getVersuche());
                    stop = true;
                    break;
                case 1:
                    System.out.println("Zu hoch!");
                    break;
                case -1:
                    System.out.println("Zu klein!");
                    break;
            }
        }
        System.out.print("Nochmal spielen? (J/n) ");
        eingabe = new Scanner(System.in);
        if("n".equalsIgnoreCase(eingabe.nextLine())) return false;
            else return true;
        }
    
    private int zahlEinlesen() {
        eingabe = new Scanner(System.in);
        if(eingabe.hasNextInt()) {
            return spiel.rateZahl(eingabe.nextInt());
        } else {
            System.out.println("Ungültige Zahl: "+eingabe.nextLine());
            return -2;
        }
    }
    
    public int getMaximum() {
        return this.maximum;
    }
    public int getMinimum() {
        return this.minimum;
    }
}
