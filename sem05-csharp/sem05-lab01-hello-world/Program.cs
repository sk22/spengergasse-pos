using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HelloWorld {
  public class Program {
    static void Main() {
      MyDate date = new MyDate { Year = 2017, Month = 9, Day = 13 };
      Console.WriteLine("Hi, C#!");
      Console.ReadKey();
    }
  }
}
