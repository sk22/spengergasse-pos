package src;

 


public class Main {
  public static void main(String[] args) {
    Sternchen stern = new Sternchen().leer(' ').plusminus(new char[] {'+', '-'}).zeichen('#');
    
    stern.zeichneRechteckVoll(15, 4);
    System.out.println();
    
    stern.zeichneRechteckLeer(10, 5);
    System.out.println();
    
    stern.zeichneRaute(7);
    System.out.println();
    
    stern.zeichneDreieck(6);
    System.out.println();
    
    stern.zeichneDreieckPlusMinus(8);
    System.out.println();

    stern.zeichneBaum(5);
    System.out.println();
    
    stern.zeichneQuadratischeFunktion(20);
    System.out.println();
  }
}
