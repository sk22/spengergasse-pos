using System;
using Spengergasse.CliUtil;
using Spengergasse.Weather;

namespace Spengergasse.ConsoleWeatherApp {
  internal class ShowView : IView {
    private State state;

    public ShowView(State state) {
      this.state = state;
    }

    public void Show() {
      Console.WriteLine();
      Console.Write("Enter a number to show the forecast for: ");

      try {
        var number = PlacesView.PickPlace();

        if (!PlacesView.CheckPlace(state, number)) {
          Console.Clear();
          ConsoleUtil.PrintWarning("Number entered didn't match any place");
          return;
        }

        SetPlace(number);

        Console.Clear();
        new WeatherView(state).Show();
      } catch (FormatException) {
        ConsoleUtil.PrintWarning("Number entered was invalid");
      }
    }

    public void Apply(string input) {
      try {
        var number = int.Parse(input);
        if (!PlacesView.CheckPlace(state, number)) {
          Console.Clear();
          return;
        }
        SetPlace(number);
        Console.Clear();
        new WeatherView(state).Show();
      } catch (FormatException) {
        Console.Clear();
      }
    }

    public void SetPlace(int number) =>
      state.CurrentPlace = state.Places[number - 1];
  }
}