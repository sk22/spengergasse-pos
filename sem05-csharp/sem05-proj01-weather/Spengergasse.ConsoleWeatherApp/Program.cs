using System;
using System.IO;
using Spengergasse.Weather;

namespace Spengergasse.ConsoleWeatherApp {
  class Program {
    static void Main(string[] args) {
      Console.OutputEncoding = System.Text.Encoding.UTF8;
      Console.Clear();
      var state = State.Load() ?? new State();
      new PlacesView(state).Show();
      try {
        state.Persist();
      } catch (IOException e) {
        Console.Clear();
        ConsoleUtil.PrintWarning("Could not persist the state");
        Console.WriteLine(e.Message);
      }
      Console.WriteLine();
    }
  }
}
