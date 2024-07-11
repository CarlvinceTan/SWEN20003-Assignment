package Entities.Collidables;

import Utilities.Point;
import bagel.Image;

/**
 * Class that represents the singular end flag entity in the game.
 */
public class EndFlag extends Collidable {

    private static EndFlag END_FLAG = null; // The singleton instance of the end flag entity

    /**
     * Constructs a new EndFlag object.
     *
     * @param position The position of the end flag
     * @param speed The speed of the end flag
     * @param image The image representing the end flag
     * @param radius The collision radius of the end flag
     */
    private EndFlag(Point position, int speed, Image image, double radius) {
        super(position, speed, image, radius);
    }

    /**
     * Renders the image of the end flag.
     */
    public void render() {
        this.getIMAGE().draw(this.getPositionX(), this.getPositionY());
    }

    /**
     * Retrieves the singleton instance of the end flag.
     *
     * @return The singleton instance of the end flag
     */
    public static EndFlag getEND_FLAG() {
        return END_FLAG;
    }

    /**
     * Sets the singleton instance of the end flag.
     *
     * @param position The position of the end flag
     * @param speed The speed of the end flag
     * @param image The image representing the end flag
     * @param radius The collision radius of the end flag
     */
    public static void setEND_FLAG(Point position, int speed, Image image, double radius) {
        if (END_FLAG == null) {
            END_FLAG = new EndFlag(position, speed, image, radius);
        }
    }

    /**
     * Resets the singleton instance of the end flag.
     */
    public static void resetEND_FLAG() {
        END_FLAG = null;
    }
}
