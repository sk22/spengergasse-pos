import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Samuel Kaiser <samuel.kaiser01@gmail.com>
 * @since 2016-06-01
 */
public class Team {
  private Wettbewerb naechster;
  private ArrayList<Laeufer> laeufer;
  private double laufzeit;

  public Team(Laeufer... laeufer) {
    this.laeufer = new ArrayList<>();
    for(Laeufer l : laeufer) l.setTeam(this);
    this.laeufer.addAll(Arrays.asList(laeufer));
  }

  public ArrayList<Laeufer> getLaeufer() {
    return laeufer;
  }
  public double getLaufzeit() {
    return laufzeit;
  }

  void setLaufzeit(double laufzeit) {
    this.laufzeit = laufzeit;
  }
  void leereNaechster() {
    this.naechster = null;
  }

  @Override
  public String toString() {
    return "Team " + Integer.toHexString(hashCode())+": "+laeufer.toString()
      +" Laufzeit: "+laufzeit+" Wettbewerb: "+naechster;
  }

  void anmelden(Wettbewerb wettbewerb) {
    anmelden(wettbewerb, new Laeufer[] {});
  }

  void anmelden(Wettbewerb wettbewerb, Laeufer... weitere) {
    if(naechster==null) {
      naechster = wettbewerb;
      laeufer.addAll(Arrays.asList(weitere));
    } else {
      System.out.println("Es ist noch ein Wettbewerb ausständig!");
    }
  }

  public Geschlecht reinesTeam() {
    for(Laeufer laeufer : this.laeufer) {
      if(laeufer.getGeschlecht() != this.laeufer.get(0).getGeschlecht()) return null;
    } return this.laeufer.get(0).getGeschlecht();
  }
}
