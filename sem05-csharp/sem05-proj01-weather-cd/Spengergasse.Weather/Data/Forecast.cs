using System;
using System.Xml.Linq;

namespace Spengergasse.Weather.Data {

  public class Forecast {
    public DateTime Date { get; set; }
    public int High { get; set; }
    public int Low { get; set; }
    public string Text { get; set; }


    public static Forecast GetForecast(XElement forecast) => new Forecast {
      Date = DateTime.Parse(forecast.Attribute("date").Value),
      High = int.Parse(forecast.Attribute("high").Value),
      Low = int.Parse(forecast.Attribute("low").Value),
      Text = forecast.Attribute("text").Value
    };
  }
}
