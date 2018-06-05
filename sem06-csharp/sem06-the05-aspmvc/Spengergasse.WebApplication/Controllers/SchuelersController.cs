using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Spengergasse.WebApplication.Models;

namespace Spengergasse.WebApplication.Controllers
{

    [Authorize]
    public class SchuelersController : Controller
    {
        private Schule2000Entities db = new Schule2000Entities();

        // GET: Schuelers
        public ActionResult Index(string search)
        {
            var schuelers = db.schuelers
              .Include(s => s.klassen)
              .Where(s => string.IsNullOrEmpty(search) ? true : s.S_Name.Contains(search));
            return View(schuelers.ToList());
        }

        // GET: Schuelers/Details/5
        public ActionResult Details(short? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            schueler schueler = db.schuelers.Find(id);
            if (schueler == null)
            {
                return HttpNotFound();
            }
            return View(schueler);
        }

        // GET: Schuelers/Create
        public ActionResult Create()
        {
            ViewBag.S_K_Klasse = new SelectList(db.klassens, "K_ID", "K_Bez");
            return View();
        }

        // POST: Schuelers/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "S_SCHNR,S_Name,S_Vorname,S_Gebdat,S_Adresse,S_K_Klasse")] schueler schueler)
        {
            if (ModelState.IsValid)
            {
                db.schuelers.Add(schueler);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.S_K_Klasse = new SelectList(db.klassens, "K_ID", "K_Bez", schueler.S_K_Klasse);
            return View(schueler);
        }

        // GET: Schuelers/Edit/5
        public ActionResult Edit(short? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            schueler schueler = db.schuelers.Find(id);
            if (schueler == null)
            {
                return HttpNotFound();
            }
            ViewBag.S_K_Klasse = new SelectList(db.klassens, "K_ID", "K_Bez", schueler.S_K_Klasse);
            Session["active_schueler_nr"] = schueler.S_SCHNR;
            return View(schueler);
          }

        // POST: Schuelers/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "S_Name,S_Vorname,S_Gebdat,S_Adresse,S_K_Klasse")] schueler schueler)
        {
            schueler.S_SCHNR = (short) Session["active_schueler_nr"];
            if (ModelState.IsValid)
            {
                db.Entry(schueler).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.S_K_Klasse = new SelectList(db.klassens, "K_ID", "K_Bez", schueler.S_K_Klasse);
            Session["active_schueler_nr"] = null;
            return View(schueler);
        }

        // GET: Schuelers/Delete/5
        public ActionResult Delete(short? id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            schueler schueler = db.schuelers.Find(id);
            if (schueler == null)
            {
                return HttpNotFound();
            }
            return View(schueler);
        }

        // POST: Schuelers/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(short id)
        {
            schueler schueler = db.schuelers.Find(id);
            db.schuelers.Remove(schueler);
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
