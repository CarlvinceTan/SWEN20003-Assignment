package Texts;

import bagel.Font;

/**
 * Class that represents a generic text object in the game.
 */
public abstract class Text {

    private final Font FONT; // The font of the text
    private final int X; // The x-coordinate of the text
    private final int Y; // The y-coordinate of the text

    /**
     * Constructs a new Text object.
     *
     * @param font The font of the text
     * @param x The x-coordinate of the text
     * @param y The y-coordinate of the text
     */
    public Text(Font font, int x, int y) {
        this.FONT = font;
        this.X = x;
        this.Y = y;
    }

    /**
     * Gets the font of the text.
     *
     * @return The font of the text
     */
    public Font getFONT() {
        return FONT;
    }

    /**
     * Gets the x-coordinate of the text.
     *
     * @return The x-coordinate of the text
     */
    public int getX() {
        return X;
    }

    /**
     * Gets the y-coordinate of the text.
     *
     * @return The y-coordinate of the text
     */
    public int getY() {
        return Y;
    }
}
