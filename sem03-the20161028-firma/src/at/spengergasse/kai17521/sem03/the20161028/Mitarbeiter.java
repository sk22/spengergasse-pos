package at.spengergasse.kai17521.sem03.the20161028;

public class Mitarbeiter {
  private Bereich bereich;
  String kontaktdaten;
  String name;
  public Bereich getBereich() {
    return bereich;
  }
  public void setBereich(Bereich bereich) {
    this.bereich = bereich;
  }
  public String getKontaktdaten() {
    return kontaktdaten;
  }
  public void setKontaktdaten(String kontaktdaten) {
    this.kontaktdaten = kontaktdaten;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("Invalid name");
    }
    this.name = name;
  }
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((bereich == null) ? 0 : bereich.hashCode());
    result = prime * result + ((kontaktdaten == null) ? 0 : kontaktdaten.hashCode());
    result = prime * result + ((name == null) ? 0 : name.hashCode());
    return result;
  }
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Mitarbeiter other = (Mitarbeiter) obj;
    if (bereich != other.bereich)
      return false;
    if (kontaktdaten == null) {
      if (other.kontaktdaten != null)
        return false;
    } else if (!kontaktdaten.equals(other.kontaktdaten))
      return false;
    if (name == null) {
      if (other.name != null)
        return false;
    } else if (!name.equals(other.name))
      return false;
    return true;
  }
  
  
}
