/**
 * @author Samuel Kaiser <samuel.kaiser01@gmail.com>
 * @since 2016-05-11
 */
public class Schildberger extends Support {
  public Schildberger(int maxAnfragen) {
    super(maxAnfragen);
  }
  public boolean ticketHinzufuegen(Ticket t) {
    return add(t);
  }
  public Ticket ticketEntfernen() {
    return delete();
  }
  public Ticket dringendesTicketEntfernen() {
    return delete(Priority.MAX);
  }
  public int durchschnittlicheArbeitszeit() {
    return AVERAGE_TIME;
  }
  public void entferneAlleTickets() {
    clear();
  }
  public Ticket[] ticketsFuerKunde(Customer kunde) {
    return get(kunde);
  }
}
