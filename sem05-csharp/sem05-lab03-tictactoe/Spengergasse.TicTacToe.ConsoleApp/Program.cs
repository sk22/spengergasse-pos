using System;
using Spengergasse.TicTacToe.Library;

namespace Spengergasse.TicTacToe.ConsoleApp {
  public class Program {
    private static Game game;
    public static void Main(string[] args) {
      while (true) {
        game = new Game();
        Play();
        Console.Write("Play again? [y/N] ");
        if (Console.ReadKey(true).KeyChar.ToString().ToUpper() != "Y") {
          Environment.Exit(0);
        }
      }
    }

    static void Play() {
      int? winner = null;

      while (winner == null) {
        Console.Clear();
        Move();
        winner = game.CheckWinner();
      }

      Console.Clear();
      DrawGrid();

      Console.WriteLine();

      if (winner == 0) {
        Console.WriteLine("It's a tie!");
      } else {
        Console.WriteLine("{0} won!", GetPlayer(winner));
      }    
    }

    static string GetPlayer(int? player) {
      if (player == 1) return "X";
      if (player == 2) return "O";
      return null;
    }

    static void Move() {
      var cells = game.Cells;
      DrawGrid();

      Console.WriteLine();
      Console.WriteLine("It's {0}'s turn.", GetPlayer(game.CurrentPlayer));

      Console.Write("Please enter a valid number between 0 and 8: ");
      var input = ScanNumber();
      Console.WriteLine(input);
      game.Set(input);
    }

    static void DrawGrid() {
      Console.WriteLine();
      var i = 0;
      var cells = game.Cells;
      while (i < cells.Length) {
        var col = i % 3;
        if (col == 0) Console.Write("  ");
        Console.Write(cells[i] == 0 ? i.ToString() : GetPlayer(cells[i]));
        Console.Write(col == 2 ? '\n' : ' ');
        i++;
      }
    }

    static int ScanNumber() {
      int? number = null;
      while (number == null) {
        try {
          number = int.Parse(Console.ReadKey(true).KeyChar.ToString());
        } catch (FormatException) {
          continue;
        }
      }
      return (int) number;
    }
  }
}
