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
    public class KlassensController : Controller
    {
        private Schule2000Entities db = new Schule2000Entities();

        // GET: Klassens
        public ActionResult Index()
        {
            var klassens = db.klassens.Include(k => k.lehrer).Include(k => k.schueler).Include(k => k.schueler1);
            return View(klassens.ToList());
        }

        // GET: Klassens/Details/5
        public ActionResult Details(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            klassen klassen = db.klassens.Find(id);
            if (klassen == null)
            {
                return HttpNotFound();
            }
            return View(klassen);
        }

        // GET: Klassens/Create
        public ActionResult Create()
        {
            ViewBag.K_L_Klavst = new SelectList(db.lehrers, "L_ID", "L_Name");
            ViewBag.K_S_Klaspr = new SelectList(db.schuelers, "S_SCHNR", "S_Name");
            ViewBag.K_S_Klasprstv = new SelectList(db.schuelers, "S_SCHNR", "S_Name");
            return View();
        }

        // POST: Klassens/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "K_ID,K_Bez,K_S_Klaspr,K_S_Klasprstv,K_L_Klavst")] klassen klassen)
        {
            if (ModelState.IsValid)
            {
                db.klassens.Add(klassen);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.K_L_Klavst = new SelectList(db.lehrers, "L_ID", "L_Name", klassen.K_L_Klavst);
            ViewBag.K_S_Klaspr = new SelectList(db.schuelers, "S_SCHNR", "S_Name", klassen.K_S_Klaspr);
            ViewBag.K_S_Klasprstv = new SelectList(db.schuelers, "S_SCHNR", "S_Name", klassen.K_S_Klasprstv);
            return View(klassen);
        }

        // GET: Klassens/Edit/5
        public ActionResult Edit(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            klassen klassen = db.klassens.Find(id);
            if (klassen == null)
            {
                return HttpNotFound();
            }
            ViewBag.K_L_Klavst = new SelectList(db.lehrers, "L_ID", "L_Name", klassen.K_L_Klavst);
            ViewBag.K_S_Klaspr = new SelectList(db.schuelers, "S_SCHNR", "S_Name", klassen.K_S_Klaspr);
            ViewBag.K_S_Klasprstv = new SelectList(db.schuelers, "S_SCHNR", "S_Name", klassen.K_S_Klasprstv);
            return View(klassen);
        }

        // POST: Klassens/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "K_ID,K_Bez,K_S_Klaspr,K_S_Klasprstv,K_L_Klavst")] klassen klassen)
        {
            if (ModelState.IsValid)
            {
                db.Entry(klassen).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.K_L_Klavst = new SelectList(db.lehrers, "L_ID", "L_Name", klassen.K_L_Klavst);
            ViewBag.K_S_Klaspr = new SelectList(db.schuelers, "S_SCHNR", "S_Name", klassen.K_S_Klaspr);
            ViewBag.K_S_Klasprstv = new SelectList(db.schuelers, "S_SCHNR", "S_Name", klassen.K_S_Klasprstv);
            return View(klassen);
        }

        // GET: Klassens/Delete/5
        public ActionResult Delete(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            klassen klassen = db.klassens.Find(id);
            if (klassen == null)
            {
                return HttpNotFound();
            }
            return View(klassen);
        }

        // POST: Klassens/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(string id)
        {
            klassen klassen = db.klassens.Find(id);
            db.klassens.Remove(klassen);
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
