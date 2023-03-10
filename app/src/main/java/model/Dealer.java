package model;

import java.util.ArrayList;
import model.rules.HitStrategy;
import model.rules.NewGameStrategy;
import model.rules.RulesFactory;
import model.rules.WinnerStrategy;

/**
 * Represents a dealer player that handles the deck of cards and runs the game using rules.
 */
public class Dealer extends Player {

  private Deck deck;
  private NewGameStrategy newGameRule;
  private HitStrategy hitRule;
  private WinnerStrategy winnerRule;
  private ArrayList<CardDrawnObserver> subscribers;

  /**
   * Initializing constructor.

   * @param rulesFactory A factory that creates the rules to use.
   */
  public Dealer(RulesFactory rulesFactory) {
    winnerRule = rulesFactory.getWinningRule();
    newGameRule = rulesFactory.getNewGameRule();
    hitRule = rulesFactory.getHitRule();
    subscribers = new ArrayList<CardDrawnObserver>();
  }

  /**
   * Notifies the subscribers.
   *
   * @param player The kind of player.
   */
  public void notifySubscribers(String player) {
    for (CardDrawnObserver cardDrawnObserver : subscribers) {
      cardDrawnObserver.cardDrawn(player);
    }
  }

  /**
   * Adds a subscriber.
   *
   * @param subscriber The new subscriber.
   */
  public void addSubscriber(CardDrawnObserver subscriber) {
    subscribers.add(subscriber);
  }

  /**
   * Starts a new game if the game is not currently under way.

   * @param player The player to play agains.
   * @return True if the game could be started.
   */
  public boolean newGame(Player player) {
    if (deck == null || isGameOver()) {
      deck = new Deck();
      clearHand();
      player.clearHand();
      return newGameRule.newGame(deck, this, player);
    }
    return false;
  }

  /**
   * Gives the player one more card if possible. I.e. the player hits.

   * @param player The player to give a card to.
   * @return true if the player could get a new card, false otherwise.
   */
  public boolean hit(Player player) {
    if (deck != null && player.calcScore() < maxScore && !isGameOver()) {
      showAndDealCard(player, true);
      return true;
    }
    return false;
  }

  /**
   * Checks if the dealer is the winner compared to a player.

   * @param player The player to check agains.
   * @return True if the dealer is the winner, false if the player is the winner.
   */
  public boolean isDealerWinner(Player player) {
    return winnerRule.isDealerTheWinner(calcScore(), player.calcScore(), maxScore);
  }

  /**
   * Checks if the game is over, i.e. the dealer can take no more cards.

   * @return True if the game is over.
   */
  public boolean isGameOver() {
    if (deck != null && hitRule.doHit(this) != true) {
      return true;
    }
    return false;
  }

  /**
   * The player has choosen to take no more cards, it is the dealers turn.
   */
  public boolean stand() {
    showHand();
    if (deck != null) {
      while (hitRule.doHit(this) == true) {
        showAndDealCard(this, true);
      }
      return true;
    }
    return false;
  }

  /**
   * Deals a card and either shows it or not.
   *
   * @param player The player who gets the card.
   * @param visibility If it is to be shown or not.
   */
  public void showAndDealCard(Player player, Boolean visibility) {
    String kindOfPlayer = player.equals(this) ? "Dealer" : "Player";
    Card.Mutable c = deck.getCard();

    c.show(visibility);
    player.dealCard(c);
    notifySubscribers(kindOfPlayer);
  }
}