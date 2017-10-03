using System;

namespace sem05_lab02_collection {
  public class Employee : IComparable<Employee> {
    public string Name { get; set; }

    public Employee(string n = "Hugo") => Name = n;

    public override string ToString() => Name + " (" + this.GetType().Name + ")";

    public virtual decimal ComputePay() => 0;

    public int CompareTo(Employee other) => Name.CompareTo(other.Name);
  }
}
