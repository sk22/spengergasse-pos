using System;
using System.Linq;
using System.Xml.Linq;

namespace Spengergasse.Weather.Data {
  public class Place {
    public string Country { get; set; }
    public string Locality { get; set; }
    public string WoeId { get; set; }

    private static string query =
      "select country.content, locality1.content, woeid from "
        + "geo.places where text='{0}'";

    public static Place GetPlace(string input) => preparePlace(fetch(input));

    private static Place preparePlace(XElement result) {
      var placeXml = result.Element("results").Elements().First();
      XNamespace xmlns = "http://where.yahooapis.com/v1/schema.rng";

      return new Place {
        Country = placeXml.Element(xmlns + "country").Value,
        Locality = placeXml.Element(xmlns + "locality1").Value,
        WoeId = placeXml.Element(xmlns + "woeid").Value
      };
    }

    private static XElement fetch(string input) {
      Console.Write("Fetching...");
      return Api.Query(string.Format(query, input));
    }

    private bool noResults(XElement result, string input) =>
      result.Element("results").Elements().Count() == 0;

    public override string ToString() =>
      string.Format("{0}, {1}", Locality, Country);
  }
}
