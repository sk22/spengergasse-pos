//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Spengergasse.DbApp
{
    using System;
    using System.Collections.Generic;
    
    public partial class Employee
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Employee()
        {
            this.Customers = new HashSet<Customer>();
            this.Employee1 = new HashSet<Employee>();
        }
    
        public int EmployeeIdi8 { get; set; }
        public string LastNameg9 { get; set; }
        public string FirstNamey9 { get; set; }
        public string Titlex6 { get; set; }
        public Nullable<int> ReportsTop9 { get; set; }
        public Nullable<System.DateTime> BirthDatev7 { get; set; }
        public Nullable<System.DateTime> HireDatek4 { get; set; }
        public string Addresso6 { get; set; }
        public string Cityo1 { get; set; }
        public string Statey1 { get; set; }
        public string Countryd6 { get; set; }
        public string PostalCodea3 { get; set; }
        public string Phoneg9 { get; set; }
        public string Faxs2 { get; set; }
        public string Emailf7 { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Customer> Customers { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Employee> Employee1 { get; set; }
        public virtual Employee Employee2 { get; set; }
    }
}
