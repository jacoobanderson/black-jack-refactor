package model.rules;

public interface WinnerStrategy {
    boolean isDealerTheWinner(int dealerScore, int playerScore, int maxScore);
}
