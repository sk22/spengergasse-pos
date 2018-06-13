using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Spengergasse.MusicMetaWebApp.Models;

namespace Spengergasse.MusicMetaWebApp.Controllers {
  [Authorize]
  public class SongsController : Controller {
    private HIF3bkaiserEntities db = new HIF3bkaiserEntities();

    [AllowAnonymous]
    // GET: Songs
    public ActionResult Index(string order, string genre, string search) {
      ViewBag.CurrentSort = order;
      ViewBag.SortByTitle = "title";
      ViewBag.SortByArtist = "artist";
      ViewBag.SortByAlbum = "album";
      ViewBag.SortByGenre = "genre";

      var songs = db.Songs.Include(s => s.Album).Include(s => s.Artist);
      
      if (!string.IsNullOrWhiteSpace(search)) {
        ViewBag.Search = search;
        var searchLower = search.ToLower();
        songs = songs.Where(s => s.Title.ToLower().Contains(searchLower)
          || s.Album.Name.ToLower().Contains(searchLower) || s.Artist.Name.ToLower().Contains(searchLower));
      }

      if (!string.IsNullOrEmpty(order)) {
        switch (order) {
          case "title":
            songs = songs.OrderBy(s => s.Title);
            break;
          case "artist":
            songs = songs.OrderBy(s => s.Artist.Name);
            break;
          case "album":
            songs = songs.OrderBy(s => s.Artist.Name).OrderBy(s => s.Album.Name);
            break;
          case "genre":
            songs = songs.OrderBy(s => s.Genre);
            break;
        }
      }

      if (!string.IsNullOrEmpty(genre)) {
        songs = songs.Where(s => s.Genre.ToLower() == genre.ToLower());
      }

      return View(songs.ToList());
    }

    // GET: Songs/Details/5
    [AllowAnonymous]
    public ActionResult Details(int? id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Song song = db.Songs.Find(id);
      if (song == null) {
        return HttpNotFound();
      }
      return View(song);
    }

    // POST: Songs/Details/5
    [HttpPost]
    [ValidateAntiForgeryToken]
    [AllowAnonymous]
    public ActionResult PostComment(int id, string comment, string username) {
      if (string.IsNullOrWhiteSpace(comment)) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Song song = db.Songs.Find(id);
      var artist = db.Artists.Find(Session["id"]);

      if (song == null) {
        return HttpNotFound();
      }
      if (ModelState.IsValid) {
        db.SongComments.Add(new SongComment {
          SongId = song.Id,
          Comment = comment,
          UserName = artist?.Name
        });
        db.SaveChanges();
      }
      return RedirectToAction("Details", new { id });
    }

    public ActionResult DeleteComment(int id, int song) {
      SongComment songComment = db.SongComments.Find(id);
      if (songComment == null) {
        return HttpNotFound();
      }

      var userId = (int) Session["id"];
      if (songComment.Song.ArtistId != userId && userId != -1) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }

      if (ModelState.IsValid) {
        db.SongComments.Remove(songComment);
        db.SaveChanges();
      }
      return RedirectToAction("Details", new { id = song });
    }

    // GET: Songs/Create
    public ActionResult Create() {
      var id = (int) Session["id"];
      ViewBag.AlbumId = new SelectList(db.Albums.Where(a => a.ArtistId == id || id == -1), "Id", "Name");
      ViewBag.ArtistId = new SelectList(db.Artists.Where(a => a.Id == id || id == -1), "Id", "Name");
      return View();
    }

    // POST: Songs/Create
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Create([Bind(Include = "Id,Title,ArtistId,AlbumId,Genre,DiscNo,TrackNo,ReleaseDate,Comment")] Song song) {
      var id = (int) Session["id"];
      if (id != song.ArtistId && id != -1) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }

      if (ModelState.IsValid) {
        db.Entry(song).State = EntityState.Modified;
        db.SaveChanges();
        return RedirectToAction("Index");
      }

      ViewBag.AlbumId = new SelectList(db.Albums.Where(a => a.ArtistId == id || id == -1), "Id", "Name");
      ViewBag.ArtistId = new SelectList(db.Artists.Where(a => a.Id == id || id == -1), "Id", "Name");
      return View(song);
    }

    // GET: Songs/Edit/5
    public ActionResult Edit(int? id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Song song = db.Songs.Find(id);

      var userId = (int) Session["id"];
      if (userId != song.ArtistId) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }

      if (song == null) {
        return HttpNotFound();
      }
      ViewBag.AlbumId = new SelectList(db.Albums.Where(a => a.ArtistId == userId || userId == -1), "Id", "Name");
      ViewBag.ArtistId = new SelectList(db.Artists.Where(a => a.Id == userId || userId == -1), "Id", "Name");
      return View(song);
    }

    // POST: Songs/Edit/5
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Edit([Bind(Include = "Id,Title,ArtistId,AlbumId,Genre,DiscNo,TrackNo,ReleaseDate,Comment")] Song song) {
      var userId = (int) Session["id"];
      if (song.ArtistId != userId && userId != -1) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }
      if (ModelState.IsValid) {
        db.Entry(song).State = EntityState.Modified;
        db.SaveChanges();
        return RedirectToAction("Index");
      }
      ViewBag.AlbumId = new SelectList(db.Albums.Where(a => a.ArtistId == userId || userId == -1), "Id", "Name");
      ViewBag.ArtistId = new SelectList(db.Artists.Where(a => a.Id == userId || userId == -1), "Id", "Name");
      return View(song);
    }

    // GET: Songs/Delete/5
    public ActionResult Delete(int? id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Song song = db.Songs.Find(id);
      var userId = (int) Session["id"];
      if (song.ArtistId != userId && userId != -1) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }
      if (song == null) {
        return HttpNotFound();
      }
      return View(song);
    }

    // POST: Songs/Delete/5
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public ActionResult DeleteConfirmed(int id) {
      Song song = db.Songs.Find(id);
      var userId = (int) Session["id"];
      if (song.ArtistId != userId && userId != -1) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }
      db.Songs.Remove(song);
      db.SaveChanges();
      return RedirectToAction("Index");
    }

    protected override void Dispose(bool disposing) {
      if (disposing) {
        db.Dispose();
      }
      base.Dispose(disposing);
    }
  }
}
