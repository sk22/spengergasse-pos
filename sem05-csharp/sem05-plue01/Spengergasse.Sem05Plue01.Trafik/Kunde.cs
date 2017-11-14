using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Spengergasse.Sem05Plue01.Trafik {
  class Kunde : Person {
    private int kdid;
    private int alter;

    private List<Waren> kaeufe = new List<Waren>();

    public List<Waren> Kaeufe { get { return kaeufe; } }

    public Kunde(String n, String v, char ges, int a, String ad, int p, String o, int id, int alter)
        : base(n, v, ges, a, ad, p, o) {
      kdid = id;
      this.alter = alter;
    }


    public Kunde()
        : base("KundeN1", "KundeV1", 'W', 30, "MusterStrß 30", 3049, "OrtKnd") {
      kdid = 1;
    }

    public List<Waren> Kaufen(Waren waren, int anzahl = 1) {
      var kauf = Enumerable.Repeat(waren, anzahl).ToList();
      kaeufe.AddRange(kauf);
      return kauf;
    }


    public int Kundenid {
      get { return kdid; }
      set { kdid = value; }
    }

    public override String ToString() {
      return base.ToString() + " ID: " + kdid + "\n";
    }
  }
}
