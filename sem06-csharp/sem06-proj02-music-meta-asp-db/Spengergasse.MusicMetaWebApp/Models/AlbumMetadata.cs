using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Spengergasse.MusicMetaWebApp.Models {
  [MetadataType(typeof(AlbumMetadata))]
  public partial class Album { }

  public class AlbumMetadata {
    [Display(Name = "ID")]
    [Required]
    public int Id { get; set; }

    [Display(Name = "Name")]
    [Required]
    public string Name { get; set; }

    [Display(Name = "Artist ID")]
    public int? ArtistId { get; set; }

    [Display(Name = "Description")]
    public string Description { get; set; }

    [Display(Name = "Released")]
    [DataType(DataType.Date)]
    [DisplayFormat(DataFormatString = "{0:yyyy-MM-dd}", ApplyFormatInEditMode = true)]
    public DateTime? Released { get; set; }

    [Display(Name = "Artist")]
    public virtual Artist Artist { get; set; }

    [Display(Name = "Ratings")]
    public virtual ICollection<AlbumRating> AlbumRatings { get; set; }
    
    [Display(Name = "Songs")]
    public virtual ICollection<Song> Songs { get; set; }
  }
}