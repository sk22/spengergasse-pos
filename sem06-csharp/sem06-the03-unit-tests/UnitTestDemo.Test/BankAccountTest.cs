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

    [Theory]
    [InlineData(10, true)]
    [InlineData(-10, false)]
    [InlineData(10000, true)]
    [InlineData(9999.99, true)]
    [InlineData(1234.5678, true)]
    [InlineData(-9999.99, false)]
    [InlineData(0, true)]
    public void CreditOnlyWorksWithValidValues(decimal value, bool expected) {
      var init = 11.99m;
      var account = new BankAccount("Hans", init);
      if (expected) {
        account.Credit(value);
        Assert.Equal(account.Balance, init + value);
      } else {
        Assert.Throws<ArgumentOutOfRangeException>(() => account.Credit(value));
      }
    }

    [Theory]
    [InlineData(100, 10, true)]
    [InlineData(0, 0, true)]
    [InlineData(10, 10, true)]
    [InlineData(10, 11, false)]
    [InlineData(1000, 2000, false)]
    [InlineData(1000000, 999999, true)]
    public void DebitThrowsWhenUnderZero(decimal init, decimal value, bool expected) {
      var account = new BankAccount("Hans", init);
      if (expected) {
        account.Debit(value);
        Assert.Equal(account.Balance, init - value);
      } else {
        Assert.Throws<ArgumentOutOfRangeException>(() => account.Debit(value));
      }
    }
  }
}
