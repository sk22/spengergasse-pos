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
  public class BestellungenController : Controller {
    private KaiserNordwindEntities db = new KaiserNordwindEntities();

    // GET: Bestellungen
    public ActionResult Index(string kunde, DateTime? datum) {
      var bestellungen = db.Bestellungens.Include(b => b.Kunden).Include(b => b.Personal).Include(b => b.Versandfirman);
      if (!String.IsNullOrEmpty(kunde)) {
        bestellungen = bestellungen.Where(b => b.Kunden_Codeg9 == kunde);
      }
      if (datum != null) {
        bestellungen = bestellungen.Where(b => b.Bestelldatums1 == datum);
      }

      ViewBag.kunde = new SelectList(db.Kundens, "Kunden_Codeq1", "Firmal3");

      return View(bestellungen.ToList());
    }

    // GET: Bestellungen/Details/5
    public ActionResult Details(int? id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Bestellungen bestellungen = db.Bestellungens.Find(id);
      if (bestellungen == null) {
        return HttpNotFound();
      }
      return View(bestellungen);
    }

    // GET: Bestellungen/Create
    public ActionResult Create() {
      ViewBag.Kunden_Codeg9 = new SelectList(db.Kundens, "Kunden_Codeq1", "Firmal3");
      ViewBag.Personal_Nrk3 = new SelectList(db.Personals, "Personal_Nrr3", "Nachnamel1");
      ViewBag.VersandUeberf7 = new SelectList(db.Versandfirmen, "Firmen_Nri1", "Firmae2");
      return View();
    }

    // POST: Bestellungen/Create
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Create([Bind(Include = "Bestell_Nrv7,Kunden_Codeg9,Personal_Nrk3,Bestelldatums1,Lieferdatumm7,Versanddatump2,VersandUeberf7,Frachtkostenk1,Empfaengerp1,Straßem2,Ortw9,Regionk1,PLZt5,Bestimmungslandr4")] Bestellungen bestellungen) {
      if (ModelState.IsValid) {
        db.Bestellungens.Add(bestellungen);
        db.SaveChanges();
        return RedirectToAction("Index");
      }

      ViewBag.Kunden_Codeg9 = new SelectList(db.Kundens, "Kunden_Codeq1", "Firmal3", bestellungen.Kunden_Codeg9);
      ViewBag.Personal_Nrk3 = new SelectList(db.Personals, "Personal_Nrr3", "Nachnamel1", bestellungen.Personal_Nrk3);
      ViewBag.VersandUeberf7 = new SelectList(db.Versandfirmen, "Firmen_Nri1", "Firmae2", bestellungen.VersandUeberf7);
      return View(bestellungen);
    }

    // GET: Bestellungen/Edit/5
    public ActionResult Edit(int? id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Bestellungen bestellungen = db.Bestellungens.Find(id);
      if (bestellungen == null) {
        return HttpNotFound();
      }
      ViewBag.Kunden_Codeg9 = new SelectList(db.Kundens, "Kunden_Codeq1", "Firmal3", bestellungen.Kunden_Codeg9);
      ViewBag.Personal_Nrk3 = new SelectList(db.Personals, "Personal_Nrr3", "Nachnamel1", bestellungen.Personal_Nrk3);
      ViewBag.VersandUeberf7 = new SelectList(db.Versandfirmen, "Firmen_Nri1", "Firmae2", bestellungen.VersandUeberf7);
      return View(bestellungen);
    }

    // POST: Bestellungen/Edit/5
    // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
    // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Edit([Bind(Include = "Bestell_Nrv7,Kunden_Codeg9,Personal_Nrk3,Bestelldatums1,Lieferdatumm7,Versanddatump2,VersandUeberf7,Frachtkostenk1,Empfaengerp1,Straßem2,Ortw9,Regionk1,PLZt5,Bestimmungslandr4")] Bestellungen bestellungen) {
      if (ModelState.IsValid) {
        db.Entry(bestellungen).State = EntityState.Modified;
        db.SaveChanges();
        return RedirectToAction("Index");
      }
      ViewBag.Kunden_Codeg9 = new SelectList(db.Kundens, "Kunden_Codeq1", "Firmal3", bestellungen.Kunden_Codeg9);
      ViewBag.Personal_Nrk3 = new SelectList(db.Personals, "Personal_Nrr3", "Nachnamel1", bestellungen.Personal_Nrk3);
      ViewBag.VersandUeberf7 = new SelectList(db.Versandfirmen, "Firmen_Nri1", "Firmae2", bestellungen.VersandUeberf7);
      return View(bestellungen);
    }

    // GET: Bestellungen/Delete/5
    public ActionResult Delete(int? id) {
      if (id == null) {
        return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
      }
      Bestellungen bestellungen = db.Bestellungens.Find(id);
      if (bestellungen == null) {
        return HttpNotFound();
      }
      return View(bestellungen);
    }

    // POST: Bestellungen/Delete/5
    [HttpPost, ActionName("Delete")]
    [ValidateAntiForgeryToken]
    public ActionResult DeleteConfirmed(int id) {
      Bestellungen bestellungen = db.Bestellungens.Find(id);
      db.Bestellungens.Remove(bestellungen);
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
