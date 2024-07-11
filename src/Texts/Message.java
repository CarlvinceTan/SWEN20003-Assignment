package Texts;

import bagel.Font;
import bagel.DrawOptions;
import bagel.util.Colour;

/**
 * Class that represents a message text in the game.
 */
public class Message extends Text {

    private final String GAME_OVER; // The text to display for game over message
    private final String GAME_WON; // The text to display for game won message
    private final int WINDOW_WIDTH; // The width of the window

    /**
     * Constructs a new Message object.
     *
     * @param gameOver The text to display for game over message
     * @param gameWon The text to display for game won message
     * @param windowWidth The width of the window
     * @param y The y-coordinate of the text
     * @param font The font of the text
     */
    public Message(String gameOver, String gameWon, int windowWidth, int y, Font font) {
        super(font, windowWidth, y);
        this.GAME_OVER = gameOver;
        this.GAME_WON = gameWon;
        this.WINDOW_WIDTH = windowWidth;
    }

    /**
     * Renders the game over message centered on the x-axis.
     */
    public void renderGameOver() {
        String[] lines = GAME_OVER.split("\n");
        for (int i = 0; i < lines.length; i++) {
            this.getFONT().drawString(lines[i], WINDOW_WIDTH / 2.0 - this.getFONT().getWidth(lines[i]) / 2.0,
                    this.getY() + i * 40, new DrawOptions().setBlendColour(Colour.WHITE));
        }
    }

    /**
     * Renders the game won message centered on the x-axis.
     */
    public void renderGameWon() {
        String[] lines = GAME_WON.split("\n");
        for (int i = 0; i < lines.length; i++) {
            this.getFONT().drawString(lines[i], WINDOW_WIDTH / 2.0 - this.getFONT().getWidth(lines[i]) / 2.0,
                    this.getY() + i * 40, new DrawOptions().setBlendColour(Colour.WHITE));
        }
    }

}
