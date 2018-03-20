using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Input;

// UNFINISHED
namespace Spengergasse.DbModel.Models {
  public class VMKlassenStunden : INotifyPropertyChanged {
    private string selectedClass;
    private stunden selectedLesson;
    private DelegateCommand saveInsertLessonCommand;

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

    public ICommand SaveInsertLessonCommand {
      get {
        if (saveInsertLessonCommand == null) {
          saveInsertLessonCommand = new DelegateCommand(
            SaveExecuted,
            SaveCanExecute
          );
        }
        return saveInsertLessonCommand;
      }
    }

    public bool SaveCanExecute(object param) => param != null;

    public void SaveExecuted(object param) {
      if (param == null) return;
      if (param is stunden) {
        var vm = new VMLessons { CurrentLesson = param as stunden };
        var view = new Lessons {
          // View -> ViewModel
          DataContext = vm
        };
        view.ShowDialog();
        if (view.DialogResult.HasValue && view.DialogResult.Value) {
          using (Schule2000Entities db = new Schule2000Entities()) {
            db.Entry(vm.CurrentLesson).State = System.Data.Entity.EntityState.Modified;
            db.SaveChanges();
          }
        }
      }

      if (param is klassen) {
        var vm = new VMLessons {
          CurrentLesson = new stunden { ST_K_Klasse = (param as klassen).K_ID }
        };
        var view = new Lessons {
          DataContext = vm
        };
        view.ShowDialog();
        if (view.DialogResult.HasValue && view.DialogResult.Value) {
          using (Schule2000Entities db = new Schule2000Entities()) {
            db.stundens.Add(vm.CurrentLesson);
            db.SaveChanges();
            PropertyChanged(this, new PropertyChangedEventArgs("LessonsOfClass"));
          }
        }
      }
    }
  }
}
