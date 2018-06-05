using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations;
using System.Linq;
using System.Web;

namespace Spengergasse.MusicMetaWebApp.Models {

  [MetadataType(typeof(SongMetadata))]
  public partial class Song {}

  public class SongMetadata {
    [Display(Name = "ID")]
    public int Id { get; set; }

    [Display(Name = "Title")]
    public string Title { get; set; }

    [Display(Name = "Artist ID")]
    public Nullable<int> ArtistId { get; set; }
    
    [Display(Name = "Album ID")]
    public Nullable<int> AlbumId { get; set; }

    [Display(Name = "Genre")]
    public string Genre { get; set; }

    [Display(Name = "Disc No")]
    public Nullable<int> DiscNo { get; set; }
    
    [Display(Name = "Track No")]
    public Nullable<int> TrackNo { get; set; }

    [Display(Name = "Release date")]
    public Nullable<System.DateTime> ReleaseDate { get; set; }

    [Display(Name = "Comment")]
    public string Comment { get; set; }

    [Display(Name = "Album")]
    public virtual Album Album { get; set; }

    [Display(Name = "Artist")]
    public virtual Artist Artist { get; set; }

    [Display(Name = "Comments")]
    public virtual ICollection<SongComment> SongComments { get; set; }
  }
}