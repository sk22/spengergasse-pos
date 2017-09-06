
/**
 * Taschenrechner
 * 
 * @author Kaiser Samuel (kai17521@spengergasse.at) 
 * @version 2
 */
public class Taschenrechner
{
    private int gespeicherteZahl;
    private boolean speicherAktiv;
    
    public Taschenrechner()
    {
        speicherAktiv = false;
    }
    
    public Taschenrechner(char rechenzeichen, int a, int b) {
        switch(rechenzeichen) {
            case '+': System.out.println(a+" + "+b+" = "+addiere(a, b));
                      break;
            case '-': System.out.println(a+" - "+b+" = "+subtrahiere(a, b));
                      break;
            case '*': System.out.println(a+" * "+b+" = "+multipliziere(a, b));
                      break;
            case '/': System.out.println(a+" / "+b+" = "+dividiere(a, b));
                      break;
            default:  System.out.println("Ungültiges Rechenzeichen");
                      System.out.println("Gültige Zeichen sind: '+', '-', '*', '/'");
        }
    }

    public void setGespeicherteZahl(int gespeicherteZahl) {
        this.gespeicherteZahl = gespeicherteZahl;
        speicherAktiv = true;
    }
    
    public int getGespeicherteZahl() {
        return gespeicherteZahl;
    }
    
    public void loescheSpeicher() {
        gespeicherteZahl = 0;
        speicherAktiv = false;
    }
    
    public boolean istSpeicherAktiv() {
        return speicherAktiv;
    }
    
    
    public int addiere(int a, int b) {
        return a+b;
    }
    
    public int addiere(int a, int b, int c) {
        return a+b+c;
    }
    
    public int subtrahiere(int a, int b) {
        return a-b;
    }
    
    public int subtrahiere(int a, int b, int c) {
        return a-b-c;
    }
    
    public int multipliziere(int a, int b) {
        return a*b;
    }
    
    public int multipliziere(int a, int b, int c) {
        return a*b*c;
    }
    
    public int dividiere(int a, int b) {
        return a/b;
    }
    
    public float dividiereFloat(float a, float b) {
        return a/b;
    }
    
    
    public void addiereZuSpeicher(int wert) {
        gespeicherteZahl += wert;
    }
    
    public void subtrahiereVonSpeicher(int wert) {
        gespeicherteZahl -= wert;
    }
    
    public int hoch2(int x) {
        return x*x;
    }
    
    public int hoch3(int x) {
        return x*x*x;
    }
    
    public int a_plus_b_hoch_3(int a, int b) {
        return hoch3(a+b);
    }
}
