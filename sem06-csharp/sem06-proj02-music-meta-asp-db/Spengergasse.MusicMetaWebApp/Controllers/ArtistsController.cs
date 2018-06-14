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
  public class ArtistsController : Controller {
    private HIF3bkaiserEntities db = new HIF3bkaiserEntities();

    [AllowAnonymous]
    // GET: Artists
    public ActionResult Index(string search) {
      var artists = db.Artists.Include(a => a.ArtistMembers);

      if (!string.IsNullOrWhiteSpace(search)) {
        ViewBag.Search = search;
        var searchLower = search.ToLower();
        artists = artists.Where(a => a.Name.ToLower().Contains(searchLower)
          || a.ArtistMembers.Any(m => m.Member.Name.ToLower().Contains(searchLower)));
      }

      return View(artists.ToList());
    }

    // GET: Artists/Details/5
    [AllowAnonymous]
    public ActionResult Details(int? id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Artist artist = db.Artists.Find(id);
      if (artist == null) {
        return HttpNotFound();
      }
      return View(artist);
    }

    // GET: Artists/Create
    public ActionResult Create() {
      var id = (int) Session["id"];
      if (id != -1) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }
      return View();
    }

    // POST: Artists/Create
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Create([Bind(Include = "Id,Name,Biography,Origin,EstablishDate,DissolveDate,Active")] Artist artist) {
      var id = (int) Session["id"];
      if (id != -1) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }
      if (ModelState.IsValid) {
        db.Artists.Add(artist);
        db.SaveChanges();
        return RedirectToAction("Index");
      }

      return View(artist);
    }

    // GET: Artists/Edit/5
    public ActionResult Edit(int? id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }

      Artist artist = db.Artists.Find(id);
      if (artist == null) {
        return HttpNotFound();
      }
      int? userId = (int) Session["id"];
      if (artist.Id != userId && userId != -1) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }
      return View(artist);
    }

    // POST: Artists/Edit/5
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Edit([Bind(Include = "Id,Name,Biography,Origin,EstablishDate,DissolveDate,Active")] Artist artist) {
      if (ModelState.IsValid) {
        var userId = (int) Session["id"];
        if (artist.Id != userId && userId != -1) {
          return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
        }
        db.Entry(artist).State = EntityState.Modified;
        db.SaveChanges();
        return RedirectToAction("Index");
      }
      return View(artist);
    }

    // GET: Artists/Delete/5
    public ActionResult Delete(int? id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Artist artist = db.Artists.Find(id);
      if (artist == null) {
        return HttpNotFound();
      }
      var userId = (int) Session["id"];
      if (artist.Id != userId && userId != -1) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }
      return View(artist);
    }

    // POST: Artists/Delete/5
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public ActionResult DeleteConfirmed(int id) {
      Artist artist = db.Artists.Find(id);
      var userId = (int) Session["id"];
      if (artist.Id != userId && userId != -1) {
        return new HttpStatusCodeResult(HttpStatusCode.Forbidden);
      }
      db.Artists.Remove(artist);
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
