using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Spengergasse.DbModel.Models {
  public class VMKlassenStunden : INotifyPropertyChanged {
    private string selectedClass;
    private stunden selectedLesson;

    public string SelectedClass {
      get => selectedClass;
      set {
        selectedClass = value;
        PropertyChanged(this, new PropertyChangedEventArgs("LessonsOfClass"));
      }
    }

    public stunden SelectedLesson {
      get => selectedLesson;
      set {
        selectedLesson = value;
        PropertyChanged(this, new PropertyChangedEventArgs("SelectedLesson"));
      }
    }

    public IEnumerable<klassen> AllClasses {
      get {
        using (Schule2000Entities db = new Schule2000Entities()) {
          return db.klassens.OrderBy(k => k.K_Bez).ToList();
        }
      }
    }

    public IEnumerable<stunden> LessonsOfClass {
      get {
        using (Schule2000Entities db = new Schule2000Entities()) {
          return db.stundens
            .Where(s => s.ST_K_Klasse == SelectedClass)
            .OrderBy(s => s.ST_Stunde)
            .ToList();
        }
      }
    }

    public event PropertyChangedEventHandler PropertyChanged;
  }
}
