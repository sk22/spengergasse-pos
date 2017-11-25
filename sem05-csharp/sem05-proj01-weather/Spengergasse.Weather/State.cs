using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Xml.Serialization;
using Spengergasse.Weather.Data;

namespace Spengergasse.Weather {
  public class State {
    private static XmlSerializer serializer = new XmlSerializer(typeof (State));

    public Place CurrentPlace { get; set; }

    public List<Place> Places = new List<Place>();

    public static State Load() {
      if (!File.Exists("config.xml")) return null;
      FileStream stream = new FileStream("config.xml", FileMode.Open);
      if (!stream.CanRead) return null; 
      return (State) serializer.Deserialize(stream);
    }

    public void Persist() {
      TextWriter writer = new StreamWriter("config.xml");
      State.serializer.Serialize(writer, this);
      writer.Close();
    }

    private string temperatureUnit = "c";

    public string TemperatureUnit {
      get => temperatureUnit.ToUpper();
      set {
        var unit = value.ToUpper();
        if ("CF".Contains(unit)) this.temperatureUnit = unit;
      }
    }

    public void ToggleTemperatureUnit() {
      this.TemperatureUnit = this.TemperatureUnit == "C" ? "F" : "C";
    }
  }
}
