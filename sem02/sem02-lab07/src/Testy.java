import java.util.logging.*;

/**
 * @author Samuel Kaiser <samuel.kaiser01@gmail.com>
 * @since 2016-05-04
 */
public class Testy {

  public static void main(String[] args) {
    System.setErr(System.out);
    Support support;
    Ticket ticket;
    {
      int amount = -5;
      head("Creating new Support with amount of possible tickets: "+amount);
      support = new Support(amount);
    }
    {
      try {
        {
          head("Adding Ticket to Support");
          ticket = new Ticket(new Customer("Stefan", "Kormann"), "Frau Professor? Ich habe ein Problem!", Priority.MAX);
          ticket.print();
          support.add(ticket);
        }
        {
          head("Creating and adding another Ticket to Support");
          ticket = new Ticket(new Customer("Bill", "Gates"), "My PC is not working", Priority.NORMAL);
          ticket.print();
          support.add(ticket);
        }
        {
          head("Listing all added Tickets");
          support.print();
        }
        {
          head("Getting and deleting oldest Ticket");
          support.delete().print();
        }
        {
          head("Adding 12 new Tickets (only 10 possible)");
          support.addAll(
            new Ticket(new Customer("Le", "Me"), "Someone stole my breakfast!", Priority.NORMAL),
            new Ticket(new Customer("Le", "You"), "I'm hungry.", Priority.MIN),
            new Ticket(new Customer("Not", "Me"), "It's 00:50. Why am I doing this?", Priority.NORMAL),
            new Ticket(Customer.EXAMPLE, "I am a phishing mail!", Priority.MAX),
            new Ticket(Customer.EXAMPLE, "My creativity is nachlassing. I need help!", Priority.MIN),
            new Ticket(Customer.EXAMPLE, "a", Priority.NORMAL),
            new Ticket(Customer.EXAMPLE, "b", Priority.MAX),
            new Ticket(Customer.EXAMPLE, "c", Priority.MIN),
            new Ticket(Customer.EXAMPLE, "d", Priority.MIN),
            new Ticket(Customer.EXAMPLE, "e", Priority.NORMAL),
            new Ticket(Customer.EXAMPLE, "f", Priority.MIN)
          );
        }
        {
          head("Popping next Ticket with Priority: MAX");
          support.delete(Priority.MAX).print();
        }
        {
          head("Popping another Ticket with Priority: MAX");
          support.delete(Priority.MAX).print();
        }
        {
          head("Printing all Tickets");
          support.print();
        }
        {
          head("Calculating processing time");
          System.out.println("All customers: "+support.getTime());
          System.out.println("Customer.EXAMPLE: "+support.getTime(Customer.EXAMPLE));
        }
        {
          head("Clearing all Tickets from Customer.EXAMPLE");
          support.clear(Customer.EXAMPLE);
          support.print();
        }
        {
          head("Clearing all remaining Tickets");
          support.clear();
          support.print();
        }
      } catch(Exception e) {
        e.printStackTrace();
      }
    }
  }
  private static void head(String head) {
    System.out.println("\n ### "+head);
  }
}
