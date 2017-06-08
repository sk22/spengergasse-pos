
/**
 * Simulation des Management der Wagons an einem Personenzug
 * 
 * @author Kaiser Samuel <kai17521@spengergasse.at>
 * @version 2016-05-04
 * @since 2015-12-16
 */
public class Personenzug {
  private Personenwagon[] wagons;
  private int frei;

  public Personenzug(int groesse) {
    //sonst ....??
    if(groesse>0) wagons = new Personenwagon[groesse];
  }
  public Personenzug(Personenwagon... wagon) {
    wagons = wagon;
  }
  
  public boolean wagonHinzufuegen(Personenwagon wagon) {
    if(wagon == null) {
      System.out.println("Kein Personenwagon-Objekt übergeben!");
      return false;
    } else if(frei()) {
        //REferenzvergleich mit == ist sinnlos
      for(Personenwagon i : wagons) if (i == wagon) {
        System.out.println("Wagon bereits enthalten!"); return false;
      }
      wagons[frei] = wagon; frei++;
      return true;
    } else {
      System.out.println("Kein Platz mehr frei!");
      return false;
    }
  }

  public Personenwagon getWagon(int pos) {
    if(pos<frei && pos>=0) return wagons[pos];
    return null;
  }

  /**
   * Wenn Klasse == 0, dann alle Wagons mitgezählt
   */
  public int getAnzahlFreieSitzplaetze(int klasse) {
    int anzahl = 0;
    for(int i=0; i<frei; i++) if(klasse==0 || wagons[i].getKlasse()==klasse) anzahl+=wagons[i].getFrei();
    return anzahl;
  }
  
  /**
   * Lässt Personen einsteigen.
   * @param anzahl Anzahl der einsteigenden Menschen
   * @param klasse Klasse der Wagons, in die die Personen einsteigen sollen. 0 -> Beliebige Klasse
   */
  public int verteilen(int anzahl, int klasse) {
    if(klasse>2 && klasse<0) System.err.println("Ungültige Klasse! Es gibt nur Klasse 1 und 2. Klasse 0, um alle Klassen zu akzepzieren.");
    else if(anzahl>0) {
      for (int i=0; i<frei; i++)
        if (klasse == 0 || wagons[i].getKlasse() == klasse)
          anzahl = wagons[i].einsteigen(anzahl);
    }
    return anzahl;
  }

  public boolean frei() {
    return frei<wagons.length;
  }

  public boolean entferneWagon(Personenwagon wagon) {
    if(wagon != null && findePosition(wagon) != -1) {
      entferneWagon(findePosition(wagon));
      return true;
    } return false;

  }
  public void entferneWagon(int position) {
    if(position>=0 && position<wagons.length) {
      for(int i=position; i<frei-1; i++) {
        wagons[i] = wagons[i+1];
      } frei--; wagons[frei] = null;
    } else System.out.println();
  }

  public int findePosition(Personenwagon wagon) {
    for(int i=0; i<frei; i++) {
      if(wagons[i].equals(wagon)) return i;
    } return -1;
  }
  
  
  public int getAnzahlWagons() {
    return frei+1;
  }
  
  public void printInfo() {
    String text = ""
     + "Anzahl Wagons: " + getAnzahlWagons()+"\n"
     + "==============\n";
    for(int i=0; i<wagons.length; i++) if(wagons[i]!=null) {
      text += ""
       + "Wagon "+i+" ["+wagons[i]+"]:\n"
       + "  - Klasse: " + wagons[i].getKlasse() + "\n"
       + "  - Sitzplätze: " + wagons[i].getMaximum() + "\n"
       + "  davon: " + wagons[i].getFrei() + " frei, "
       + wagons[i].getBelegt() + " belegt\n";
    }

    System.out.println(text);
  }
}











