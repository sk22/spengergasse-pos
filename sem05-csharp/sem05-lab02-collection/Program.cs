using System;
using System.Collections.Generic;
using System.Linq;

namespace sem05_lab02_collection {
  class Program {
    static void Main(string[] args) {
      var employees = new EmployeeCollection {
        new Employee(),
        new WageEmployee("Fred") {
          Wage = 224,
          Hours = 24
        },
        new SalesPerson("Wilma") {
          SalesMade = 1234,
          Commission = 0.3M,
          Wage = 224,
          Hours = 24
        },
        new Manager("Boss") { WeeklySalary = 5000.5M }
      };

      employees.ForEach(Console.WriteLine);

      Console.WriteLine();

      Console.WriteLine(employees.GetEmployeePercentages());

      Console.WriteLine();

      employees.Sort();
      Console.WriteLine("Sorted by name: " + employees.ToString());

      employees.Sort(new EmployeeComparer());
      Console.WriteLine("Sorted by pay via Comparer: " + employees.ToString());

      employees.Sort((x, y) => x.ComputePay().CompareTo(y.ComputePay()));
      Console.WriteLine("Sorted by pay via Lambda: " + employees.ToString());

      Console.ReadKey(true);
    }

    private static void PrintList(List<Employee> list) => Console.WriteLine();
  }

  public class EmployeeComparer : IComparer<Employee> {
    public int Compare(Employee x, Employee y) => x.ComputePay().CompareTo(y.ComputePay());
  }
}
