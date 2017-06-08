/**
 * @author Samuel Kaiser <samuel.kaiser01@gmail.com>
 * @since 2016-05-04
 */
public class Customer {
  private String firstName;
  private String lastName;

  public static final Customer EXAMPLE = new Customer("John", "Doe");

  private void setFirstName(String firstName) { this.firstName = firstName; }
  private void setLastName(String lastName) { this.lastName = lastName; }

  public String getFirstName() { return firstName; }
  public String getLastName() { return lastName; }

  public Customer(String firstName, String lastName) {
    setFirstName(firstName);
    setLastName(lastName);
  }
}
