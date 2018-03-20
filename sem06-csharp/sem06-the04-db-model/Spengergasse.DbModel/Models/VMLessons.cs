using System;
using System.Collections.Generic;
using System.Linq;

namespace Spengergasse.DbModel.Models {
  public class VMLessons {
    public stunden CurrentLesson { get; set; }

    public List<gegenstaende> AllSubjects {
      get {
        using (Schule2000Entities db = new Schule2000Entities()) {
          return db.gegenstaendes.OrderBy(g => g.G_ID).ToList();
        }
      }
    }

    public List<lehrer> AllTeachers {
      get {
        using (Schule2000Entities db = new Schule2000Entities()) {
          return db.lehrers.OrderBy(t => t.L_Name).ThenBy(t => t.L_Vorname).ToList();
        }
      }
    }

    public List<raeume> AllRooms {
      get {
        using (Schule2000Entities db = new Schule2000Entities()) {
          return db.raeumes.OrderBy(r => r.R_ID).ToList();
        }
      }
    }

  }
}
