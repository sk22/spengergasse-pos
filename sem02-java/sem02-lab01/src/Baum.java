package src;

 


public class Baum {
  char zeichen;
  int hoehe;
  
  public Baum(int hoehe) {
    this(hoehe, '#');
  }
  public Baum(int hoehe, char zeichen) {
    this.setHoehe(hoehe);
    this.setZeichen(zeichen);
  }
  
  public void setZeichen(char zeichen) {
    this.zeichen = zeichen;
  }
  
  public void setHoehe(int hoehe) {
    this.hoehe = hoehe;
  }
  
  public void generate() {
    for(int i=this.hoehe; i>0; i--) {
      System.out.println(this.zeile(this.hoehe, i));
    }
    System.out.println(this.stamm(this.hoehe));
  }

  public String zeile(int anzahl, int teile) {
    String abstand = ""; String zeile = "";
    for(int i=0; i<teile; i++) abstand += " ";
    for(int i=0; i<(anzahl-teile)*2-1; i++) zeile += this.zeichen;
    return abstand+zeile;
  }

  public String leerzeichen(int anzahl) {
    String leerzeichen = "";
    for(int i=0; i<anzahl; i++) leerzeichen+=" ";
    return leerzeichen;
  }
  public String stamm(int anzahl) {
    String stamm = this.leerzeichen(anzahl-1)+this.zeichen;
    return stamm;
  }
}
