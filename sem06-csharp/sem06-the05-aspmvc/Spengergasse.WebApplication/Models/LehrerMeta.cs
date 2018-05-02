using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Spengergasse.WebApplication.Models {
  [MetadataType(typeof(LehrerMeta))]
  public partial class lehrer { }

  public class LehrerMeta {

    [Display(Name = "Lehrer-ID")]
    [Required]
    public string L_ID { get; set; }

    [Display(Name = "Nachname")]
    [Required]
    public string L_Name { get; set; }
    
    [Display(Name = "Vorname")]
    [Required]
    public string L_Vorname { get; set; }

    [Display(Name = "Geburtsdatum")]
    public Nullable<System.DateTime> L_Gebdat { get; set; }

    [Display(Name = "Gehalt")]
    public Nullable<float> L_Gehalt { get; set; }

    [Display(Name = "Chef")]
    [Required]
    public string L_L_Chef { get; set; }
  }
}