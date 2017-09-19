import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Samuel Kaiser
 * @since 9/26/2016
 */
public class Company {
  private String name;
  private String address;
  private List<Employee> employees;

  /**
   * Creates a new Company
   * @param name Company's name
   * @param address Company's address
   */
  public Company(String name, String address) {
    this.employees = new ArrayList<>();
    this.setName(name);
    this.setAddress(address);
  }

  /**
   * Gets the Employee corresponding to the given tax ID
   * @param taxId Employee's tax ID
   * @return Employee determined by the tax ID
   */
  public Employee getEmployee(long taxId) {
    List<Employee> result = this.employees.stream()
      .filter(employee -> employee.taxId == taxId)
      .collect(Collectors.toList());
    return result.size() > 0 ? result.get(0) : null;
  }

  public Employee getEmployee(int index) {
    return this.employees.get(index);
  }

  public void setName(String name) {
    if (name == null || name.length() == 0) {
      throw new IllegalArgumentException("Name must be a non-empty String.");
    }
    this.name = name;
  }

  public void setAddress(String address) {
    if (address == null || address.length() == 0) {
      throw new IllegalArgumentException("Name must be a non-empty String.");
    }
    this.address = address;
  }

  /**
   * Employs an Employee to the Company
   * @param employee Employee to employ
   */
  public void employ(Employee employee) {
    if (this.employees.stream().filter(empl -> empl.taxId == employee.taxId).count() > 0) {
      throw new IllegalArgumentException("Employee with this Tax ID is already employed.");
    }
    this.employees.add(employee);
    if (employee.getCompanies().size() == 0) {
      Employee.activeCounter++;
    }
    employee.addCompany(this);
  }

  /**
   * Retires an Employee and decrements the number of active Employees
   * after removing them from all Companies they are employed in.
   * @param taxId Employee's tax ID
   * @return Retired Employee or null
   */
  public Employee retire(long taxId) {
    Employee employee = this.getEmployee(taxId);
    if (employee == null) return null;
    this.employees.remove(employee);

    List<Company> companies = new ArrayList<>(employee.getCompanies());

    companies.forEach(company -> {
      employee.removeCompany(company);
      company.fire(employee.taxId);
    });

    if (employee.getCompanies().size() == 0) {
      Employee.activeCounter--;
    }
    return employee;
  }

  /**
   * Fires an Employee and decrements the number of active Employees
   * if they are not employed in any other Company.
   * @param taxId Employee's tax ID
   * @return Fired Employee or null
   */
  public Employee fire(long taxId) {
    Employee employee = this.getEmployee(taxId);
    if (employee == null) return null;
    this.employees.remove(employee);
    employee.removeCompany(this);
    if (employee.getCompanies().size() == 0) {
      Employee.activeCounter--;
    }
    return employee;
  }

  @Override
  public String toString() {
    return "Company{" +
      "name='" + name + '\'' +
      ", address='" + address + '\'' +
      ", employees=" + employees +
      '}';
  }
}
