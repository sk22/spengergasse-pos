/**
 * Klasse dient zum Speichern von Informationen über Personen
 * 
 * @author Kaiser Samuel
 * @version 2
 * 
 * Erstellt am 30.9.2015
 */

public class Person {
    
    // Konstruktoren
    public Person() { // Default-Konstruktor
        setMann(true);
    }
    
    public Person(String name) { // Konstruktor für Namen
        setName(name);
        setMann(true);
    }
    
    public Person(String name, int alter, boolean mann, String augenfarbe) {
        setName(name);
        setAlter(alter);
        setMann(mann);
        setAugenfarbe(augenfarbe);
    }
    
    // Variablen deklinieren
    private String name;
    private int alter;
    private boolean mann;
    private String augenfarbe;
    
    // Methoden
    public void setName(String name) {
        // setzt den Wert des Attributes name
        this.name = name;
    }
    
    public String getName() {
        // gibt den Wert des Attributes name zurück
        return name;
    }
    
    public void setAlter(int alter) {
        this.alter = alter;
    }
    
    public int getAlter() {
        return alter;
    }
    
    public void setMann(boolean mann) {
        this.mann = mann;
    }
    
    public boolean getMann() {
        return mann;
    }
    
    public void setAugenfarbe(String augenfarbe) {
        this.augenfarbe = augenfarbe;
    }
    
    public String getAugenfarbe() {
        return augenfarbe;
    }
    
    // Ausgabe
    public void printPerson() {
        System.out.print(name + " ist " + alter + " Jahre alt. ");
        if(mann) {
            System.out.print("Er ist männlich, und seine Augenfarbe ist ");
        } else {
            System.out.print("Sie ist weiblich, und ihre Augenfarbe ist ");
        }
        System.out.print(augenfarbe + ".\n");
    }
}
