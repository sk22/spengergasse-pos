using Spengergasse.MusicMetaWebApp.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Spengergasse.MusicMetaWebApp.Controllers {
  public class HomeController : Controller {
    private HIF3bkaiserEntities db = new HIF3bkaiserEntities();

    public ActionResult Index() {
      return View(db);
    }
  }
}