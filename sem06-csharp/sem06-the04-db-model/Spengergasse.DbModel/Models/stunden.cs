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
    
    public partial class stunden
    {
        public string ST_K_Klasse { get; set; }
        public string ST_L_Lehrer { get; set; }
        public string ST_G_Fach { get; set; }
        public string ST_Stunde { get; set; }
        public string ST_R_Raum { get; set; }
    
        public virtual gegenstaende gegenstaende { get; set; }
        public virtual klassen klassen { get; set; }
        public virtual lehrer lehrer { get; set; }
        public virtual raeume raeume { get; set; }
    }
}
