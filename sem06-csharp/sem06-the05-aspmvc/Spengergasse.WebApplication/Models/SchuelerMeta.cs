using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Spengergasse.WebApplication.Models {
  [MetadataType(typeof(SchuelerMeta))]
  public partial class schueler { }

  public class SchuelerMeta {
    [Display(Name = "Schüler-Nr.")]
    public short S_SCHNR { get; set; }

    [Display(Name = "Nachname")]
    [Required]
    [StringLength(50, MinimumLength = 1, ErrorMessage = "Nachname darf nicht leer sein.")]
    public string S_Name { get; set; }

    [Display(Name = "Vorname")]
    [Required]
    [StringLength(50, MinimumLength = 1, ErrorMessage = "Vorname darf nicht leer sein.")]
    public string S_Vorname { get; set; }

    [Display(Name = "Geburtsdatum")]
    [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
    public Nullable<System.DateTime> S_Gebdat { get; set; }

    [Display(Name = "Adresse")]
    public string S_Adresse { get; set; }

    [Display(Name = "Klasse")]
    public string S_K_Klasse { get; set; }
  }
}