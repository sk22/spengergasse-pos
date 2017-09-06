/**
 * @author Samuel Kaiser <samuel.kaiser01@gmail.com>
 * @since 2016-06-01
 */
public class Laeufer {
  private Geschlecht geschlecht;
  private Team team;

  public Laeufer(Geschlecht geschlecht) {
    this.geschlecht = geschlecht;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
  }

  public Geschlecht getGeschlecht() {
    return geschlecht;
  }

  @Override
  public String toString() {
    return Integer.toHexString(hashCode())+" "+this.geschlecht.name();
  }
}
