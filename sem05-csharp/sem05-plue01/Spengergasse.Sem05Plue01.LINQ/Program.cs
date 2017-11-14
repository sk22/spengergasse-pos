using System;
using System.Linq;
using System.Xml.Linq;
using oestdaten;

namespace Spengergasse.Sem05Plue01.LINQ {
  class Program {
    static void Heading(string text) {
      System.Console.WriteLine("\n{0}\n===\n", text);
    }
    static void Main(string[] args) {

      Heading("1.");

      ObjectDumper.Write(
        from b in Data.Bezirke
        where b.Bundesland == Data.Bundeslaender.Where(bl => bl.ID == 8).First()
        orderby b.Einwohner
        select new { b.Name, b.Einwohner }
      );
      
      Heading("2.");

      ObjectDumper.Write(
        from g in Data.Gemeinden
        where g.Bezirk == Data.Bezirke
          .Where(b => b.Name == "Eisenstadt-Umgebung").First()
        select new { g.Name }  
      );

      Heading("3.");

      ObjectDumper.Write(
        from bl in Data.Bundeslaender
        where bl.ID == 7
        let bezirke =
          from bz in Data.Bezirke
          where bz.Bundesland == bl
          select bz.Einwohner
        select new { bl.Name, bl.Einwohner, Summe = bezirke.Sum() }
      );

      Heading("4.");

      ObjectDumper.Write(
        from plz in Data.Gemeinden
          .Where(g => g.Name == "Hüttenberg").First().Postleitzahlen
        select new { plz.Ort, plz.Plz }
      );

      Heading("5.");

      ObjectDumper.Write(
        from g in Data.Gemeinden
        group g by g.Status into grp
        select new { Status = grp.Key, Count = grp.Count() }
      );

      Heading("6.");

      System.Console.WriteLine(new XElement(
        "Bezirke",
        from bzk in Data.Bezirke
        where bzk.ID < 104
        let gemeinden =
          from g in Data.Gemeinden
          where g.Bezirk == bzk
          select g
        select new XElement(
          "Bezirk",
          new XAttribute("Name", bzk.Name),
          new XAttribute("AnzahlGemeinden", gemeinden.Count()),
          gemeinden.Select(g => new XElement("Gemeinde", g.Name))
        )
      ));
    }
  }
}
