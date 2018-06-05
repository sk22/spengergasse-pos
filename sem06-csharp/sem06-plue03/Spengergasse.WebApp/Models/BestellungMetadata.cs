using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Spengergasse.WebApp.Models {
  [MetadataType(typeof(BestellungMetadata))]
  public partial class Bestellungen { }

  public class BestellungMetadata {
    [Display(Name = "Best.-Nr.")]
    [Required]
    public int Bestell_Nrv7 { get; set; }

    [Display(Name = "Kunden-Code")]
    [Required]
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
  }
}