using System;
using Spengergasse.CliUtil;
using Spengergasse.Weather;

namespace Spengergasse.ConsoleWeatherApp {
  internal class DeleteView : IView {
    private State state;

    public DeleteView(State state) {
      this.state = state;
    }

    public void Show() {
      Console.WriteLine();
      Console.Write(
        "Which place do you want to delete? Enter a number: "
      );

      try {
        var number = PlacesView.PickPlace();
        Console.Clear();

        if (PlacesView.CheckPlace(state, number)) {
          state.Places.RemoveAt(number - 1);
        } else  {
          ConsoleUtil.PrintWarning("Number entered didn't match any place");
        }
      } catch (FormatException) {
        Console.Clear();
      }
    }
  }
}