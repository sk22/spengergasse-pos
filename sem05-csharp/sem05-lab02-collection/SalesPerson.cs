namespace sem05_lab02_collection {
  public class SalesPerson : WageEmployee {
    public SalesPerson(string n = null) : base(n) {
      Commission = 0;
      SalesMade = 0;
    }

    public decimal Commission { get; set; }

    public decimal SalesMade { get; set; }

    public override decimal ComputePay() {
      return Commission * SalesMade + base.ComputePay();
    }
  }
}
