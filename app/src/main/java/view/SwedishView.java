package view;

/**
 * Implements a Swedish console view.
 */
public class SwedishView implements View {

  /**
   * Displays dealer gets a card.
   */
  public void displayDealerDrawsCard() {
    System.out.println("Dealern får ett nytt kort.");
  }

  /**
   * Displays player gets a card.
   */
  public void displayPlayerDrawsCard() {
    System.out.println("Spelaren får ett nytt kort.");
  }

  /**
   * Shows a welcome message.
   */
  public void displayWelcomeMessage() {
    for (int i = 0; i < 50; i++) {
      System.out.print("\n");
    }

    System.out.println("Hej Black Jack Världen");
    System.out.println("----------------------");
    System.out.println("Skriv 'p' för att Spela, 'h' för nytt kort, 's' för att stanna 'q' för att avsluta\n");
  }

  /**
   * Returns pressed characters from the keyboard.

   * @return the pressed character.
   */
  public GameEvent getInput() {
    try {
      int c = System.in.read();
      while (c == '\r' || c == '\n') {
        c = System.in.read();
      }
      return handleInput(c);
    } catch (java.io.IOException e) {
      System.out.println("" + e);
      return handleInput(0);
    }
  }

  private GameEvent handleInput(int input) {
    switch (input) {
      case 'p':
        return GameEvent.NEW_GAME;
      case 'h':
        return GameEvent.HIT;
      case 's':
        return GameEvent.STAND;
      default:
        return GameEvent.QUIT;
    }
  }

  /**
   * Displays a card.

   * @param card The card to display.
   */
  public void displayCard(model.Card card) {
    if (card.getColor() == model.Card.Color.Hidden) {
      System.out.println("Dolt Kort");
    } else {
      String[] colors = { "Hjärter", "Spader", "Ruter", "Klöver" };
      String[] values = { "två", "tre", "fyra", "fem", "sex", "sju", "åtta", "nio", "tio",
                          "knekt", "dam", "kung", "ess" };
      System.out.println("" + colors[card.getColor().ordinal()] + " " + values[card.getValue().ordinal()]);
    }
  }

  public void displayPlayerHand(Iterable<model.Card> hand, int score) {
    displayHand("Spelare", hand, score);
  }

  public void displayDealerHand(Iterable<model.Card> hand, int score) {
    displayHand("Croupier", hand, score);
  }

  /**
   * Displays the winner of the game.

   * @param dealerIsWinner True if the dealer is the winner.
   */
  public void displayGameOver(boolean dealerIsWinner) {
    System.out.println("Slut: ");
    if (dealerIsWinner) {
      System.out.println("Croupiern Vann!");
    } else {
      System.out.println("Du vann!");
    }
  }

  private void displayHand(String name, Iterable<model.Card> hand, int score) {
    System.out.println(name + " Har: " + score);
    for (model.Card c : hand) {
      displayCard(c);
    }
    System.out.println("Poäng: " + score);
    System.out.println("");
  }

  /**
   * Displays that something has gone wrong with the pause..
   */
  public void pauseError() {
    System.out.println("Something has gone wrong with the pause!");
  }
}
