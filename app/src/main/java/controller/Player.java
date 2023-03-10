package controller;

import model.CardDrawnObserver;
import model.Game;
import view.View;


/**
 * Scenario controller for playing the game.
 */
public class Player implements CardDrawnObserver {
  private Game game;
  private View view;

  /**
   * The constructor.
   *
   * @param game The game.
   * @param view The view.
   */
  Player(Game game, View view) {
    this.game = game;
    this.view = view;
    game.addSubscriber(this);
  }

  /**
   * Runs the play use case.

   * @return True as long as the game should continue.
   */
  public boolean play() {
    view.displayWelcomeMessage();

    view.displayDealerHand(game.getDealerHand(), game.getDealerScore());
    view.displayPlayerHand(game.getPlayerHand(), game.getPlayerScore());

    if (game.isGameOver()) {
      view.displayGameOver(game.isDealerWinner());
    }

    View.GameEvent input = view.getInput();

    if (input == View.GameEvent.NEW_GAME) {
      game.newGame();
    } else if (input == View.GameEvent.HIT) {
      game.hit();
    } else if (input == View.GameEvent.STAND) {
      game.stand();
    }

    return input != View.GameEvent.QUIT;
  }

  /**
   * Pauses the game and shows the view.
   *
   * @param player The kind of player.
   */
  public void cardDrawn(String player) {
    try {
      if (player.equals("Dealer")) {
        view.displayDealerDrawsCard();
      } else {
        view.displayPlayerDrawsCard(); 
      }
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      view.pauseError();
    }
  }
}