package Texts;

import bagel.Font;
import bagel.DrawOptions;
import bagel.util.Colour;

/**
 * Class that represents the score text in the game.
 */
public class Score extends Text {

    private final String SCORE_TEXT; // The text to display before the score value

    /**
     * Constructs a new Score object.
     *
     * @param scoreText The text to display before the score value
     * @param x The x-coordinate of the text
     * @param y The y-coordinate of the text
     * @param font The font of the text
     */
    public Score(String scoreText, int x, int y, Font font) {
        super(font, x, y);
        this.SCORE_TEXT = scoreText;
    }

    /**
     * Renders the score text in the format "[SCORE_TEXT] [score]".
     *
     * @param score The current score value to display
     */
    public void render(int score) {
        this.getFONT().drawString(SCORE_TEXT + score, this.getX(), this.getY(),
                new DrawOptions().setBlendColour(Colour.WHITE));
    }
}
