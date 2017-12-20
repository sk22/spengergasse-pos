using System;
using System.Linq;
using System.Net;
using System.Xml.Linq;
using System.Xml.XPath;
using Spengergasse.Weather;
using Spengergasse.Weather.Data;
using Spengergasse.CliUtil;

namespace Spengergasse.ConsoleWeatherApp {
  internal class AddView : IView {
    private State state;

    public AddView(State state) {
      this.state = state;
    }


    public void Show() {
      Console.WriteLine();
      ConsoleUtil.PrintSubheading("Add Place");

      var startLine = Console.CursorTop;

      while (true) {
        ConsoleUtil.ClearLines(startLine, Console.CursorTop);

        Console.Write("Enter the place's name: ");
        var input = Console.ReadLine();
        try {
          if (input.Length == 0) {
            Console.Clear();
            return;
          }

          var place = Place.GetPlace(input);
          ConsoleUtil.ClearLine();

          if (!checkLocality(place)) {
            if (!tryAgain()) return;
            continue;
          }

          if (!resultApproved(place)) continue;

          state.Places.Add(place);
          new ShowView(state).Apply(state.Places.Count);
          return;
        } catch (WebException we) {
          Console.Clear();
          ConsoleUtil.PrintWarning(we.Message);
        } catch (InvalidOperationException) {
          ConsoleUtil.ClearLine();
          ConsoleUtil.PrintWarning(
            string.Format("No results for {0}", input)
          );
          if (!tryAgain()) {
            Console.Clear();
            return;
          }
          continue;
        }
      }
    }

    private bool tryAgain() {
      Console.Write("Try again? [y/N] ");
      var key = Console.ReadKey(true);
      return key.Key == ConsoleKey.Y;
    }

    private bool resultApproved(Place place) {
      Console.Write("{0}? [Y/n] ", place);
      var approved = Console.ReadKey(true).Key != ConsoleKey.N;
      return approved;
    }

    private bool checkLocality(Place place) {
      if (place.Locality != null && place.Locality.Length > 0) return true;
      ConsoleUtil.PrintWarning("The place found was invalid");
      return false;
    }
  }
}