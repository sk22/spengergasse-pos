using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace HelloWorld {
  public class MyDate {
    private int month;
    private int day;

    // Snippet prop
    public int Year { get; set; }


    // Snippet propfull
    public int Day {
      get { return day; }
      set { day = value; }
    }

    // Auto-generated for field
    public int Month {
      get => month;
      set => month = value;
    }
  }
}
