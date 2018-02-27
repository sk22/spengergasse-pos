using System;
using UnitTestDemo.API;
using Xunit;

namespace UnitTestDemo.Test {
  public class BankAccountTest {
    [Fact]
    public void DebitValueCalculatesCorrectly() {
      var account = new BankAccount("Hans", 11.99m);
      decimal amount = 5.77m;
      decimal expected = 6.22m;

      account.Debit(amount);
      Assert.Equal<decimal>(expected, account.Balance);
    }

    [Fact]
    public void DebitThrowsExceptionWhenValueHigherThanBalance() {
      var account = new BankAccount("Hans", 11.99m);
      var amount = 12.99m;
      Assert.Throws<ArgumentOutOfRangeException>(() => account.Debit(amount));
    }

    [Fact]
    public void FrozenAccountsCannotDebit() {
      var account = new BankAccount("Hans", 11.99m);
      account.FreezeAccount();
      Assert.Throws<Exception>(() => account.Debit(5m));
    }

    [Fact]
    public void FrozenAccountsCannotCredit() {
      var account = new BankAccount("Hans", 11.99m);
      account.FreezeAccount();
      Assert.Throws<Exception>(() => account.Credit(5m));
    }
  }
}
