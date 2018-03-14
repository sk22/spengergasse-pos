using System;
using UnitTestDemo.API;

namespace UnitTestDemo {
  class Program {
    static void Main(string[] args) {
      BankAccount ba = new BankAccount("Mr. Bryan Walton", 11.99m);

      ba.Credit(5.77m);
      ba.Debit(11.22m);
      Console.WriteLine("Current balance is ${0}", ba.Balance);
      Console.ReadKey();
    }
  }
}
