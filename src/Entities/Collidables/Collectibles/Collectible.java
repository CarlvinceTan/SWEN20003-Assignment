package Entities.Collidables.Collectibles;

import Entities.Collidables.Collidable;
import Utilities.Point;
import bagel.Image;

/**
 * Class for collectibles, entities that can be collected by Player and when collected, they float up
 */
public class Collectible extends Collidable {
    /**
     * Constructor
     * @param position The position of the collectible.
     * @param speed The speed of the collectible.
     * @param image The image of the collectible.
     * @param radius The radius of the collectible.
     */
    public Collectible(Point position, int speed, Image image, double radius) {
        super(position, speed, image, radius);
    }

    /**
     * Makes the collectible float upwards until it is out of the window screen.
     */
    public void floatUp() {
        if (this.getPositionY() + this.getRADIUS() >= 0) {
            this.setPositionY(this.getPositionY() - 10);
        }
    }
}
