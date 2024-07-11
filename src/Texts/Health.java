package Texts;

import bagel.Font;
import bagel.DrawOptions;

/**
 * Class that represents the health text in the game.
 */
public class Health extends Text {

    private final String HEALTH_TEXT; // The text to display before the health value

    /**
     * Constructs a new Health object.
     *
     * @param healthText The text to display before the health value
     * @param x The x-coordinate of the text
     * @param y The y-coordinate of the text
     * @param font The font of the text
     */
    public Health(String healthText, int x, int y, Font font) {
        super(font, x, y);
        this.HEALTH_TEXT = healthText;
    }

    /**
     * Renders the health text in the format "HEALTH: [health]".
     *
     * @param health The current health value to display
     * @param colour The draw options for rendering the text
     */
    public void render(int health, DrawOptions colour) {
        this.getFONT().drawString(HEALTH_TEXT + health, this.getX(), this.getY(), colour);
    }
}
