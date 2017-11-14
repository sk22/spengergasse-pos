using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Spengergasse.Sem05Plue01.Trafik {
  class Chef : Angestellter {
    private Trafik trafik;

    public Chef(String n, String v, char ges, int a, String ad, int p, String o, double std, double stdl, int i, Trafik trafik)
        : base(n, v, ges, a, ad, p, o, std, stdl, i) {
      this.trafik = trafik;
    }

    public Chef()
        : base("Musterman", "Max", 'M', 20, "MusterArd 10", 1040, "Wien", 200.0, 44.0, 01) {
    }

    public int Untergebene {
      get => trafik.Angestellte.Count() - 1;
    }
    public override string ToString() {
      return base.ToString() + "Untergebene vom Chef: " + Untergebene + "\n";
    }
  }
}
