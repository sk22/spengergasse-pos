package at.spengergasse.kai17521.sem03.the20161028;

public class Taetigkeit {
  private String beschreibung;
  private int wert;
  private int interneKosten;
  
  public Taetigkeit(String beschreibung, int wert, int interneKosten) {
    setBeschreibung(beschreibung);
    setWert(wert);
    setInterneKosten(interneKosten);
  }
  
  public String getBeschreibung() {
    return beschreibung;
  }
  
  public void setBeschreibung(String beschreibung) throws IllegalArgumentException {
    if (beschreibung == null) {
      throw new IllegalArgumentException("Ungültige Beschreibung!");
    }
    this.beschreibung = beschreibung;
  }
  
  public int getWert() {
    return wert;
  }
  
  public void setWert(int wert) {
    this.wert = wert;
  }
  
  public int getInterneKosten() {
    return interneKosten;
  }
  
  public void setInterneKosten(int interneKosten) {
    if (interneKosten < 0 || interneKosten > wert) {
      throw new IllegalArgumentException("Ungültige interne Kosten");
    }
    this.interneKosten = interneKosten;
  }
  
  public int firmenwert() {
    return wert - interneKosten;
  }
  
  public void verteuern(float prozent) {
    
  }

  @Override
  public String toString() {
    return "Taetigkeit [beschreibung=" + beschreibung + ", wert=" + wert + ", interneKosten=" + interneKosten + "]";
  }
}
