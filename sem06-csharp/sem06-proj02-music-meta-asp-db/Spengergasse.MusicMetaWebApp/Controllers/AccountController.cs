using Spengergasse.MusicMetaWebApp.Models;
using System;
using System.Linq;
using System.Web.Mvc;
using System.Web.Security;


namespace Spengergasse.MusicMetaWebApp.Controllers {
  public class AccountController : Controller {
    private HIF3bkaiserEntities db = new HIF3bkaiserEntities();

    // GET: Account
    public ActionResult Login() {
        return View();
    }

    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Login(Account model, string returnUrl) {
      if (ModelState.IsValid) {
        Artist artist = null;
        try {
          artist = db.Artists.Find(int.Parse(model.Password));
        } catch (FormatException) {}

        if ((model.Username == "admin" && model.Password == "-1")
          || (artist != null && model.Username == artist.Name)) {
          FormsAuthentication.SetAuthCookie(model.Username, model.RememberMe);
          Session["id"] = int.Parse(model.Password);
          return RedirectToLocal(returnUrl);
        } else {
          ModelState.AddModelError("", "Invalid username or password.");
        }
      }


      return View(model);
    }

    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Logout() {
      FormsAuthentication.SignOut();
      Session.Clear();
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