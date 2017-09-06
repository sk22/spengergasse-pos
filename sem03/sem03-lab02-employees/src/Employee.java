import com.sun.istack.internal.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Samuel Kaiser
 * @since 9/26/2016
 */
public class Employee {
  private static float increasingPercentage = 1;
  private static long counter;
  static long activeCounter;

  public final long taxId;
  private String name;
  private int salary;
  private List<Company> companies;

  /**
   * Constructs an Employee
   * @param name Employee's name
   * @param salary Salary
   */
  public Employee(String name, int salary) {
    this.setName(name);
    this.taxId = next();
    this.name = name;
    this.salary = salary;
    this.companies = new ArrayList<>();
  }

  /**
   * Returns the counter of all registered Employees
   * @return Counter
   */
  public static long getCounter() {
    return Employee.counter;
  }

  /**
   * Increments the counter for all Employees
   * @return Incremented counter
   */
  private long next() {
    return ++Employee.counter;
  }

  public static void setIncreasingPercentage(float percentage) {
    if (percentage <= 0) {
      throw new IllegalArgumentException("Percentage must be greater than 0.");
    } else if (percentage >= 20) {
      throw new IllegalArgumentException("Percentage must be less than 20.");
    }

    Employee.increasingPercentage = percentage;
  }

  /**
   * Sets the Employee's name
   * @param name Non-empty String
   */
  public void setName(String name) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("Name must be a non-empty String.");
    }
    this.name = name;
  }

  /**
   * Sets the Employee's salary
   * @param salary Greater than 0
   */
  public void setSalary(int salary) {
    if (salary <= 0) {
      throw new IllegalArgumentException("Salary must be greater than 0.");
    }
    this.salary = salary;
  }

  /**
   * Adds a Company the Employee is employed in
   * @param company Company to add
   */
  void addCompany(Company company) {
    this.companies.add(company);
  }

  /**
   * Removes a Company the Employee is employed in
   * @param company Company to remove
   */
  void removeCompany(Company company) {
    this.companies.remove(company);
  }

  /**
   * Increases the Employee's salary by the static increasingPercentage attribute
   */
  public void salaryIncrease() {
    this.salary += this.salary * (Employee.increasingPercentage / 100f);
  }

  public static float getIncreasingPercentage() {
    return increasingPercentage;
  }

  public String getName() {
    return name;
  }

  public int getSalary() {
    return salary;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "taxId=" + taxId +
        ", name='" + name + '\'' +
        ", salary=" + salary +
        ", companies.size=" + companies.size() +
        '}';
  }

  /**
   * Returns a list of the companies the Employee is employed to.
   * @return List of Companies
   */
  public List<Company> getCompanies() {
    return new ArrayList<>(companies);
  }
}
