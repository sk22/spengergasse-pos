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
    
    public partial class InvoiceLine
    {
        public int InvoiceLineIdi7 { get; set; }
        public int InvoiceIdr9 { get; set; }
        public int TrackIdv7 { get; set; }
        public decimal UnitPricew3 { get; set; }
        public int Quantityw6 { get; set; }
    
        public virtual Invoice Invoice { get; set; }
        public virtual Track Track { get; set; }
    }
}