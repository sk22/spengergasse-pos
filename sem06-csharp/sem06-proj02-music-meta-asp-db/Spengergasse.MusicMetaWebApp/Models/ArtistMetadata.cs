using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Spengergasse.MusicMetaWebApp.Models {
  [MetadataType(typeof(ArtistMetadata))]
  public partial class Artist { }

  public class ArtistMetadata {
    [Display(Name = "ID")]
    [Required]
    public int Id { get; set; }

    [Display(Name = "Name")]
    [Required]
    public string Name { get; set; }

    [Display(Name = "Biography")]
    public string Biography { get; set; }

    [Display(Name = "Origin")]
    public string Origin { get; set; }

    [Display(Name = "Established")]
    [DataType(DataType.Date)]
    [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
    public DateTime? EstablishDate { get; set; }

    [Display(Name = "Dissolved")]
    [DataType(DataType.Date)]
    [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
    public DateTime? DissolveDate { get; set; }

    [Display(Name = "Active")]
    public bool? Active { get; set; }

    [Display(Name = "Albums")]
    public virtual ICollection<Album> Albums { get; set; }

    [Display(Name = "Members")]
    public virtual ICollection<ArtistMember> ArtistMembers { get; set; }

    [Display(Name = "Songs")]
    public virtual ICollection<Song> Songs { get; set; }
  }
}