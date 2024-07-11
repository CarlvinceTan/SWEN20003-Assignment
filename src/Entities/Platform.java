package Entities;

import Utilities.Point;
import bagel.Image;

/**
 * Class that represents the platform, the lowest possible position the player can reach in the game.
 */
public class Platform extends Entity {

    private final Image IMAGE; // The image representing the platform
    private static Platform PLATFORM = null; // The singleton instance of the platform

    /**
     * Constructs a new Platform object.
     *
     * @param image The image representing the platform
     * @param position The position of the platform
     * @param speed The speed of the platform
     */
    private Platform(Image image, Point position, int speed) {
        super(position, speed);
        this.IMAGE = image;
    }

    /**
     * Renders the image of the platform.
     */
    public void render() {
        IMAGE.draw(this.getPositionX(), this.getPositionY());
    }

    /**
     * Retrieves the image of the platform.
     *
     * @return The image of the platform
     */
    public Image getIMAGE() {
        return IMAGE;
    }

    /**
     * Retrieves the singleton instance of the platform.
     *
     * @return The singleton instance of the platform
     */
    public static Platform getPLATFORM() {
        return PLATFORM;
    }

    /**
     * Resets the singleton instance of the platform.
     */
    public static void resetPLATFORM() {
        PLATFORM = null;
    }

    /**
     * Sets the singleton instance of the platform.
     *
     * @param image The image representing the platform
     * @param position The position of the platform
     * @param speed The speed of the platform
     */
    public static void setPLATFORM(Image image, Point position, int speed) {
        if (PLATFORM == null) {
            PLATFORM = new Platform(image, position, speed);
        }
    }
}
