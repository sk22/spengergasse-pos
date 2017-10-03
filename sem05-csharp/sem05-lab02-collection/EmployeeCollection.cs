using System;
using System.Collections.Generic;
using System.Linq;

namespace sem05_lab02_collection {
  public class EmployeeCollection<T> : List<T> where T : Employee {
    public override string ToString() => string.Join(", ", this);

    public string GetEmployeePercentages() {
      var sum = this.Sum(e => e.ComputePay());
      var lineLength = this.Select(e => e.ToString().Length).Max();
      return this.Select(e =>
        (e.ToString() + ' ').PadRight(lineLength + 1, '.') + "... "
          + string.Format("{0,5:f}%", Math.Round(e.ComputePay() / sum * 100))
        )
      .ToList()
      .Aggregate((pre, cur) => pre + '\n' + cur);
    }
  }
}
