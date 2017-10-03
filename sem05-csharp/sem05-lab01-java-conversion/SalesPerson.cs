namespace sem05_lab01_java_conversion {

  public class SalesPerson : WageEmployee {
    // Konstruktoren
    public SalesPerson(string n) : base(n) {
      Commission = 0;
      SalesMade = 0;
    }

    public SalesPerson() : this(null) {}

    public decimal Commission { get; set; }

    public decimal SalesMade { get; set; }

    public override string ToString() => "Salesperson " + Name;

    public override decimal ComputePay() {
      return Commission * SalesMade + base.ComputePay();
    }
  }
}
