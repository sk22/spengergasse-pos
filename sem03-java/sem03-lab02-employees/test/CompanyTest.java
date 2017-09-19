import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 9/26/2016
 */
public class CompanyTest {
  @Test
  public void test() {
    Company company = new Company("Hieden", "Baker Street 11b");

    Employee[] employees = {
      new Employee("Laurids", 89),
      new Employee("Lauridso", 32)
    };

    company.employ(employees[0]);
    company.employ(employees[1]);

    System.out.println("Two active Employees");
    assert Employee.activeCounter == 2;
    System.out.println("Two Employees");
    assert Employee.getCounter() == 2;
    System.out.println(company);

    System.out.println();

    System.out.println("Trying to employ Employee 1 again");

    try {
      company.employ(employees[0]);
      throw new AssertionError();
    } catch(IllegalArgumentException iae) {
      System.out.println("IllegalArgumentException was thrown: " + iae.getMessage());
    }

    System.out.println();
    System.out.println("Active Employees: " + Employee.activeCounter);
    assert Employee.activeCounter == 2;
    System.out.println();

    System.out.println("Retiring " + employees[1]);
    System.out.println(company);
    company.retire(employees[1].taxId);
    System.out.println("Active Employees: 1");
    assert Employee.activeCounter == 1;
    System.out.println("Registered Employees: 2");
    assert Employee.getCounter() == 2;

    System.out.println();

    System.out.println("Retired Employee's Companies");
    System.out.println(employees[1].getCompanies().size());
    assert employees[1].getCompanies().size() == 0;
  }
}