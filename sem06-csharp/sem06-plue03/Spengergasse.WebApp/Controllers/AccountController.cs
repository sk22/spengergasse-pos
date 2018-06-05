using Spengergasse.WebApp.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;

namespace Spengergasse.WebApp.Controllers
{
    public class AccountController : Controller
    {
        // GET: Account
        public ActionResult Login()
        {
            return View();
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult Login(LoginModel model, string returnUrl)
        {
            // schueler slogin;

            if (ModelState.IsValid)
            {
                /* using (var db = new schule2000Entities())
                {
                    // model.Username ... eingegebener Username
                    // model.Password ... eingegebener Passwort

                    // prüfe, ob Username und Passwort richtig sind
                    // Beispiel für die Schul-DB mit Nach- und Vorname:

                    // var erg = from s in db.schuelers
                    //           where s.S_Name == model.Username && s.S_Vorname == model.Password
                    //           select s;
                    // slogin = erg.FirstOrDefault();
                } */

                /* if (slogin !== null)
                { */
                    FormsAuthentication.SetAuthCookie(model.Username, model.RememberMe);
                    return RedirectToLocal(returnUrl);  // Url  ?? "~/Home/Index"
                /* }
                else
                {
                    ModelState.AddModelError("", "Invalid username or password.");
                } */
            }

            return View(model);
        }

        [HttpPost]
        [ValidateAntiForgeryToken]
        public ActionResult LogOff()
        {
            FormsAuthentication.SignOut();
            return RedirectToAction("Index", "Home");
        }

        private ActionResult RedirectToLocal(string returnUrl)
        {
            if (Url.IsLocalUrl(returnUrl))
            {
                return Redirect(returnUrl);
            }
            else
            {
                return RedirectToAction("Index", "Home");
            }
        }
    }
}
