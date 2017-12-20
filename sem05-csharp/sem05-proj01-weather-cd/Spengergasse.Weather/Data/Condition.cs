using System;
using System.Xml.Linq;

namespace Spengergasse.Weather.Data {
  public class Condition {
    public DateTime Date { get; set; }
    public int Temp { get; set; }
    public string Text { get; set; }

    public static Condition GetCondition(XElement condition) => new Condition {
      Date = DateTime.Parse(sanitizeDate(condition.Attribute("date").Value)),
      Temp = int.Parse(condition.Attribute("temp").Value),
      Text = condition.Attribute("text").Value
    };

    private static string sanitizeDate(string date) =>
      date.Substring(0, date.Length - 4);
  }
}
