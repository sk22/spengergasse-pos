using System;
using System.Linq;

namespace Spengergasse.CliUtil {
  public static class ConsoleUtil {
    public static void PrintHeading(string heading) {
      System.Console.WriteLine(heading + "\n===\n");
    }

    public static void PrintSubheading(string heading) {
      System.Console.WriteLine(heading + "\n---\n");
    }

    public static void PrintWarning(string warning) {
      System.Console.WriteLine("⚠  " + warning + "\n");
    }

    public static void ClearLine(int lineDelta = 0) {
      int line = Console.CursorTop + lineDelta;
      Console.SetCursorPosition(0, line);
      Console.Write(new string(' ', Console.WindowWidth));
      Console.SetCursorPosition(0, line);
    }

    public static void ClearLineNumber(int line) {
      Console.SetCursorPosition(0, line);
      Console.Write(new string(' ', Console.WindowWidth));
      Console.SetCursorPosition(0, line);
    }

    public static void ClearLines(int startLine, int endLine) {
      var max = Enumerable.Max(new int[] { startLine, endLine });
      var min = Enumerable.Min(new int[] { startLine, endLine });

      foreach (int i in Enumerable.Range(min, max - min + 1)) {
        ConsoleUtil.ClearLineNumber(i);
      }
      Console.SetCursorPosition(0, min);
    }
  }
}
