namespace sem05_lab02_collection {
  public class WageEmployee : Employee {

    public WageEmployee(string n = null) : base(n) {
      Wage = 0;
      Hours = 0;
    }

    public decimal Wage { get; set; }
    public decimal Hours { get; set; }

    public override decimal ComputePay() => Wage * Hours;
  }
}
