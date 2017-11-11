using System;
using System.Linq;
using System.Xml.Linq;
using System.Xml.XPath;

namespace Spengergasse.XMLIntroduction {
  class Program {
    static void Main(string[] args) {
      PrintTitle("Load XML file");

      var xmlFromFile = XElement.Load("Handykatalog.xml");
      System.Console.WriteLine(xmlFromFile);

      PrintHeading("Query prices using Descendants");

      Console.WriteLine(new XElement(
        "Preise",
        xmlFromFile.Descendants("Preis")
      ));

      PrintHeading("Query prices using XPath");

      System.Console.WriteLine(new XElement(
        "Preise",
        xmlFromFile.XPathSelectElements("//Preis")
      ));

      PrintHeading("After 1995");

      var moore = XElement.Load("Moore.xml");

      System.Console.WriteLine(new XElement(
        "Task1",
        moore.XPathSelectElements("//Movie[Year > 1995]/Title")
      ));

      // ObjectDumper.Write(
      //   from title in moore.XPathSelectElements("//Movie[Year > 1995]/Title")
      //   select title.Value
      // );

      PrintHeading("Counts of movies by actor");

      ObjectDumper.Write(
        from actor in moore.Elements("Actor")
        select new {
          Actor = actor.Element("Name").Value,
          Count = actor.Element("Filmography").Elements().Count()
        }
      );

      PrintTitle("Kundenbestellungen");

      var kundenbestellungen = XElement.Load("kundenbestellungen.xml");

      PrintHeading("");

      ObjectDumper.Write(
        from firma in kundenbestellungen.Elements()
        orderby firma.Attribute("Land").Value
        select new {
          Firma = firma.Attribute("Firma").Value,
          Land = firma.Attribute("Land").Value
        }
      );

      System.Console.WriteLine(new XElement(
        "Firmas",
        from firma in kundenbestellungen.Elements()
        orderby firma.Attribute("Land").Value
        select new XElement(
          "Kunde",
          firma.Attribute("Firma"),
          firma.Attribute("Land")
        )
      ));

      PrintHeading("Frachtkosten pro Kunde");

      ObjectDumper.Write(
        from kunde in kundenbestellungen.Elements()
        select new {
          Firma = kunde.Attribute("Firma").Value,
          Kosten = kunde.Descendants("Frachtkosten")
            .Sum(element =>
              Decimal.Parse(element.Value.Replace(",", ".")))
          // Kosten =
          //   (from kosten in kunde.Descendants("Frachtkosten")
          //   select Decimal.Parse(kosten.Value.Replace(",", "."))
          //   ).Sum()
        }
      );

      PrintHeading("Bestellungen pro Mitarbeiter");

      // Collection: [9, 1, 4]
      var personalNummern =
        kundenbestellungen.XPathSelectElements("//Kunden/Personal_Nr")
          .Select(element => element.Value)
          .Distinct();

      ObjectDumper.Write(
        from nr in personalNummern
        select new {
          Personal_Nr = nr,
          Bestellungen = kundenbestellungen
            .XPathSelectElements(
              // Alle Bestellungen von Kunden mit der aktuellen Personalnummer
              string.Format("//Kunden[Personal_Nr = {0}]/Bestellungen", nr)
            ).Count()
        }
      );

      PrintHeading("Change year of Live and Let Die");
 
      moore
        .XPathSelectElement(@"./Actor[Name/FirstName = 'Roger' and
          Name/LastName = 'Moore']/Filmography/
          Movie[Title = 'Live and Let Die']/Year")
        .SetValue(1972);

      moore.Save("Moore-new.xml");

      Console.WriteLine();
    }

    static void PrintHeading(string heading) {
      Console.WriteLine("\n{0}\n---\n", heading);
    }
    
    static void PrintTitle(string title) {
      System.Console.WriteLine(
        "\n{0}\n{1}\n", title, new String('=', title.Length)
      );
    }
  }
}
