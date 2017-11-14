using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Spengergasse.Sem05Plue01.Trafik {
  class Angestellter : Person {
    private int id;
    private double stunden;
    private double stundenLohn;

    public Angestellter(String n, String v, char ges, int a, String ad, int p, String o, double std, double stdl, int i)
        : base(n, v, ges, a, ad, p, o) {
      id = i;
      stunden = std;
      stundenLohn = stdl;

    }

    public Angestellter()
        : base("Mustermann", "Max", 'M', 20, "Mustergasse 12/2", 1050, "Wien") {
      stunden = 40;
      stundenLohn = 25.5;
    }
    public double Stunden {
      get { return stunden; }
      set { stunden = value; }
    }

    public double StundenLohn {
      get { return stundenLohn; }
      set {
        if (value >= 100 || value <= 10)
          Console.WriteLine("StundenLohn überprüfen");
        else
          stundenLohn = value;
      }
    }

    public override String ToString() {
      return base.ToString() + " Stunden: " + stunden + " StundenLohn: " + stundenLohn + "\n";
    }

    public int CompareTo(Angestellter other) {
      return this.Stunden.CompareTo(other.Stunden);
    }
  }
}
