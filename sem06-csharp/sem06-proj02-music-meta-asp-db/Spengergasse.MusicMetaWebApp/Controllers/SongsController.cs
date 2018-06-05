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
  public class SongsController : Controller {
    private HIF3bkaiserEntities db = new HIF3bkaiserEntities();

    // GET: Songs
    public ActionResult Index(string order) {
      ViewBag.CurrentSort = order;
      ViewBag.SortByTitle = "title";
      ViewBag.SortByArtist = "artist";
      ViewBag.SortByAlbum = "album";
      ViewBag.SortByGenre = "genre";

      var songs = db.Songs.Include(s => s.Album).Include(s => s.Artist);
      if (order != null && order != "") {
        switch (order) {
          case "title": songs = songs.OrderBy(s => s.Title); break;
          case "artist": songs = songs.OrderBy(s => s.Artist.Name); break;
          case "album": songs = songs.OrderBy(s => s.Album.Name); break;
          case "genre": songs = songs.OrderBy(s => s.Genre); break;
        }
      }
      return View(songs.ToList());
    }

    // GET: Songs/Details/5
    public ActionResult Details(int? id)
    {
        if (id == null)
        {
            return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
        }
        Song song = db.Songs.Find(id);
        if (song == null)
        {
            return HttpNotFound();
        }
        return View(song);
    }

    // GET: Songs/Create
    public ActionResult Create()
    {
        ViewBag.AlbumId = new SelectList(db.Albums, "Id", "Name");
        ViewBag.ArtistId = new SelectList(db.Artists, "Id", "Name");
        return View();
    }

    // POST: Songs/Create
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Create([Bind(Include = "Id,Title,ArtistId,AlbumId,Genre,DiscNo,TrackNo,ReleaseDate,Comment")] Song song)
    {
        if (ModelState.IsValid)
        {
            db.Songs.Add(song);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        ViewBag.AlbumId = new SelectList(db.Albums, "Id", "Name", song.AlbumId);
        ViewBag.ArtistId = new SelectList(db.Artists, "Id", "Name", song.ArtistId);
        return View(song);
    }

    // GET: Songs/Edit/5
    public ActionResult Edit(int? id)
    {
        if (id == null)
        {
            return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
        }
        Song song = db.Songs.Find(id);
        if (song == null)
        {
            return HttpNotFound();
        }
        ViewBag.AlbumId = new SelectList(db.Albums, "Id", "Name", song.AlbumId);
        ViewBag.ArtistId = new SelectList(db.Artists, "Id", "Name", song.ArtistId);
        return View(song);
    }

    // POST: Songs/Edit/5
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Edit([Bind(Include = "Id,Title,ArtistId,AlbumId,Genre,DiscNo,TrackNo,ReleaseDate,Comment")] Song song)
    {
        if (ModelState.IsValid)
        {
            db.Entry(song).State = EntityState.Modified;
            db.SaveChanges();
            return RedirectToAction("Index");
        }
        ViewBag.AlbumId = new SelectList(db.Albums, "Id", "Name", song.AlbumId);
        ViewBag.ArtistId = new SelectList(db.Artists, "Id", "Name", song.ArtistId);
        return View(song);
    }

    // GET: Songs/Delete/5
    public ActionResult Delete(int? id)
    {
        if (id == null)
        {
            return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
        }
        Song song = db.Songs.Find(id);
        if (song == null)
        {
            return HttpNotFound();
        }
        return View(song);
    }

    // POST: Songs/Delete/5
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public ActionResult DeleteConfirmed(int id)
    {
        Song song = db.Songs.Find(id);
        db.Songs.Remove(song);
        db.SaveChanges();
        return RedirectToAction("Index");
    }

    protected override void Dispose(bool disposing)
    {
        if (disposing)
        {
            db.Dispose();
        }
        base.Dispose(disposing);
    }
  }
}
