package model.rules;

import model.Card;
import model.Player;

public class Soft17Strategy implements HitStrategy {
    private static final int hitLimit = 17;
    
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
