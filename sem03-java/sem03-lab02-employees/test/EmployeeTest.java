import org.junit.*;

import static org.junit.Assert.*;

/**
 * @author Samuel Kaiser
 * @since 9/26/2016
 */
public class EmployeeTest {
  @Test
  public void test() {
    System.out.println("Print");
    Employee employee = new Employee("Marko Cavic", 5000);
    String str = employee.toString();
    System.out.println(str);
    assert str.equals("Employee{taxId=1, name='Marko Cavic', salary=5000}");

    System.out.println();

    System.out.println("Second employee has ID 2");
    Employee second = new Employee("Michael Größ", 5000);
    assert second.taxId == 2;

    System.out.println();

    System.out.println("Salary increase (5000 + 1%)");
    System.out.println("Before: " + employee);
    employee.salaryIncrease();
    System.out.println("After: " + employee);
    assert employee.getSalary() == 5050;

    System.out.println();

    System.out.println("Alter increase percentage (from 1% to 10%)");
    Employee.setIncreasingPercentage(10);
    assert Employee.getIncreasingPercentage() == 10;

    System.out.println();

    System.out.println("Another salary increase (5050 + 10% = 5555)");
    employee.salaryIncrease();
    assert employee.getSalary() == 5555;
  }
}