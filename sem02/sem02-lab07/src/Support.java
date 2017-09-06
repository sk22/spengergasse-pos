import java.util.ArrayList;
import java.util.logging.*;

/**
 * @author Samuel Kaiser <samuel.kaiser01@gmail.com>
 * @since 2016-05-04
 */
public class Support {
  private Ticket[] tickets;
  private int position;

  private static final int MAX_DEFAULT = 10;
  public static final int AVERAGE_TIME = 5; // Minutes

  public Support(int maxTickets) {
    if(maxTickets<1)
      System.err.println("Invalid maximum amount of tickets given! Using default value instead: "
        +(maxTickets = MAX_DEFAULT));
    this.tickets = new Ticket[maxTickets];
    this.position = 0;
  }

  public boolean add(Ticket ticket) {
    if(position>=tickets.length) {
      System.err.println("No more tickets can be accepted!");
      return false;
    } else if(ticket == null) {
      System.err.println("Null reference is not a valid instance of Ticket!");
      return false;
    }
    tickets[position] = ticket;
    position++;
    return true;
  }

  public boolean addAll(Ticket... tickets) {
    boolean bool = false;
    for(Ticket ticket : tickets) bool = add(ticket);
    return bool;
  }

  /**
   * Get oldest Ticket and delete
   * @return
   */
  public Ticket delete() {
    return delete(position-1);
  }
  public Ticket delete(Priority priority) {
    int pos = -1;
    if(priority == null) pos=0; else for(int i=0; i<position; i++) {
      if(tickets[i]!=null && tickets[i].getPriority().equals(priority)) { pos = i; break; }
    }
    if(pos>=0 && pos<position) {
      return delete(pos);
    } return null;
  }
  public Ticket delete(int pos) {
    Ticket deleted = tickets[pos];
    if(pos>=0 && pos<position-1) System.arraycopy(tickets, pos+1, tickets, pos, position-pos-1);
    tickets[position-1] = null; position--;
    return deleted;
  }

  public int count(Customer customer) {
    int count = 0;
    for(int i=0; i<position; i++) if(tickets[i].getCustomer() == customer) count++;
    return count;
  }


  public Ticket[] clear() {
    return clear(null);
  }
  /**
   * Clear all tickets by specified Customer
   * @param customer The customer whose tickets should be cleared
   * @return Cleared tickets
   */
  public Ticket[] clear(Customer customer) {
    Ticket[] array;
    if(customer == null) {
      array = tickets;
      tickets = new Ticket[tickets.length];
    } else {
      array = new Ticket[count(customer)];
      int iArray = 0; int position = this.position;
      Ticket[] tickets = this.tickets.clone();
      for(int i=0; i<position; i++) if(tickets[i].getCustomer() == customer) {
        array[iArray++] = tickets[i];
        delete(i);
      }
    }
    return array;

  }

  public Ticket[] get(Customer customer) {
    if(customer == null) return tickets;
    Ticket[] array = new Ticket[count(customer)];
    int iReturn = 0;
    for(int i=0; i<position; i++) {
      if(customer.equals(tickets[i].getCustomer())) array[iReturn++] = tickets[i];
    } return array;
  }

  public int getTime() {
    return getTime(null);
  }

  public int getTime(Customer customer) {
    int time = 0;
    for(int i=0; i<position; i++) {
      if(tickets[i] != null && (customer == null || customer.equals(tickets[i].getCustomer())))
        time+=tickets[i].getTime();
    } return time;
  }

  public void print() {
    print(null);
  }

  public void print(Priority priority) {
    for(Ticket ticket : tickets)
      if(ticket != null && (priority == null || ticket.getPriority().equals(priority))) ticket.print();
  }

}
