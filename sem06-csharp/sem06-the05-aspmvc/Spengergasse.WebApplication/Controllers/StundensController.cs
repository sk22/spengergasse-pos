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
    public class StundensController : Controller
    {
        private Schule2000Entities db = new Schule2000Entities();

        // GET: Stundens
        public ActionResult Index(string lehrer, string klasse)
        {
            var stundens = db.stundens
              .Include(s => s.gegenstaende).Include(s => s.klassen).Include(s => s.lehrer).Include(s => s.raeume)
              .Where(s => string.IsNullOrEmpty(lehrer) ? true : s.ST_L_Lehrer == lehrer)
              .Where(s => string.IsNullOrEmpty(klasse) ? true : s.ST_K_Klasse == klasse);

            ViewBag.lehrer = new SelectList(db.lehrers, "L_ID", "L_Name");
            ViewBag.klassen = new SelectList(db.lehrers, "K_ID", "K_Bez");
            return View(stundens.ToList());
        }

        // GET: Stundens/Details/5
        public ActionResult Details(string klasse, string stunde)
        {
          if (klasse == null || stunde == null) {
            return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
          }
          stunden stunden = db.stundens.Find(klasse, stunde);
          if (stunden == null) {
            return HttpNotFound();
          }
          ViewBag.ST_G_Fach = new SelectList(db.gegenstaendes, "G_ID", "G_Bez", stunden.ST_G_Fach);
          ViewBag.ST_K_Klasse = new SelectList(db.klassens, "K_ID", "K_Bez", stunden.ST_K_Klasse);
          ViewBag.ST_L_Lehrer = new SelectList(db.lehrers, "L_ID", "L_Name", stunden.ST_L_Lehrer);
          ViewBag.ST_R_Raum = new SelectList(db.raeumes, "R_ID", "R_ID", stunden.ST_R_Raum);
          return View(stunden);
      }

        // GET: Stundens/Create
        public ActionResult Create()
        {
            ViewBag.ST_G_Fach = new SelectList(db.gegenstaendes, "G_ID", "G_Bez");
            ViewBag.ST_K_Klasse = new SelectList(db.klassens, "K_ID", "K_Bez");
            ViewBag.ST_L_Lehrer = new SelectList(db.lehrers, "L_ID", "L_Name");
            ViewBag.ST_R_Raum = new SelectList(db.raeumes, "R_ID", "R_ID");
            return View();
        }

        // POST: Stundens/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "ST_K_Klasse,ST_L_Lehrer,ST_G_Fach,ST_Stunde,ST_R_Raum")] stunden stunden)
        {
            if (ModelState.IsValid)
            {
                db.stundens.Add(stunden);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.ST_G_Fach = new SelectList(db.gegenstaendes, "G_ID", "G_Bez", stunden.ST_G_Fach);
            ViewBag.ST_K_Klasse = new SelectList(db.klassens, "K_ID", "K_Bez", stunden.ST_K_Klasse);
            ViewBag.ST_L_Lehrer = new SelectList(db.lehrers, "L_ID", "L_Name", stunden.ST_L_Lehrer);
            ViewBag.ST_R_Raum = new SelectList(db.raeumes, "R_ID", "R_ID", stunden.ST_R_Raum);
            return View(stunden);
        }

        public ActionResult Edit(string klasse, string stunde) {
          if (klasse == null || stunde == null) {
            return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
          }
          stunden stunden = db.stundens.Find(klasse, stunde);
          if (stunden == null) {
            return HttpNotFound();
          }
          ViewBag.ST_G_Fach = new SelectList(db.gegenstaendes, "G_ID", "G_Bez", stunden.ST_G_Fach);
          ViewBag.ST_K_Klasse = new SelectList(db.klassens, "K_ID", "K_Bez", stunden.ST_K_Klasse);
          ViewBag.ST_L_Lehrer = new SelectList(db.lehrers, "L_ID", "L_Name", stunden.ST_L_Lehrer);
          ViewBag.ST_R_Raum = new SelectList(db.raeumes, "R_ID", "R_ID", stunden.ST_R_Raum);
          return View(stunden);
        }
  

        // POST: Stundens/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "ST_K_Klasse,ST_L_Lehrer,ST_G_Fach,ST_Stunde,ST_R_Raum")] stunden stunden)
        {
            if (ModelState.IsValid)
            {
                db.Entry(stunden).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.ST_G_Fach = new SelectList(db.gegenstaendes, "G_ID", "G_Bez", stunden.ST_G_Fach);
            ViewBag.ST_K_Klasse = new SelectList(db.klassens, "K_ID", "K_Bez", stunden.ST_K_Klasse);
            ViewBag.ST_L_Lehrer = new SelectList(db.lehrers, "L_ID", "L_Name", stunden.ST_L_Lehrer);
            ViewBag.ST_R_Raum = new SelectList(db.raeumes, "R_ID", "R_ID", stunden.ST_R_Raum);
            return View(stunden);
        }

        // GET: Stundens/Delete/5
        public ActionResult Delete(string klasse, string stunde) {
          if (klasse == null || stunde == null) {
            return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
          }
          stunden stunden = db.stundens.Find(klasse, stunde);
          if (stunden == null) {
            return HttpNotFound();
          }
          ViewBag.ST_G_Fach = new SelectList(db.gegenstaendes, "G_ID", "G_Bez", stunden.ST_G_Fach);
          ViewBag.ST_K_Klasse = new SelectList(db.klassens, "K_ID", "K_Bez", stunden.ST_K_Klasse);
          ViewBag.ST_L_Lehrer = new SelectList(db.lehrers, "L_ID", "L_Name", stunden.ST_L_Lehrer);
          ViewBag.ST_R_Raum = new SelectList(db.raeumes, "R_ID", "R_ID", stunden.ST_R_Raum);
          return View(stunden);
        }

        // POST: Stundens/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(string klasse, string stunde)
        {
            if (klasse == null || stunde == null) {
              return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            stunden stunden = db.stundens.Find(klasse, stunde);
            if (stunden == null) {
              return HttpNotFound();
            }
            db.stundens.Remove(stunden);
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
