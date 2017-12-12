/**
 * TicketMachine models a naive ticket machine that issues
 * flat-fare tickets.
 * The price of a ticket is specified via the constructor.
 * It is a naive machine in the sense that it trusts its users
 * to insert enough money before trying to print a ticket.
 * It also assumes that users enter sensible amounts.
 *
 * @author Kaiser Samuel (kai17521@spengergasse.at)
 * @version 3
 * @date 14.10.2015
 */
public class TicketMachine
{
    // The price of a ticket from this machine with default value.
    private int price = 50;
    // The amount of money entered by a customer so far.
    private int balance;
    // The total amount of money collected by this machine.
    private int total;
    
    private boolean child;
    private boolean bike;

    /**
     * Create a machine that issues tickets of the given price.
     * Note that the price must be greater than zero, and there
     * are no checks to ensure this.
     */
    public TicketMachine(int ticketCost) {
        setPrice(ticketCost);
        setBalance(0);
        setTotal(0);
    }
    
    public TicketMachine() {
        setPrice(price);
        setBalance(0);
        setTotal(0);
    }

    
    public void setPrice(int price) {
        if(price>0) {
            this.price = price;
        } else {
            System.out.println("Invalid price. Using default value instead: " + this.price + "\n");
        }
    }
    
    public void setBalance(int balance) {
        if(balance>=0) {
            this.balance = balance;
        } else {
            System.out.println("Invalid balance.\n");
        }
    }
    
    public void setTotal(int total) {
        if(total>=0) {
            this.total = total;
        } else {
            System.out.println("Invalid value for total.\n");
        }
    }
    
    public void setChild(boolean child) {
        this.child = child;
    }
    
    public void setBike(boolean bike) {
        this.bike = bike;
    }
    
    /**
     * Return the price of a ticket.
     */
    public int getPrice() {
        if(child && bike) {
            return price;
        } else if(child) {
            return price/2;
        } else if(bike) {
            return price*2;
        } else {
            return price;
        }
    }

    /**
     * Return the amount of money already inserted for the
     * next ticket.
     */
    public int getBalance() {
        return balance;
    }

    public int getTotal() {
        return total;
    }
    
    public boolean getChild() {
        return child;
    }
    
    public boolean getBike() {
        return bike;
    }
    
    /**
     * Receive an amount of money in cents from a customer.
     */
    public void insertMoney(int amount) {
        if(amount>0) {
            setBalance(getBalance() + amount);
            System.out.print("You inserted " + amount + " cents. ");
            System.out.println("Current balance: " + getBalance() + " cents.\n");
        } else if(amount<0) {
            System.out.println("Invalid amount: Value must greater than 0!\n");
        } else {
            System.out.println("Invalid amount!\n");
        }
    }

    /**
     * Print a ticket.
     * Update the total collected and
     * reduce the balance to zero.
     */
    public void printTicket() {
        if(getBalance()>=getPrice()) {
            // Simulate the printing of a ticket.
            System.out.println("#####################");
            System.out.println("  The BlueJ Line");
            System.out.print("  ");
            // Individual output for each ticket type.
            if(getChild()) {
                System.out.print("Child ");
            }
            if(getBike()) {
                System.out.print("Bike ");
            }
            System.out.println("Ticket");
            
            System.out.println("  " + getPrice() + " cents");
            System.out.println("#####################");
            
            // Update the total and balance values.
            setTotal(getTotal() + getPrice());
            setBalance(getBalance() - getPrice());
            
            System.out.println("\nMoney remaining: " + getBalance() + " cents\n");
        } else {
            System.out.println("Not enough money!");
            System.out.println("Money required: " + getPrice() + " cents");
            System.out.println("Current balance: " + getBalance() + " cents");
            System.out.println("You need " + (getPrice()-getBalance()) + " more cents.\n");
            System.out.println();
        }
    }
    
    public int refundBalance() {
        if(getBalance()>0) {
            System.out.println("Here is your remaining money: " + balance + " cents\n");
            int refund = getBalance();
            balance = 0;
            return refund;
        } else {
            System.out.println("No money to refund!\n");
            return getBalance();
        }
    }
    
    public int empty() {
        int empty = total;
        System.out.println("Machine emptied: Recieved " + empty + " cents.\n");
        total = 0;
        return empty;
    }
}
