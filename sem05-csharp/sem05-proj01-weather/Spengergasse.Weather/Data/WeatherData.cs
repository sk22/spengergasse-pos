using System;
using System.Collections.Generic;
using System.Linq;
using System.Xml.Linq;
using System.Xml.XPath;

namespace Spengergasse.Weather.Data {
  public class WeatherData {
    public IEnumerable<Forecast> Forecasts { get; set; }
    public Condition Condition { get; set; }

    public static WeatherData GetWeatherData(State state, Place place) =>
      prepareWeather(fetch(state, place));

    private static XElement fetch(State state, Place place) =>
      Api.Query(string.Format(
        "select * from weather.forecast where woeid ='{0}' and u='{1}'",
        place.WoeId,
        state.TemperatureUnit
      ));


    private static WeatherData prepareWeather(XElement result) {
      try {
        XNamespace xmlns = "http://xml.weather.yahoo.com/ns/rss/1.0";
        var item = result.XPathSelectElement("results/channel/item");
        var condition = item.Element(xmlns + "condition");
        var forecasts = item.Elements(xmlns + "forecast");

        return new WeatherData {
          Condition = Condition.GetCondition(condition),
          Forecasts = forecasts.Select(Forecast.GetForecast)
        };
      } catch (NullReferenceException nre) {
        System.Console.WriteLine(result);
        throw new ArgumentException("Result was not formed as expected.", nre);
      }
    }
  }
}
