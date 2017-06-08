
/**
 * Stellt Methoden für Kreise zur Verfügung
 * 
 * @author Kaiser Samuel (KAI17521@spengergasse.at)
 * @version 4
 */
public class Kreis
{
    private double radius;
    
    public Kreis() {
        setRadius(10);
    }
    
    public Kreis(double radius) {
        setRadius(radius);
    }
    
    public double getRadius() {
        return radius;
    }
    
    public void setRadius(double radius) {
        if(radius>0 && radius<=200) {
            this.radius = radius;
        } else {
            System.out.println("Ungültiger Radius");
            this.radius = 10;
            System.out.println("Standardwert gesetzt: " + this.radius);
        }
    }
    
    public double berechneUmfang() {
        return 2*radius*Math.PI;
    }
    
    public double berechneFlaeche() {
        return radius*radius*Math.PI;
    }
    
    public void printInfo() {
        System.out.println("Kreis:");
        System.out.println(" Radius = " + radius + " m");
        System.out.println(" Umfang = " + berechneUmfang() + " m");
        System.out.println(" Fläche = " + berechneFlaeche() + " m²\n");
    }
}
