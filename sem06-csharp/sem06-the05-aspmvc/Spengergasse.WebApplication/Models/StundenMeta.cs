using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Spengergasse.WebApplication.Models {
  [MetadataType(typeof(StundenMeta))]
  public partial class stunden { }

  public class StundenMeta {
    [Display(Name = "Klasse")]
    [Required]
    public string ST_K_Klasse { get; set; }

    [Display(Name = "Lehrer")]
    [Required]
    public string ST_L_Lehrer { get; set; }

    [Display(Name = "Fach")]
    [Required]
    public string ST_G_Fach { get; set; }

    [Display(Name = "Stunde")]
    [Required]
    public string ST_Stunde { get; set; }

    [Display(Name = "Raum")]
    [Required]
    public string ST_R_Raum { get; set; }
  }
}