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

    [Display(Name = "Betreuer")]
    [Required]
    public Nullable<int> Personal_Nrk3 { get; set; }

    [Display(Name = "Bestelldatum")]
    [DataType(DataType.Date)]
    [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
    public Nullable<System.DateTime> Bestelldatums1 { get; set; }

    [Display(Name = "Lieferdatum")]
    [DataType(DataType.Date)]
    [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
    public Nullable<System.DateTime> Lieferdatumm7 { get; set; }

    [Display(Name = "Versanddatum")]
    [DataType(DataType.Date)]
    [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
    public Nullable<System.DateTime> Versanddatump2 { get; set; }

    [Display(Name = "Versand über")]
    [Required]
    public Nullable<int> VersandUeberf7 { get; set; }

    [Display(Name = "Versandkosten")]
    [Required]
    public Nullable<decimal> Frachtkostenk1 { get; set; }

    [Display(Name = "Empfänger")]
    [Required]
    public string Empfaengerp1 { get; set; }

    [Display(Name = "Straße")]
    [Required]
    public string Straßem2 { get; set; }

    [Display(Name = "Ort")]
    [Required]
    public string Ortw9 { get; set; }

    [Display(Name = "Region")]
    [Required]
    public string Regionk1 { get; set; }

    [Display(Name = "PLZ")]
    [Required]
    public string PLZt5 { get; set; }

    [Display(Name = "Bestimmungsland")]
    [Required]
    public string Bestimmungslandr4 { get; set; }
  }
}