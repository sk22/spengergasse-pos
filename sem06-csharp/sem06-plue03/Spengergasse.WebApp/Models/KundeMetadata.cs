using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Spengergasse.WebApp.Models {
  [MetadataType(typeof(KundeMetadata))]
  public partial class Kunden { }

  public class KundeMetadata {
    [Display(Name = "Kunden-Code")]
    [Required]
    public string Kunden_Codeq1 { get; set; }

    [Display(Name = "Firma")]
    [Required]
    public string Firmal3 { get; set; }

    [Display(Name = "Kontaktperson")]
    [Required]
    public string Kontaktpersonb8 { get; set; }

    [Display(Name = "Position")]
    [Required]
    public string Positionk9 { get; set; }

    [Display(Name = "Straße")]
    [Required]
    public string Straßel8 { get; set; }

    [Display(Name = "Ort")]
    [Required]
    public string Orts6 { get; set; }

    [Display(Name = "Region")]
    public string Regionx8 { get; set; }

    [Display(Name = "PLZ")]
    [Required]
    public string PLZy8 { get; set; }

    [Display(Name = "Land")]
    [Required]
    public string Landp1 { get; set; }

    [Display(Name = "Telefon")]
    [Required]
    public string Telefonn1 { get; set; }

    [Display(Name = "Telefax")]
    [Required]
    public string Telefaxw9 { get; set; }
  }
}