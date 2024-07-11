package Entities.Collidables;

import Entities.Entity;
import Utilities.Point;
import bagel.Image;

/**
 * Abstract class representing collidable entities in the game.
 */
public abstract class Collidable extends Entity {

    private final Image IMAGE; // The image representing the collidable entity
    private final double RADIUS; // The collision radius of the entity
    private boolean collided; // Flag indicating if the entity has collided with another entity

    /**
     * Constructs a new Collidable object.
     *
     * @param position The position of the collidable entity
     * @param speed The speed of the collidable entity
     * @param image The image representing the collidable entity
     * @param radius The collision radius of the entity
     */
    public Collidable(Point position, int speed, Image image, double radius) {
        super(position, speed);
        this.IMAGE = image;
        this.RADIUS = radius;
        this.collided = false;
    }

    /**
     * Retrieves the image representing the collidable entity.
     *
     * @return The image of the collidable entity
     */
    public Image getIMAGE() {
        return IMAGE;
    }

    /**
     * Retrieves the collision radius of the entity.
     *
     * @return The collision radius of the entity
     */
    public double getRADIUS() {
        return RADIUS;
    }

    /**
     * Checks if the entity has collided with another entity.
     *
     * @return True if the entity has collided, otherwise false
     */
    public boolean isCollided() {
        return collided;
    }

    /**
     * Sets the collision flag to indicate that the entity has collided.
     */
    public void setCollided() {
        this.collided = true;
    }
}
