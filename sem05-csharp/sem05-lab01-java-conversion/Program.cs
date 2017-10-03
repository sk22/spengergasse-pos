using System;

namespace sem05_lab01_java_conversion {
  class Program {
    static void Main(string[] args) {
      var emp2 = new Employee();

      var wemp2 = new WageEmployee("Fred") {
        Wage = 224,
        Hours = 24
      };

      var semp2 = new SalesPerson("Wilma") {
        SalesMade = 1234,
        Commission = 0.3M,
        Wage = 224,
        Hours = 24
      };

      var man2 = new Manager("Boss") {
        WeeklySalary = 2500.5M
      };

      Console.WriteLine(emp2);

      Console.WriteLine(emp2.Name + " verdient " + emp2.ComputePay());
      Console.WriteLine(wemp2.Name + " verdient " + wemp2.ComputePay());
      Console.WriteLine(semp2.Name + " verdient " + semp2.ComputePay());
      Console.WriteLine(man2.Name + " verdient " + man2.ComputePay());
      Console.WriteLine(man2.Name + " verdient immer noch " + ((Employee) man2).ComputePay());

      Console.ReadKey(true);
    }
  }
}
