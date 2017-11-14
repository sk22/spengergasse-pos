using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Spengergasse.Sem05Plue01.Trafik {
  class Trafik {
    private String name;
    private String adresse;
    private List<Waren> waren;
    private List<Angestellter> angestellte;

    public List<Angestellter> Angestellte {
      get => angestellte;
    }

    public Angestellter Chef { get; set; }

    public Trafik() {
      angestellte = new List<Angestellter>();
      waren = new List<Waren>();
      name = "Neziri Trafik";
      this.adresse = "Mariahilferstraße 69/31";
    }

    public String Name {
      get { return name; }
      set { name = value; }
    }

    public String Adresse {
      get { return adresse; }
      set { adresse = value; }
    }

    public void PersonEinstellen(Angestellter p) {
      angestellte.Add(p);
    }

    public void WareEinfuegen(Waren w) {
      waren.Add(w);
    }

    public void PersonAbstellen(Angestellter p) {
      angestellte.Remove(p);

    }

    public void WareEntfernen(Waren w) {
      waren.Remove(w);
    }

    public void Print() {
      Console.WriteLine();
      foreach (Person p in angestellte) {
        Console.WriteLine(p.ToString());
      }
      foreach (Waren w in waren) {
        Console.WriteLine(w.ToString());
      }

    }
    public Double Monatslohn() {
      double sum = 0.0;
      foreach (Person a in angestellte) {
        //if(a == pList.GetType().IsInstanceOfType(Angestellter))
        //sum += a.StundenLohn * a.Stunden;
      }
      Console.WriteLine("Monatslohn für alle Arbeiter: " + sum);
      return sum;
    }

    public double Kosten() {
      double sum = 0.0;
      foreach (Person p in angestellte) {
        sum += p.Geld();
      }
      Console.WriteLine("Die Kostenbetragen: " + sum);
      return sum;
    }

    public double WarenGesamtwert() {
      double sum = 0.0;
      //foreach (Waren w in wList)
      //{
      //    sum += w.Preis;
      //}
      sum = (from a in angestellte
             select a.Geld()).Sum();
      return sum;
    }
  }
}
