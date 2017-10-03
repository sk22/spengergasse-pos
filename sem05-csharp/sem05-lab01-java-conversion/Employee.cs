using System;

namespace sem05_lab01_java_conversion {
  public class Employee {
    // Attribute um Angestelltendaten zu speichern
    public string Name { get; set; }

    public Employee(String n) => Name = n;
    public Employee() : this("Hugo") { }


    public override string ToString() => Name;

    public virtual decimal ComputePay() => 0;
  }
}
