using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace oestdaten {
  public class Gemeinde {
    public int ID { get; set; }
    public string Name { get; set; }
    public string Status { get; set; }
    public int hilfPlzGemeindeamt { get; set; } // die 3  hilf...   Felder nicht verwenden!!
    public string hilfPlzweitere { get; set; }
    public int hilfPb { get; set; }
    public Bezirk Bezirk { get; set; }
    public Postleitzahl PostleitzahlGemeindeamt { get; set; }
    public IEnumerable<Postleitzahl> Postleitzahlen { get; set; }
  }
}
