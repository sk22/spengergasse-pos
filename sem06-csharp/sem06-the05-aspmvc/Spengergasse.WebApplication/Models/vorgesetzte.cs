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
    
    public partial class vorgesetzte
    {
        public string V_L_Vorg { get; set; }
        public string V_Art { get; set; }
        public string V_L_Unt { get; set; }
    
        public virtual lehrer lehrer { get; set; }
        public virtual lehrer lehrer1 { get; set; }
    }
}
