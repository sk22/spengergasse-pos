using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Spengergasse.Sem05Plue01.Trafik {
  class Program {
    static void Main(string[] args) {
      Chef chef1;
      Angestellter ang1, ang2;
      Waren waren1, waren2, waren3, waren4, waren5;

      Trafik t = new Trafik();

      // Angestellte
      chef1 = new Chef("Neziri", "Shukri", 'm', 17, "Musteradr", 1050, "Wien", 50, 100, 1, t);
      t.Chef = chef1;

      ang1 = new Angestellter("Mustermann", "Max", 'm', 20, "Adresse", 1050, "Wien", 40, 35, 2);

      ang2 = new Angestellter("Berger", "Möritz", 'm', 23, "Adresse2", 1090, "Wien", 40, 36, 3);

      //Waren ------------
      waren1 = new Waren(1, 4, "Zeitung", false);

      waren2 = new Waren(2, 3, "Fahrkarten", false);

      waren3 = new Waren(3, 5, "Zigaretten", true);

      waren4 = new Waren(4, 1, "Feuerzeug", false);

      waren5 = new Waren(5, 6, "Magazin", false);


      List<Waren> waren = new List<Waren> { waren1, waren2, waren3, waren4, waren5 };
      List<Person> person = new List<Person> { ang1, ang2, chef1 };
      List<Angestellter> angestellter = new List<Angestellter> { ang1, ang2 };

      t.PersonEinstellen(ang1);
      t.PersonEinstellen(ang2);
      t.PersonEinstellen(chef1);

      t.WareEinfuegen(waren1);
      t.WareEinfuegen(waren2);
      t.WareEinfuegen(waren3);
      t.WareEinfuegen(waren4);

      t.Print();

      var kunde = new Kunde();
      kunde.Kaufen(waren1, 2);

      System.Console.WriteLine(kunde);
      ObjectDumper.Write(kunde.Kaeufe);

      // Console.WriteLine("-- >>>> Linq-Abfragen NUR mit Klassen aus Ordner KlassenLinq <<<<--");

      //var erg = from b in Data.Bundeslaender
      //          where ..............
      //          select b;

      //ObjectDumper.Write(erg);



      Console.ReadKey();
    }
  }
}
