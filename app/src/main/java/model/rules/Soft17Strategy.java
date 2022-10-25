package model.rules;

import model.Card;
import model.Player;

public class Soft17Strategy implements HitStrategy {
    private static final int hitLimit = 17;
    
    public boolean doHit(Player dealer) {
      int score = dealer.calcScore();

      for (Card c : dealer.getHand()) {
        if (score == hitLimit && c.getValue() == Card.Value.Ace) {
          return true;
        }
      }

      if (score < hitLimit) {
        return true;
      }

      return false;
    }
}
