using Spengergasse.MusicMetaWebApp.Models;
using System.Linq;
using System.Web.Mvc;
using System.Web.Security;

namespace Spengergasse.MusicMetaWebApp.Controllers {
  public class AccountController : Controller {
    // GET: Account
    public ActionResult Login() {
        return View();
    }

    // Validates password based on form data
    private bool ValidatePassword(Account model)
      => model.Password == new string(model.Username.ToCharArray().Reverse().ToArray());

    [HttpPost]
    [ValidateAntiForgeryToken]
    public ActionResult Login(Account model, string returnUrl) {
      if (ModelState.IsValid) {
        if (ValidatePassword(model)) {
          FormsAuthentication.SetAuthCookie(model.Username, model.RememberMe);
          Session["username"] = model.Username;
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