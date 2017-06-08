import org.junit.*;

/**
 * @author Samuel Kaiser <samuel.kaiser01@gmail.com>
 * @since 2016-06-01
 */
public class WettbewerbTest {
  private Wettbewerb wettbewerb;

  @Before
  public void setUp() throws Exception {
    System.setErr(System.out);
    wettbewerb = new Wettbewerb(4, 2);
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void test() throws Exception {
    Team team;
    {
      System.out.println("\n# Team");
      team = new Team(new Laeufer(Geschlecht.MAENNLICH), new Laeufer(Geschlecht.WEIBLICH));
      System.out.println(team);
    }
    {
      System.out.println("\n# Wettbewerb");
      wettbewerb.anmelden(team);
      wettbewerb.ausgeben();
    }
    {
      System.out.println("\n# Neues Team");
      team = new Team(new Laeufer(Geschlecht.WEIBLICH), new Laeufer(Geschlecht.WEIBLICH));
      System.out.println(team);
      wettbewerb.anmelden(team);
    }
    {
      System.out.println("\n# Wettbewerb");
      wettbewerb.ausgeben();
    }
    int anz;
    {
      System.out.printf("\n# Anmelden von %d weiteren Teams\n", anz=5);
      while(anz-- > 0) wettbewerb.anmelden(new Team(new Laeufer(Geschlecht.WEIBLICH), new Laeufer(Geschlecht.WEIBLICH)));
    }
    {
      System.out.println("\n# Austragen");
      wettbewerb.austragen();
      wettbewerb.ausgeben();
    }
    {
      System.out.println("\n# Schnellstes Damenteam");
      System.out.println(team = wettbewerb.schnellstesTeam(Geschlecht.WEIBLICH));
    }
    {
      System.out.println("\n# Schnellstes Herrenteam (null, weil kein reines Herrenteam existiert)");
      System.out.println(wettbewerb.schnellstesTeam(Geschlecht.MAENNLICH));
    }
    {
      System.out.println("\n# Beenden des Wettbewerbs");
      wettbewerb.beenden();
      System.out.println("Sieger-Damenteam:");
      System.out.println(team);
    }
  }

}