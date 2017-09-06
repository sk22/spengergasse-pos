import org.junit.Test;

import java.util.Arrays;

/**
 * @author Samuel Kaiser
 * @since 9/26/2016
 */
public class CompaniesTest {
  @Test
  public void test() {
    System.out.println("Creating 3 companies");
    Company[] companies = {
      new Company("Company 1", "First Avenue 1"),
      new Company("Company 2", "Second Street 2"),
      new Company("Company 3", "Third Avenue 3")
    };
    System.out.println(Arrays.toString(companies));

    System.out.println();

    System.out.println("Adding Employees");

    companies[0].employ(new Employee("One", 1100));
    companies[0].employ(new Employee("Two", 1200));
    companies[0].employ(new Employee("Three", 1300));

    companies[1].employ(new Employee("Uno", 2100));
    companies[1].employ(new Employee("Dos", 2200));
    companies[1].employ(new Employee("Tres", 2300));

    companies[2].employ(new Employee("Eins", 3100));
    companies[2].employ(new Employee("Zwei", 3200));
    companies[2].employ(new Employee("Drei", 3300));

    System.out.println(Arrays.toString(companies));

    System.out.println();

    System.out.println("Drei has Tax ID #9");
    System.out.println(companies[2].getEmployee(2));
    assert companies[2].getEmployee(2).taxId == 9;

    System.out.println();

    Employee one = companies[0].getEmployee(0);

    System.out.println("Employing One in Company 2");
    companies[1].employ(one);
    System.out.println(one);
    assert one.getCompanies().size() == 2;

    System.out.println();

    System.out.println("Employing One in Company 3");
    companies[2].employ(one);
    System.out.println(one);
    assert one.getCompanies().size() == 3;

    System.out.println();

    System.out.println("Firing One from Company 2");
    companies[1].fire(one.taxId);
    System.out.println(one);
    assert one.getCompanies().size() == 2;

    System.out.println("Active Counter is still 9 because the Employee is still employed in other companies");
    System.out.println("Active Employees: " + Employee.activeCounter);
    System.out.println("One's Companies: " + one.getCompanies().size());

    System.out.println();

    System.out.println("Retiring One from Company 1");
    companies[0].retire(one.taxId);
    System.out.println("One's Companies: " + one.getCompanies().size());
    assert one.getCompanies().size() == 0;
    System.out.println("One: " + one);

  }
}
