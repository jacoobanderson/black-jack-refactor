package model.rules;

import model.Card;
import model.Player;

/**
 * The Soft 17 strategy.
 */
public class Soft17Strategy implements HitStrategy {
  private static final int hitLimit = 17;
  
  /**
   * Determines if the player should hit or not.
   *
   * @param dealer The player.
   * @return If the player should hit.
   */
  public boolean doHit(Player dealer) {
    int score = dealer.calcScore();
    int aces = 0;

    for (Card c : dealer.getHand()) {
      if (c.getValue() == Card.Value.Ace) {
        aces += 1;
      }
      if (score == hitLimit && aces > 0) {
        return true;
      }
    }

    if (score < hitLimit) {
      return true;
    }
    return false;
  }
}
