using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace oestdaten {
  public class Bezirk {
    public int ID { get; set; }
    public string Name { get; set; }
    public int Einwohner { get; set; }
    public int hilfBdl { get; set; }               // nicht benutzen!!
    public Bundesland Bundesland { get; set; }
  }
}
