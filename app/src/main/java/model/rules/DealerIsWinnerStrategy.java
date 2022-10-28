package model.rules;

/**
 * Handles who the winner is.
 */
public class DealerIsWinnerStrategy implements WinnerStrategy {

  /**
   * Checks if the dealer is the winner or not.
   */
  public boolean isDealerTheWinner(int dealerScore, int playerScore, int maxScore) {
    if (playerScore > maxScore) {
      return true;
    } else if (dealerScore > maxScore) {
      return false;
    }
    return dealerScore >= playerScore;
  }
    
}
