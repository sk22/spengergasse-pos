using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Spengergasse.WebApplication.Models {
  [MetadataType(typeof(KlasseMeta))]
  public partial class klassen { }

  public class KlasseMeta {
    [Display(Name = "Klassen-ID")]
    [Required]
    [StringLength(10)]
    public string K_ID { get; set; }

    [Display(Name = "Bezeichnung")]
    [Required]
    [StringLength(30, MinimumLength = 2, ErrorMessage = "Bezeichnung darf nicht leer sein")]
    public string K_Bez { get; set; }

    [Display(Name = "Klassensprecher")]
    public Nullable<short> K_S_Klaspr { get; set; }

    [Display(Name = "Klassensprecher Stv.")]
    public Nullable<short> K_S_Klasprstv { get; set; }

    [Display(Name = "Klassenvorstand")]
    [Required]
    public string K_L_Klavst { get; set; }
  }
}