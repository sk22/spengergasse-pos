namespace sem05_lab01_java_conversion {
  public class Manager : Employee {

    // Konstruktoren
    public Manager(string n) : base(n) => WeeklySalary = 0;

    public decimal WeeklySalary { get; set; }

    public Manager() => WeeklySalary = 0;

    public override decimal ComputePay() => WeeklySalary;
  }
}
