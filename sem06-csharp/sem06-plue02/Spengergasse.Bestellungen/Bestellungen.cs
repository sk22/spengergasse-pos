//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Spengergasse.Kundenbestellungen
{
    using System;
    using System.Collections.Generic;
    
    public partial class Bestellungen
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Bestellungen()
        {
            this.Bestelldetails = new HashSet<Bestelldetail>();
        }
    
        public int Bestell_Nrv7 { get; set; }
        public string Kunden_Codeg9 { get; set; }
        public Nullable<int> Personal_Nrk3 { get; set; }
        public Nullable<System.DateTime> Bestelldatums1 { get; set; }
        public Nullable<System.DateTime> Lieferdatumm7 { get; set; }
        public Nullable<System.DateTime> Versanddatump2 { get; set; }
        public Nullable<int> VersandUeberf7 { get; set; }
        public Nullable<decimal> Frachtkostenk1 { get; set; }
        public string Empfaengerp1 { get; set; }
        public string Straßem2 { get; set; }
        public string Ortw9 { get; set; }
        public string Regionk1 { get; set; }
        public string PLZt5 { get; set; }
        public string Bestimmungslandr4 { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Bestelldetail> Bestelldetails { get; set; }
        public virtual Kunden Kunden { get; set; }
        public virtual Personal Personal { get; set; }
        public virtual Versandfirman Versandfirman { get; set; }
    }
}
