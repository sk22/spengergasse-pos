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
      if (state.Places.Count == 1) {
        Apply(1);
        return;
      }

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
        Console.Clear();
      }
    }

    public void Apply(int number) {
      if (!PlacesView.CheckPlace(state, number)) {
        Console.Clear();
        return;
      }
      SetPlace(number);
      Console.Clear();
      new WeatherView(state).Show();
    }

    public void Apply(string input) {
      try {
        Apply(int.Parse(input));
      } catch (FormatException) {
        Console.Clear();
      }
    }

    public void SetPlace(int number) =>
      state.CurrentPlace = state.Places[number - 1];
  }
}