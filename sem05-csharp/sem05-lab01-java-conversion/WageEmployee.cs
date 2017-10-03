namespace sem05_lab01_java_conversion {

  public class WageEmployee : Employee {
    public WageEmployee(string n) : base(n) {
      Wage = 0;
      Hours = 0;
    }

    public WageEmployee() : base(null) {}

    public decimal Wage { get; set; }
    public decimal Hours { get; set; }

    public override decimal ComputePay() => Wage * Hours;
  }
}
