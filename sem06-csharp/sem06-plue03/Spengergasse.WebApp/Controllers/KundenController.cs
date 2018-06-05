using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Spengergasse.WebApp.Models;

namespace Spengergasse.WebApp.Controllers {
  [Authorize]
  public class KundenController : Controller {
    private KaiserNordwindEntities db = new KaiserNordwindEntities();

    // GET: Kunden
    public ActionResult Index() {
      return View(db.Kundens.ToList());
    }

    // GET: Kunden/Details/5
    public ActionResult Details(string id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Kunden kunden = db.Kundens.Find(id);
      if (kunden == null) {
        return HttpNotFound();
      }
      return View(kunden);
    }

    // GET: Kunden/Create
    public ActionResult Create() {
      return View();
    }

    // POST: Kunden/Create
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Create([Bind(Include = "Kunden_Codeq1,Firmal3,Kontaktpersonb8,Positionk9,Straßel8,Orts6,Regionx8,PLZy8,Landp1,Telefonn1,Telefaxw9")] Kunden kunden) {
      if (ModelState.IsValid) {
        db.Kundens.Add(kunden);
        db.SaveChanges();
        return RedirectToAction("Index");
      }

      return View(kunden);
    }

    // GET: Kunden/Edit/5
    public ActionResult Edit(string id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Kunden kunden = db.Kundens.Find(id);
      if (kunden == null) {
        return HttpNotFound();
      }
      return View(kunden);
    }

    // POST: Kunden/Edit/5
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Edit([Bind(Include = "Kunden_Codeq1,Firmal3,Kontaktpersonb8,Positionk9,Straßel8,Orts6,Regionx8,PLZy8,Landp1,Telefonn1,Telefaxw9")] Kunden kunden) {
      if (ModelState.IsValid) {
        db.Entry(kunden).State = EntityState.Modified;
        db.SaveChanges();
        return RedirectToAction("Index");
      }
      return View(kunden);
    }

    // GET: Kunden/Delete/5
    public ActionResult Delete(string id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Kunden kunden = db.Kundens.Find(id);
      if (kunden == null) {
        return HttpNotFound();
      }
      return View(kunden);
    }

    // POST: Kunden/Delete/5
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public ActionResult DeleteConfirmed(string id) {
      Kunden kunden = db.Kundens.Find(id);
      db.Kundens.Remove(kunden);
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
