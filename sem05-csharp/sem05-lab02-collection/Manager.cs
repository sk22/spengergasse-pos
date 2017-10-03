namespace sem05_lab02_collection {
  public class Manager : Employee {
    public Manager(string n = null) : base(n) {
      WeeklySalary = 0;
    }

    public decimal WeeklySalary { get; set; }

    public Manager() => WeeklySalary = 0;

    public override decimal ComputePay() => WeeklySalary;
  }
}
