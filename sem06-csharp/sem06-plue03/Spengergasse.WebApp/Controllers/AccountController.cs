using Spengergasse.WebApp.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;

namespace Spengergasse.WebApp.Controllers {
  public class AccountController : Controller {
    // GET: Account
    public ActionResult Login() {
      return View();
    }

    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Login(LoginModel model, string returnUrl) {
      Personal personal;

      if (ModelState.IsValid) {
        using (var db = new KaiserNordwindEntities()) {
          personal = db.Personals
            .Where(p => p.Nachnamel1 == model.Username
              && p.Vornamea1 == model.Password).FirstOrDefault();
        }

        if (personal != null) {
          FormsAuthentication.SetAuthCookie(model.Username, model.RememberMe);
          return RedirectToLocal(returnUrl);  // Url  ?? "~/Home/Index"
        } else {
          ModelState.AddModelError("", "Invalid username or password.");
        }
      }

      return View(model);
    }

    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult LogOff() {
      FormsAuthentication.SignOut();
      return RedirectToAction("Index", "Home");
    }

    private ActionResult RedirectToLocal(string returnUrl) {
      if (Url.IsLocalUrl(returnUrl)) {
        return Redirect(returnUrl);
      } else {
        return RedirectToAction("Index", "Home");
      }
    }
  }
}
