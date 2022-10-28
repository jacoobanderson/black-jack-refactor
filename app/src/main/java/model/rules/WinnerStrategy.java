package model.rules;

/**
 * Handles who the winner is.
 */
public interface WinnerStrategy {
  /**
   * Checks if the dealer is the winner.
   *
   * @param dealerScore The score of the dealer.
   * @param playerScore The score of the player.
   * @param maxScore The max score.
   * @return If the dealer is the winner.
   */
  boolean isDealerTheWinner(int dealerScore, int playerScore, int maxScore);
}
