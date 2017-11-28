using System;
using System.IO;
using Spengergasse.Weather;
using Spengergasse.CliUtil;

namespace Spengergasse.ConsoleWeatherApp {
  class Program {
    static void Main(string[] args) {
      Console.OutputEncoding = System.Text.Encoding.UTF8;
      Console.Clear();
      var state = State.Load() ?? new State();
      new PlacesView(state).Show();

      while (true) {
        try {
          state.Persist();
          Console.WriteLine();
          return;
        } catch (IOException e) {
          Console.Clear();
          ConsoleUtil.PrintWarning("Could not persist the state");
          Console.WriteLine(e.Message);
          Console.Write("Try again? [Y/n] ");
          if (Console.ReadKey(true).KeyChar == 'n') return;
          Console.WriteLine();
        }
      }
    }
  }
}
