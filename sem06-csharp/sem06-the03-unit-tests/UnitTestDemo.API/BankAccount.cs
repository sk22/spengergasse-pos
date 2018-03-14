using System;

namespace UnitTestDemo.API {
  public class BankAccount {
    private string m_customerName;

    private decimal m_balance;

    private bool m_frozen = false;

    private BankAccount() {
    }

    public BankAccount(string customerName, decimal balance) {
      m_customerName = customerName;
      m_balance = balance;
    }

    public string CustomerName {
      get { return m_customerName; }
    }

    public decimal Balance {
      get { return m_balance; }
    }

    public void Debit(decimal amount) {
      if (m_frozen) {
        throw new Exception("Account frozen");
      }

      if (amount > m_balance) {
        throw new ArgumentOutOfRangeException("amount");
      }

      if (amount < 0) {
        throw new ArgumentOutOfRangeException("amount");
      }

      m_balance -= amount; // intentionally incorrect code (fixed)
    }

    public void Credit(decimal amount) {
      if (m_frozen) {
        throw new Exception("Account frozen");
      }

      if (amount < 0) {
        throw new ArgumentOutOfRangeException("amount");
      }

      m_balance += amount;
    }

    public void FreezeAccount() {
      m_frozen = true;
    }

    public void UnfreezeAccount() {
      m_frozen = false;
    }

  }

}
