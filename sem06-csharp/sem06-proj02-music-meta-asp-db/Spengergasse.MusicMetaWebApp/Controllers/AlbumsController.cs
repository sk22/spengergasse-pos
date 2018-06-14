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
  public class AlbumsController : Controller {
    private HIF3bkaiserEntities db = new HIF3bkaiserEntities();

    // GET: Albums
    [AllowAnonymous]
    public ActionResult Index(string search) {
      var albums = db.Albums.Include(a => a.Artist);

      if (!string.IsNullOrWhiteSpace(search)) {
        ViewBag.Search = search;
        var searchLower = search.ToLower();
        albums = albums.Where(a => a.Name.ToLower().Contains(searchLower)
          || a.Artist.Name.ToLower().Contains(searchLower));
      }
      return View(albums.ToList());
    }

    // GET: Albums/Details/5
    [AllowAnonymous]
    public ActionResult Details(int? id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Album album = db.Albums.Find(id);
      if (album == null) {
        return HttpNotFound();
      }
      return View(album);
    }

    // GET: Albums/Create
    [Authorize]
    public ActionResult Create() {
      var id = (int) Session["id"];
      ViewBag.ArtistId = new SelectList(db.Artists.Where(a => a.Id == id || id == -1), "Id", "Name");
      return View();
    }

    // POST: Albums/Create
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Create([Bind(Include = "Id,Name,ArtistId,Description,Released")] Album album, string returnUrl) {
      if (ModelState.IsValid) {
        db.Albums.Add(album);
        db.SaveChanges();
        if (string.IsNullOrEmpty(returnUrl)) {
          return RedirectToAction("Index");
        } else {
          return Redirect(returnUrl);
        }
      }

      var id = (int) Session["id"];
      ViewBag.ArtistId = new SelectList(db.Artists.Where(a => a.Id == id || id == -1), "Id", "Name");
      return View(album);
    }

    // GET: Albums/Edit/5
    public ActionResult Edit(int? id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }

      Album album = db.Albums.Find(id);
      if (album == null) {
        return HttpNotFound();
      }

      int? userId = (int) Session["id"];
      if (album.ArtistId != userId && userId != -1) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }

      ViewBag.ArtistId = new SelectList(db.Artists.Where(a => a.Id == userId || userId == -1), "Id", "Name");
      return View(album);
    }

    // POST: Albums/Edit/5
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Edit([Bind(Include = "Id,Name,ArtistId,Description,Released")] Album album) {
      if (ModelState.IsValid) {
        var userId = (int) Session["id"];
        if (album.ArtistId != userId && userId != -1) {
          return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
        }
        db.Entry(album).State = EntityState.Modified;
        db.SaveChanges();
        return RedirectToAction("Index");
      }
      ViewBag.ArtistId = new SelectList(db.Artists, "Id", "Name", album.ArtistId);
      return View(album);
    }

    // GET: Albums/Delete/5
    public ActionResult Delete(int? id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Album album = db.Albums.Find(id);
      if (album == null) {
        return HttpNotFound();
      }
      var userId = (int) Session["id"];
      if (album.ArtistId != userId && userId != -1) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }
      return View(album);
    }

    // POST: Albums/Delete/5
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public ActionResult DeleteConfirmed(int id) {
      Album album = db.Albums.Find(id);
      var userId = (int) Session["id"];
      if (album.ArtistId != userId && userId != -1) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }
      db.Albums.Remove(album);
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
