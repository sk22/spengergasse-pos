//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Spengergasse.Lieferanten
{
    using System;
    using System.Collections.Generic;
    
    public partial class Artikel
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public Artikel()
        {
            this.Bestelldetails = new HashSet<Bestelldetail>();
        }
    
        public int Artikel_Nre3 { get; set; }
        public string Artikelnamex5 { get; set; }
        public Nullable<int> Lieferanten_Nrt9 { get; set; }
        public Nullable<int> Kategorie_Nra6 { get; set; }
        public string Liefereinheitw8 { get; set; }
        public Nullable<decimal> Einzelpreisn5 { get; set; }
        public Nullable<short> Lagerbestandc8 { get; set; }
        public Nullable<short> BestellteEinheitenm1 { get; set; }
        public Nullable<short> Mindestbestandm5 { get; set; }
        public bool Auslaufartikelk2 { get; set; }
    
        public virtual Kategorien Kategorien { get; set; }
        public virtual Lieferanten Lieferanten { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<Bestelldetail> Bestelldetails { get; set; }
    }
}
