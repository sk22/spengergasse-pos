using Newtonsoft.Json;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Spengergasse.DbApp {
  class Program {
    static void Main(string[] args) {
      var db = new KaiserChinnokEntities();

      Heading("1.");

      Print(
        from e in db.Employees
        where e.HireDatek4 < new DateTime(2003, 10, 1)
          && e.Cityo1 == "Calgary"
        orderby
          e.PostalCodea3 descending,
          e.LastNameg9 descending,
          e.FirstNamey9 descending
        select new { e.LastNameg9, e.FirstNamey9, e.PostalCodea3, e.Cityo1, e.HireDatek4 }
      );

      Heading("2. ");

      Print(
        from g in db.Genres
        select new { g.Namek3, TrackCount = g.Tracks.Count() }
      );

      Heading("3. ");

      Print(
        from p in db.Playlists
        where p.Namey7 == "Grunge"
        let Artists = p.Tracks.Select(t => t.Album.Artist.Names4).Distinct()
        select new { p.PlaylistIdj3, p.Namey7, Artists }
      );

      Heading("4. ");

      var most = db.Employees.OrderBy(e => e.Employee1.Count()).First();

      Print(
        new { most.LastNameg9, most.FirstNamey9, most.EmployeeIdi8, most.Titlex6 }
      );

      Heading("5. ");

      Print(
        from g in db.Genres
        let quantitySum = g.Tracks.Count
        let unitPriceSum = g.Tracks.Sum(t => t.UnitPricew8)
        select new { g.Namek3, Sales = quantitySum * unitPriceSum }
      );

      Heading("6. ");

      Print(
        from c in db.Customers
        group c by c.Statem6 into s
        select new { s.Key, Count = s.Count() }
      );

      Heading("7. ");

      Print(
        from c in db.Customers
        let Other = c.Invoices.Where(b => b.BillingAddresst2 != c.Addressv5).Distinct()
         where Other.Count() > 0
        select new { c.LastNameq2, c.FirstNameo7, c.Addressv5, Other }
      );

      Heading("8. ");

      Print(
        from t in db.Tracks
        where t.Playlists.Any(p => p.Namey7.StartsWith("Classical"))
        select new {
          t.Namer5,
          Invoices = t.InvoiceLines.Count(),
          Count = t.InvoiceLines.Count() == 0
            ? 0 : t.InvoiceLines.Sum(i => i.Quantityw6)
        }
      );

      Console.ReadKey();
    }

    static void Heading(string heading) {
      Console.WriteLine($"\n\n{heading}\n===\n");
    }

    static void Print(object obj) {
      Console.WriteLine(
        JsonConvert.SerializeObject(obj, Formatting.Indented)
      );
    }
  }
}
