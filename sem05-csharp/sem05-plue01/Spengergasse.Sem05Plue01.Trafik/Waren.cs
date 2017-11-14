using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Spengergasse.Sem05Plue01.Trafik {
  class Waren {
    private int id;
    private int preis;
    private String name;
    private Boolean einschraenkung;

    public Waren(int id, int p, String n, Boolean e) {
      this.id = id;
      preis = p;
      name = n;
      einschraenkung = e;
    }

    public Waren() {
      id = 1;
      preis = 2;
      name = "Zeitung";
      einschraenkung = false;
    }

    public int Id {
      get { return id; }
      set { id = value; }
    }

    public int Preis {
      get { return preis; }
      set { preis = value; }
    }

    public String Name {
      get { return name; }
      set { name = value; }
    }

    public Boolean Einschränkung {
      get { return einschraenkung; }
      set {
        if (einschraenkung == true) {
          //Kunde muss über 16 oder 18 sein
        } else {
          einschraenkung = value;
        }
      }
    }

    public virtual String ToString() {
      return " ID: " + id + " Preis: " + preis + " Name: " + name + " Einschränkung: " + einschraenkung;
    }
  }
}
