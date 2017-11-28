using System;
using System.Collections.Generic;
using Spengergasse.Weather;
using Spengergasse.Weather.Data;
using Spengergasse.CliUtil;


namespace Spengergasse.ConsoleWeatherApp {
  internal class PlacesView : IView {
    private State state;

    public PlacesView(State state) {
      this.state = state;
    }

    private void printPlace(Place place) {
      System.Console.WriteLine(
        string.Format("{0}. {1}", state.Places.IndexOf(place) + 1, place)
      );
    }

    private void printPlaces() {
      if (state.Places.Count > 0) {
        state.Places.ForEach(printPlace);
      } else {
        System.Console.WriteLine("(no places to show)");
      }

      System.Console.WriteLine();
    }

    public void Show() {
      while (true) {
        ConsoleUtil.PrintHeading("Places");

        printPlaces();

        if (state.Places.Count < 1) {
          new AddView(state).Show();
          Console.Clear();
          continue;
        }

        var text = "";

        var option = new Options(
          new List<Option> {
            new Option(ConsoleKey.S, "Show", new ShowView(state).Show),
            new Option(ConsoleKey.A, "Add", new AddView(state).Show),
            new Option(ConsoleKey.D, "Delete", new DeleteView(state).Show),
            new Option(ConsoleKey.U, string.Format(
              "Switch temperature unit (°{0})",
              state.TemperatureUnit
            ), toggleTemperatureUnit),
            new Option(ConsoleKey.Q, "Quit"),
            new Option(
              ConsoleKey.Enter,
              hidden: true,
              action: () => new ShowView(state).Apply(text)
            )
          },
          key => {
            try {
              int.Parse(key.KeyChar.ToString());
              text += key.KeyChar;
              Console.Write(key.KeyChar);
            } catch (FormatException) { }
          }
        ).Show();



        if (option.Action == null) return;
      }
    }

    private void toggleTemperatureUnit() {
      state.ToggleTemperatureUnit();
      Console.Clear();
    }

    private void noMatch() {
      Console.Clear();
      ConsoleUtil.PrintWarning("Number entered didn't match any place");
    }

    private void invalid() {
      Console.Clear();
      ConsoleUtil.PrintWarning("Number entered was invalid");
    }

    public static int PickPlace() {
      var line = System.Console.ReadLine();
      return int.Parse(line);
    }

    public static bool CheckPlace(State state, int number) =>
      number > 0 && number <= state.Places.Count;
  }
}