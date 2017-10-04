namespace Spengergasse.TicTacToe.Library {
  public interface IGame {
    /// <summary>
    /// Makes the current player set the defined sell.
    /// If not applicable, no change is made.
    /// </summary>
    /// <param name="cell">Index of the cell</param>
    void Set(int cell);

    /// <summary>
    /// Checks if there is a winner in the current game state.
    /// Returns 1 for player 1, 2 for player 2, 0 for tie
    /// or null if the game is not yet decided.
    /// </summary>
    /// <returns></returns>
    int? CheckWinner();

    /// <summary>
    /// Checks if a specific player has won the game.
    /// </summary>
    /// <param name="player">Player number (1 or 2)</param>
    /// <returns></returns>
    bool CheckWinner(int player);
  }
}