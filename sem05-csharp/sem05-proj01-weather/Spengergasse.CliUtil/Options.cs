using System;
using System.Collections.Generic;
using System.Linq;

namespace Spengergasse.CliUtil {
  public class Options {
    private List<Option> options;
    private Action<ConsoleKeyInfo> action;

    public Options(List<Option> options, Action<ConsoleKeyInfo> action = null) {
      this.options = options;
      this.action = action;
    }
    public static string GetOptionsString(List<Option> options) => options
      .Where(option => !option.Hidden)
      .Select(option =>
        string.Format("[{0}] {1}", option.Key, option.Desc))
      .Aggregate((s1, s2) => s1 + "    " + s2);

    public void PrintOptions() =>
      System.Console.Write(GetOptionsString(options) + ' ');

    public Option Show() {
      PrintOptions();
      return RunOptions();
    }

    public Option RunOptions() {
      while (true) {
        var key = Console.ReadKey(true);
        if (this.action != null) this.action(key);
        Predicate<Option> predicate = o => o.Key == key.Key;
        if (!options.Exists(predicate)) continue;
        System.Console.WriteLine();
        Option option = options.Find(predicate);
        if (option.Action != null) option.Action();
        return option;
      }
    }
  }

  public struct Option {
    public readonly ConsoleKey Key;
    public readonly string Desc;
    public readonly Action Action;
    public readonly bool Hidden;

    public Option(
      ConsoleKey key,
      string desc = null,
      Action action = null,
      bool hidden = false
    ) {
      Key = key;
      Desc = desc;
      Hidden = hidden;
      Action = action;
    }
  }
}