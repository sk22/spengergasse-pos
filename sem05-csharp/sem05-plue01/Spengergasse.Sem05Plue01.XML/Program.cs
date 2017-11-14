using System;
using System.Linq;
using System.Xml.Linq;
using System.Xml.XPath;

namespace Spengergasse.Sem05Plue01.XML {
  class Program {
    static void Heading(string text) {
      System.Console.WriteLine("\n{0}\n===\n", text);
    }
    static void Main(string[] args) {
      var publishers = XElement.Load("Kaiser.xml");

      Heading("1.");

      System.Console.WriteLine(new XElement(
        "Employees",
        publishers.XPathSelectElements(
          "Publisherh3/Employeei0[@Level2 >= 120 and @Level2 <= 180]"
        )
      ));

      Heading("2.");

      ObjectDumper.Write(
        from p in publishers.Elements("Publisherh3")
        select new {
          p.Name.LocalName,
          Employees =
            from e in p.Elements("Employeei0")
            select new { Name = e.Attribute("Name3").Value }
        }, 2
      );

      Heading("3.");

      ObjectDumper.Write(
        from p in publishers.Elements("Publisherh3")
        let anzahl = p.Elements("Titlef2").Count()
        where anzahl < 5
        select new { Name = p.Attribute("Pname4").Value, Anzahl = anzahl }
      );
    }
  }
}
