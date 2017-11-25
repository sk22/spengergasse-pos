using System;
using System.Collections.Generic;
using System.Linq;
using System.Xml.Linq;
using System.Xml.XPath;
using Spengergasse.Weather;
using Spengergasse.Weather.Data;

namespace Spengergasse.ConsoleWeatherApp {
  internal class WeatherView : IView {
    private State state;

    public WeatherView(State state) {
      this.state = state;
    }

    public void Show() {
      ConsoleUtil.PrintHeading(state.CurrentPlace.Locality);

      var place = state.CurrentPlace;

      Console.Write("Fetching...");
      var weather = WeatherData.GetWeatherData(state, place);
      ConsoleUtil.ClearLine();

      printCondition(weather.Condition);
      printForecasts(weather.Forecasts.ToList());

      var option = new Options(new List<Option> {
        new Option(ConsoleKey.Q, "Quit")
      }).Show();

      if (option.Action == null) {
        System.Console.Clear();
        return;
      }
    }

    private void printForecasts(List<Forecast> forecasts) {
      ConsoleUtil.PrintSubheading("Forecasts");
      forecasts.ForEach(printForecast(new int[] {
        forecasts.Select(f => f.Date.ToShortDateString().Length).Max(),
        forecasts.Select(f => f.Text.Length).Max(),
        forecasts.Select(f => f.Low.ToString().Length).Max(),
        forecasts.Select(f => f.High.ToString().Length).Max()
      }));
      Console.WriteLine();
    }

    private Action<Forecast> printForecast(int[] lengths) => forecast =>
      Console.WriteLine(
        "{1} {2,-" + lengths[0] + "}   {3,-" + lengths[1] + "}   Low: {4,"
          + lengths[2] + "} °{0}   High: {5," + lengths[3] + "} °{0}",
        state.TemperatureUnit.ToString(),
        forecast.Date.ToString("ddd"),
        forecast.Date.ToShortDateString(),
        forecast.Text,
        forecast.Low,
        forecast.High
      );

    private void printCondition(Condition condition) {
      ConsoleUtil.PrintSubheading("Current Conditions");
      Console.WriteLine(condition.Date.ToLongTimeString());
      Console.WriteLine(
        "{0}, {1} °{2}",
        condition.Text,
        condition.Temp,
        state.TemperatureUnit
      );
      Console.WriteLine();
    }
  }
}