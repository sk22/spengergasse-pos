using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Linq;
using System.Net;
using System.Web;
using System.Web.Mvc;
using Spengergasse.WebApplication.Models;

namespace Spengergasse.WebApplication.Controllers {
    [Authorize]
    public class LehrersController : Controller
    {
        private Schule2000Entities db = new Schule2000Entities();

        // GET: Lehrers
        public ActionResult Index()
        {
            var lehrers = db.lehrers.Include(l => l.lehrer2);
            return View(lehrers.ToList());
        }

        // GET: Lehrers/Details/5
        public ActionResult Details(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            lehrer lehrer = db.lehrers.Find(id);
            if (lehrer == null)
            {
                return HttpNotFound();
            }
            return View(lehrer);
        }

        // GET: Lehrers/Create
        public ActionResult Create()
        {
            ViewBag.L_L_Chef = new SelectList(db.lehrers, "L_ID", "L_Name");
            return View();
        }

        // POST: Lehrers/Create
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Create([Bind(Include = "L_ID,L_Name,L_Vorname,L_Gebdat,L_Gehalt,L_L_Chef")] lehrer lehrer)
        {
            if (ModelState.IsValid)
            {
                db.lehrers.Add(lehrer);
                db.SaveChanges();
                return RedirectToAction("Index");
            }

            ViewBag.L_L_Chef = new SelectList(db.lehrers, "L_ID", "L_Name", lehrer.L_L_Chef);
            return View(lehrer);
        }

        // GET: Lehrers/Edit/5
        public ActionResult Edit(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            lehrer lehrer = db.lehrers.Find(id);
            if (lehrer == null)
            {
                return HttpNotFound();
            }
            ViewBag.L_L_Chef = new SelectList(db.lehrers, "L_ID", "L_Name", lehrer.L_L_Chef);
            return View(lehrer);
        }

        // POST: Lehrers/Edit/5
        // To protect from overposting attacks, please enable the specific properties you want to bind to, for 
        // more details see https://go.microsoft.com/fwlink/?LinkId=317598.
        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Edit([Bind(Include = "L_ID,L_Name,L_Vorname,L_Gebdat,L_Gehalt,L_L_Chef")] lehrer lehrer)
        {
            if (ModelState.IsValid)
            {
                db.Entry(lehrer).State = EntityState.Modified;
                db.SaveChanges();
                return RedirectToAction("Index");
            }
            ViewBag.L_L_Chef = new SelectList(db.lehrers, "L_ID", "L_Name", lehrer.L_L_Chef);
            return View(lehrer);
        }

        // GET: Lehrers/Delete/5
        public ActionResult Delete(string id)
        {
            if (id == null)
            {
                return new HttpStatusCodeResult(HttpStatusCode.BadRequest);
            }
            lehrer lehrer = db.lehrers.Find(id);
            if (lehrer == null)
            {
                return HttpNotFound();
            }
            return View(lehrer);
        }

        // POST: Lehrers/Delete/5
        [HttpPost, ActionName("Delete")]
        [ValidateAntiForgeryToken]
        public ActionResult DeleteConfirmed(string id)
        {
            lehrer lehrer = db.lehrers.Find(id);
            db.lehrers.Remove(lehrer);
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
