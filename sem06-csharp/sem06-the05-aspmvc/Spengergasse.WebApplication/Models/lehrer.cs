//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Spengergasse.WebApplication.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class lehrer
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public lehrer()
        {
            this.klassens = new HashSet<klassen>();
            this.lehrer1 = new HashSet<lehrer>();
            this.pruefungens = new HashSet<pruefungen>();
            this.stundens = new HashSet<stunden>();
            this.vorgesetztes = new HashSet<vorgesetzte>();
            this.vorgesetztes1 = new HashSet<vorgesetzte>();
        }
    
        public string L_ID { get; set; }
        public string L_Name { get; set; }
        public string L_Vorname { get; set; }
        public Nullable<System.DateTime> L_Gebdat { get; set; }
        public Nullable<float> L_Gehalt { get; set; }
        public string L_L_Chef { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<klassen> klassens { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<lehrer> lehrer1 { get; set; }
        public virtual lehrer lehrer2 { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<pruefungen> pruefungens { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<stunden> stundens { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<vorgesetzte> vorgesetztes { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<vorgesetzte> vorgesetztes1 { get; set; }
    }
}
