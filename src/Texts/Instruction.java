package Texts;

import bagel.Font;
import bagel.DrawOptions;
import bagel.util.Colour;

/**
 * Class that represents an instruction text in the game.
 */
public class Instruction extends Text {

    private final String INSTRUCTION; // The instruction text to display
    private final int WINDOW_WIDTH; // The width of the window

    /**
     * Constructs a new Instruction object.
     *
     * @param instruction The instruction text to display
     * @param windowWidth The width of the window
     * @param y The y-coordinate of the text
     * @param font The font of the text
     */
    public Instruction(String instruction, int windowWidth, int y, Font font) {
        super(font, windowWidth, y);
        this.INSTRUCTION = instruction;
        this.WINDOW_WIDTH = windowWidth;
    }

    /**
     * Renders the instruction text centered on the x-axis.
     */
    public void render() {
        String[] lines = INSTRUCTION.split("\n");
        for (int i = 0; i < lines.length; i++) {
            this.getFONT().drawString(lines[i], WINDOW_WIDTH / 2.0 - this.getFONT().getWidth(lines[i]) / 2.0,
                    this.getY() + i * 40, new DrawOptions().setBlendColour(Colour.WHITE));
        }
    }
}
