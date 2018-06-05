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
  public class BestelldetailsController : Controller {
    private KaiserNordwindEntities db = new KaiserNordwindEntities();

    // GET: Bestelldetails
    public ActionResult Index() {
      var bestelldetails = db.Bestelldetails.Include(b => b.Artikel).Include(b => b.Bestellungen);
      return View(bestelldetails.ToList());
    }

    // GET: Bestelldetails/Details/5
    public ActionResult Details(int? artikel, int? bestellung) {
      if (artikel == null || bestellung == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Bestelldetail bestelldetail = db.Bestelldetails.Find(artikel, bestellung);
      if (bestelldetail == null) {
        return HttpNotFound();
      }
      return View(bestelldetail);
    }

    // GET: Bestelldetails/Create
    public ActionResult Create() {
      ViewBag.Artikel_Nrk8 = new SelectList(db.Artikels, "Artikel_Nre3", "Artikelnamex5");
      ViewBag.Bestell_Nrn8 = new SelectList(db.Bestellungens, "Bestell_Nrv7", "Kunden_Codeg9");
      return View();
    }

    // POST: Bestelldetails/Create
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Create([Bind(Include = "Bestell_Nrn8,Artikel_Nrk8,Einzelpreisw2,Anzahlt2,Rabattm4")] Bestelldetail bestelldetail) {
      if (ModelState.IsValid) {
        db.Bestelldetails.Add(bestelldetail);
        db.SaveChanges();
        return RedirectToAction("Index");
      }

      ViewBag.Artikel_Nrk8 = new SelectList(db.Artikels, "Artikel_Nre3", "Artikelnamex5", bestelldetail.Artikel_Nrk8);
      ViewBag.Bestell_Nrn8 = new SelectList(db.Bestellungens, "Bestell_Nrv7", "Kunden_Codeg9", bestelldetail.Bestell_Nrn8);
      return View(bestelldetail);
    }

    // GET: Bestelldetails/Edit/5
    public ActionResult Edit(int? artikel, int? bestellung) {
      if (artikel == null || bestellung == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Bestelldetail bestelldetail = db.Bestelldetails.Find(artikel, bestellung);
      if (bestelldetail == null) {
        return HttpNotFound();
      }
      ViewBag.Artikel_Nrk8 = new SelectList(db.Artikels, "Artikel_Nre3", "Artikelnamex5", bestelldetail.Artikel_Nrk8);
      ViewBag.Bestell_Nrn8 = new SelectList(db.Bestellungens, "Bestell_Nrv7", "Kunden_Codeg9", bestelldetail.Bestell_Nrn8);
      return View(bestelldetail);
    }

    // POST: Bestelldetails/Edit/5
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Edit([Bind(Include = "Bestell_Nrn8,Artikel_Nrk8,Einzelpreisw2,Anzahlt2,Rabattm4")] Bestelldetail bestelldetail) {
      if (ModelState.IsValid) {
        db.Entry(bestelldetail).State = EntityState.Modified;
        db.SaveChanges();
        return RedirectToAction("Index");
      }
      ViewBag.Artikel_Nrk8 = new SelectList(db.Artikels, "Artikel_Nre3", "Artikelnamex5", bestelldetail.Artikel_Nrk8);
      ViewBag.Bestell_Nrn8 = new SelectList(db.Bestellungens, "Bestell_Nrv7", "Kunden_Codeg9", bestelldetail.Bestell_Nrn8);
      return View(bestelldetail);
    }

    // GET: Bestelldetails/Delete/5
    public ActionResult Delete(int? artikel, int? bestellung) {
      if (artikel == null || bestellung == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Bestelldetail bestelldetail = db.Bestelldetails.Find(artikel, bestellung);
      if (bestelldetail == null) {
        return HttpNotFound();
      }
      return View(bestelldetail);
    }

    // POST: Bestelldetails/Delete/5
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public ActionResult DeleteConfirmed(int artikel, int bestellung) {
      Bestelldetail bestelldetail = db.Bestelldetails.Find(artikel, bestellung);
      db.Bestelldetails.Remove(bestelldetail);
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
