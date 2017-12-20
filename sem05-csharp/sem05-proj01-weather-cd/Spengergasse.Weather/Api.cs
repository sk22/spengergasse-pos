using System;
using System.Web;
using System.Xml.Linq;

namespace Spengergasse.Weather {
  public class Api {
    public static string BuildQueryUrl(string query) =>
      "https://query.yahooapis.com/v1/public/yql?q="
        + HttpUtility.UrlEncode(query)
        + "&format=xml&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys";

    public static XElement Query(string query) =>
      XElement.Load((BuildQueryUrl(query)));
  }
}
