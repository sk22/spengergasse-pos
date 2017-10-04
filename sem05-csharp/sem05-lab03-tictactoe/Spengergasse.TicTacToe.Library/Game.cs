using System.Linq;

namespace Spengergasse.TicTacToe.Library {
  public class Game {
    public int[] Cells { get; } = new int[9];

    public int CurrentPlayer { get; private set; } = 1;

    public void Set(int cell) {
      if (Cells[cell] == 0) {
        Cells[cell] = CurrentPlayer;
        IteratePlayer();
      }
    }

    private void IteratePlayer() =>
      CurrentPlayer = CurrentPlayer == 1 ? 2 : 1;

    public int? CheckWinner() {
      if (CheckWinner(1)) return 1;
      if (CheckWinner(2)) return 2;
      if (CheckFull()) return 0;
      return null;
    }

    public bool CheckFull() {
      foreach (int cell in Cells) {
        if (cell == 0) return false;
      }
      return true;
    }

    public bool CheckWinner(int player) {
      if (Cells[0] == player && Cells[4] == player && Cells[8] == player) {
        return true;
      }
      if (Cells[2] == player && Cells[4] == player && Cells[6] == player) {
        return true;
      }
      for (int offset = 0; offset < 3; offset++) {
        if (Cells[0 + 3 * offset] == player
          && Cells[1 + 3 * offset] == player
          && Cells[2 + 3 * offset] == player) {
          return true;
        }
        if (Cells[0 + offset] == player
          && Cells[3 + offset] == player
          && Cells[6 + offset] == player) {
          return true;
        }
      }
      return false;
    }

    public override string ToString() => string.Join("", Cells) + " Current Player: " + CurrentPlayer;
  }
}
