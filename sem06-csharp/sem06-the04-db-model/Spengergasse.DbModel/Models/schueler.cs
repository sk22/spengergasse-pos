//------------------------------------------------------------------------------
// <auto-generated>
//     This code was generated from a template.
//
//     Manual changes to this file may cause unexpected behavior in your application.
//     Manual changes to this file will be overwritten if the code is regenerated.
// </auto-generated>
//------------------------------------------------------------------------------

namespace Spengergasse.DbModel.Models
{
    using System;
    using System.Collections.Generic;
    
    public partial class schueler
    {
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2214:DoNotCallOverridableMethodsInConstructors")]
        public schueler()
        {
            this.klassens = new HashSet<klassen>();
            this.klassens1 = new HashSet<klassen>();
            this.pruefungens = new HashSet<pruefungen>();
        }
    
        public short S_SCHNR { get; set; }
        public string S_Name { get; set; }
        public string S_Vorname { get; set; }
        public Nullable<System.DateTime> S_Gebdat { get; set; }
        public string S_Adresse { get; set; }
        public string S_K_Klasse { get; set; }
    
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<klassen> klassens { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<klassen> klassens1 { get; set; }
        public virtual klassen klassen { get; set; }
        [System.Diagnostics.CodeAnalysis.SuppressMessage("Microsoft.Usage", "CA2227:CollectionPropertiesShouldBeReadOnly")]
        public virtual ICollection<pruefungen> pruefungens { get; set; }
    }
}
