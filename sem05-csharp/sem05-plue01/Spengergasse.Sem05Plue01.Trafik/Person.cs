using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Spengergasse.Sem05Plue01.Trafik {
  class Person {
    private String name;
    private String vname;
    private char geschl;
    private int alter;
    private int plz;
    private String ort;

    //Default Konstruktor
    public Person() {
      name = "Mustermann";
      vname = "Max";
      geschl = 'M';
      alter = 38;
      Adresse = "Mustergasse 1";
      plz = 1100;
      ort = "Wien";

    }
    public Person(String n, String v, char ges, int a, String ad, int p, String o) {
      name = n;
      vname = v;
      geschl = ges;
      alter = a;
      adresse = ad;
      plz = p;
      ort = o;
    }

    public String Name {
      get { return name; }
      set { name = value; }
    }


    public String Vname {
      get { return vname; }
      set { vname = value; }
    }

    public char Geschlecht {
      get { return geschl; }
      set { geschl = value; }
    }

    public int Alter {
      get { return alter; }

      set {
        if (value >= 120 || value <= 16)
          Console.WriteLine("Darf nicht arbeiten");
        else
          alter = value;
      }
    }
    private String adresse;

    public String Adresse {
      get { return adresse; }
      set { adresse = value; }
    }


    public int Plz {
      get { return plz; }
      set { plz = value; }
    }


    public String Ort {
      get { return ort; }
      set { ort = value; }
    }

    public override String ToString() {
      return " Name: " + name + " Vorname: " + vname + " Alter: " + alter + " Adresse: " + adresse + "\n" + " PLZ: " + plz + " Ort: " + ort;
    }

    public double Geld() {
      return 120.0;
    }
  }
}
