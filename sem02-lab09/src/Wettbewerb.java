/**
 * @author Samuel Kaiser <samuel.kaiser01@gmail.com>
 * @since 2016-06-01
 */
public class Wettbewerb {
  private Team[] teams;
  private int pos;
  private int anzahlLaeufer;

  private boolean ausgetragen;

  public Wettbewerb(int anzahlTeams, int anzahlLaeufer) {
    this.anzahlLaeufer = anzahlLaeufer;
    this.teams = new Team[anzahlTeams];
  }

  public boolean anmelden(Team team) {
    if(anzahlLaeufer != team.getLaeufer().size()) {
      System.err.println("Anzahl der Läufer muss "+anzahlLaeufer+" pro Team betragen!");
      return false;
    }
    if(pos<teams.length) {
      this.teams[pos++] = team;
      team.anmelden(this);
    } else {
      System.err.println("Es sind im Wettbewerb keine Plätze mehr frei!");
      return false;
    } return true;
  }

  public void ausgeben() {
    System.out.println("Wettbewerb: "+pos+" von "+teams.length+" Teams, "+anzahlLaeufer+" Läufer");
    for(int i=0; i<pos; i++) {
      System.out.println(teams[i]);
    }
  }

  public void austragen() {
    if(pos<1) System.err.println("Der Wettbewerb hat keine Teilnehmer! Mindestens ein Team wird benötigt.");
    if(!ausgetragen) {
      for (int i=0; i<pos; i++) teams[i].setLaufzeit(Math.random() * 10 + 100);
      ausgetragen=true;
    }
  }
  public void beenden() {
    for(int i=0; i<pos; i++) {
      teams[i].setLaufzeit(0);
      teams[i].leereNaechster();
    }
  }

  public Team schnellstesTeam() { return schnellstesTeam(null); }
  public Team schnellstesTeam(Geschlecht geschlecht) {
    if(!ausgetragen) {
      System.err.println("Der Wettbewerb wurde noch nicht ausgetragen! Keine Ergebnisse!");
      return null;
    }
    Team schnellstes = null;
    for(Team team : teams)
      if((geschlecht == null || team.reinesTeam() == geschlecht)
        && (schnellstes == null || team.getLaufzeit() < schnellstes.getLaufzeit()))
        schnellstes = team;
    return schnellstes;
  }
}
