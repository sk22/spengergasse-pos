
/**
 * @author Samuel Kaiser <samuel.kaiser01@gmail.com>
 * @since 2016-05-04
 */
public class Ticket {
  private String problem;
  private Customer customer;
  private Priority priority;
  private int time;

  private void setProblem(String problem) throws Exception {
    if(problem == null || problem.isEmpty()) throw new Exception("Invalid problem (null or empty given)!");
    this.problem = problem;
  }

  private void setCustomer(Customer customer) throws Exception {
    if(customer == null) throw new Exception("Invalid customer! (null given)");
    this.customer = customer;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public void setTime(int time) throws Exception {
    if(time>0) this.time = time;
    else throw new Exception("Invalid time! Must be greater than 0.");
  }

  public String getProblem() {
    return problem;
  }

  public Customer getCustomer() {
    return customer;
  }

  public Priority getPriority() {
    return priority;
  }


  public int getTime() {
    return time;
  }

  public Ticket(Customer customer, String problem, Priority priority) throws Exception {
    if(customer == null || problem == null || priority == null) throw new Exception("Invalid request!");
    setProblem(problem);
    setPriority(priority);
    setCustomer(customer);
    setTime(Support.AVERAGE_TIME);
  }

  public void print() {
    System.out.println(this+":\n" +
      "- "+customer+": "+customer.getFirstName()+" "+customer.getLastName()+"\n" +
      "- Priority: "+priority.name()+"\n" +
      "- Time: "+time+" mins\n" +
      "- Problem: "+problem);
  }
}
