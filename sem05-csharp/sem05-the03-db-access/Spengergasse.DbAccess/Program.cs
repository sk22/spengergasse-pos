using Newtonsoft.Json;
using System.Linq;
using System.Xml.Linq;

namespace Spengergasse.DbAccess {
  class Program {
    static void Main(string[] args) {
      Schule2000Entities db = new Schule2000Entities();

      Title("schuelerdb_uebungsfragen");

      Print(
        from klasse in db.klassens
        select new {
          klasse.K_Bez,
          K_Schueler =
            from schueler in db.schuelers
            where schueler.S_K_Klasse == klasse.K_ID
            select new { schueler.S_Name, schueler.S_Vorname }
        }
      );

      Heading("Task 8");

      Print(
        (from stunde in db.stundens
         where stunde.ST_R_Raum.StartsWith("LA")
         select stunde).Count()
      );

      Heading("Task 9");

      Print(
        from s in db.schuelers
        where s.klassen.stundens.Any(st => st.ST_R_Raum == "B1")
        select new { s.S_SCHNR, s.S_Name, s.S_Vorname, s.S_K_Klasse }
      );

      Heading("Task 10");

      Print(
        (from v in db.vorgesetztes
         where v.V_Art.StartsWith("A")
         let l = v.lehrer
         select new { l.L_ID, l.L_Name }).Distinct()
      );

      Heading("Task 11a");

      Print(
        from l in db.lehrers
        where l.lehrer2.L_Vorname == l.L_Vorname
        select new { l.L_ID, L_L_Chef = l.lehrer2.L_ID, l.L_Vorname }
      );

      Heading("Task 11b");

      Print(
        from v in db.vorgesetztes
        where v.lehrer.L_Vorname == v.lehrer1.L_Vorname
        select new { v.V_L_Unt, v.V_L_Vorg, v.lehrer.L_Vorname }
      );

      Heading("Task 14");

      Print(
        from g in db.gegenstaendes
        let avg = g.pruefungens.Average(p => p.P_Note)
        orderby avg
        select new { g.G_ID, g.G_Bez, avg }
      );

      Heading("Task 15");

      var chefs =
        (from l in db.lehrers
         select l.lehrer2).Distinct();

      Print(
        (from l in chefs
         from s in l.stundens
         let g = s.gegenstaende
         select new { g.G_ID, g.G_Bez }).Distinct()
      );

      Heading("Task 16");

      Print(
        (from v in db.vorgesetztes
         from s1 in v.lehrer.stundens
         from k1s in s1.klassen.stundens
         where k1s.lehrer == v.lehrer1
         orderby k1s.ST_K_Klasse
         select new {
           k1s.ST_K_Klasse,
           k1s.ST_L_Lehrer
         }).Distinct()
      );

      Heading("Task 17");

      Print(
        from k in db.klassens
        where k.stundens.Count == 0
        let s1 = k.schueler
        let s2 = k.schueler1
        select new { klaspr = s1.S_Name, klasprstv = s2.S_Name }
      );

      Title("angabe3 - nurdb");

      Heading("Task 7");

      Print(
        from s in db.schuelers
        orderby s.klassen.K_Bez
        select new { s.S_Name, s.S_K_Klasse, s.klassen.K_Bez }
      );

      Heading("Task 10");

      // get all data as anonymous classes

      var klassen =
        from k in db.klassens
        select new {
          k.K_ID,
          k.K_Bez,
          k.lehrer.L_Name,
          Schanzahl = k.schuelers.Count(),
          schueler =
            from s in k.schuelers
            select new { s.S_Name, s.S_Vorname }
        };

      // convert all data to list to avoid
      // XElement constructors being called parameterless

      System.Console.WriteLine(new XElement(
        "Klassenliste",
        klassen.ToList().Select(k => new XElement(
          "Klasse",
          new XAttribute("KID", k.K_ID),
          new XAttribute("KBez", k.K_Bez),
          new XAttribute("KVName", k.L_Name ?? ""),
          new XAttribute("Schanzahl", k.Schanzahl),
          k.schueler.Select(s => new XElement(
            "Schueler",
            new XAttribute("Nachname", s.S_Name),
            new XAttribute("Vorname", s.S_Vorname)
          ))
        ))
      ));

      System.Console.ReadKey();
    }

    static void Print(object obj) {
      System.Console.WriteLine(
        JsonConvert.SerializeObject(obj, Formatting.Indented)
      );
    }

    static void Heading(string heading) {
      System.Console.WriteLine($"\n{heading}\n---\n");
    }

    static void Title(string title) {
      System.Console.WriteLine($"\n{title}\n===\n");
      System.Console.ReadKey(true);
    }
  }
}
