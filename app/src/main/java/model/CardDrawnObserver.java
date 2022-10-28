package model;

/**
 * The card observer interface.
 */
public interface CardDrawnObserver {
  /**
   * Handles the functionality of when a card is drawn.
   *
   * @param player The player that draws the card.
   */
  void cardDrawn(String player);
}
