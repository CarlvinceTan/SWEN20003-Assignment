package Texts;

import bagel.Font;
import bagel.DrawOptions;
import bagel.util.Colour;

/**
 * Class that represents the title text in the game.
 */
public class Title extends Text {

    private final String TITLE; // The title text to display

    /**
     * Constructs a new Title object.
     *
     * @param title The title text to display
     * @param x The x-coordinate of the text
     * @param y The y-coordinate of the text
     * @param font The font of the text
     */
    public Title(String title, int x, int y, Font font) {
        super(font, x, y);
        this.TITLE = title;
    }

    /**
     * Renders the title text.
     */
    public void render() {
        this.getFONT().drawString(TITLE, this.getX(), this.getY(), new DrawOptions().setBlendColour(Colour.WHITE));
    }
}
