using System;
using System.Linq;
using System.Xml.Linq;
using System.Xml.XPath;

namespace Spengergasse.XMLTraining {
  class Program {
    static void Main() {
      XElement moore = XElement.Load("Moore.xml");
      System.Console.WriteLine(moore);
      System.Console.WriteLine(
        moore
          .Element("Actor")
          .Element("Name")
          .Element("FirstName").Value
      );

      ObjectDumper.Write(
        from e in moore.XPathSelectElements("/Actor/Name/FirstName")
        select e.Value
      );

      ObjectDumper.Write(
        from e in moore
          .Elements("Actor")
          .Elements("Name")
          .Elements("FirstName")
        select e.Value
      );

      System.Console.WriteLine(
        new XElement(
          "Actors",
          moore.XPathSelectElements("/Actor/Name/FirstName")
        )
      );

      XElement year = (
        from m in moore
          .Elements("Actor")
          .Elements("Filmography")
          .Elements("Movie")
        where m.Element("Title").Value == "D.R.E.A.M. Team"
        select m.Element("Year")
      ).First();
      year.SetValue(2018);

      moore.XPathSelectElement(
        "Actor/Filmography/Movie[Title = 'D.R.E.A.M. Team']/Year"
      ).SetValue(2018);


      moore.Save("Moore-training.xml");

      System.Console.WriteLine(moore.XPathSelectElement(
        "Actor/Filmography/Movie[Title = 'D.R.E.A.M. Team']/Year"
      ).Value);

      System.Console.WriteLine(
        moore
          .Elements("Actor")
          .Elements("Filmography")
          .Elements("Movie")
          .Count()
      );
    }
  }
}
