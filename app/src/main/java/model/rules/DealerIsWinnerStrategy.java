package model.rules;

public class DealerIsWinnerStrategy implements WinnerStrategy {

  public boolean isDealerTheWinner(int dealerScore, int playerScore, int maxScore) {
    if (playerScore > maxScore) {
        return true;
      } else if (dealerScore > maxScore) {
        return false;
      }
      return dealerScore >= playerScore;
  }
    
}
