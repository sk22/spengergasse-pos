using Spengergasse.WebApplication.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.Security;

namespace Spengergasse.WebApplication.Controllers {
  public class AccountController : Controller {
    // GET: Account
    public ActionResult Login() {
      return View();
    }

    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Logout() {
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

    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Login(LoginModel model, string returnUrl) {

      if (ModelState.IsValid) {
        schueler loggedIn;
        using (var db = new Schule2000Entities()) {
          loggedIn = db.schuelers
            .Where(s => s.S_Name == model.UserName && s.S_Vorname == model.Password)
            .FirstOrDefault();
        }

        if (loggedIn != null) {

          FormsAuthentication.SetAuthCookie(model.UserName, model.RememberMe);
          return RedirectToLocal(returnUrl ?? "~/Home/Index");
        } else {
          ModelState.AddModelError("", "Invalid username or password.");
        }
      }


      return View(model);
    }
  }
}