package Entities;

import Entities.Actors.Player;
import Utilities.Point;
import bagel.Keys;

/**
 * Abstract class representing an entity in the game.
 */
public abstract class Entity {

    private Point position; // The position of the entity, setters made to set individual x and y components of Point
    private final int SPEED; // The speed of the entity

    /**
     * Constructs a new Entity object.
     *
     * @param position The initial position of the entity
     * @param speed The speed of the entity
     */
    public Entity(Point position, int speed) {
        this.position = position;
        this.SPEED = speed;
    }

    /**
     * Updates the position of the entity based on player movement.
     *
     * @param platform The platform in the game
     * @param key The key pressed indicating player movement
     * @param player The player entity
     */
    public void updatePosition(Platform platform, Keys key, Player player) {
        double platformWidth = platform.getIMAGE().getWidth();
        double playerWidth = player.getIMAGE_LEFT().getWidth();
        if (key == Keys.LEFT && platform.getPosition().x - platformWidth / 2 - SPEED <=
                player.getPosition().x - playerWidth / 2) {
            position.x += SPEED; // Player moves left
        } else if (key == Keys.RIGHT && platform.getPosition().x + platformWidth / 2 + SPEED >=
                player.getPosition().x + playerWidth / 2) {
            position.x -= SPEED; // Player moves right
        }
    }

    /**
     * Retrieves the position of the entity.
     *
     * @return The position of the entity
     */
    public Point getPosition() {
        return position;
    }

    /**
     * Retrieves the x-coordinate of the entity's position.
     *
     * @return The x-coordinate of the entity's position
     */
    public double getPositionX() {
        return position.x;
    }

    /**
     * Retrieves the y-coordinate of the entity's position.
     *
     * @return The y-coordinate of the entity's position
     */
    public double getPositionY() {
        return position.y;
    }

    /**
     * Sets the x-coordinate of the entity's position.
     *
     * @param x The new x-coordinate of the entity's position
     */
    public void setPositionX(double x) {
        this.position.x = x;
    }

    /**
     * Sets the y-coordinate of the entity's position.
     *
     * @param y The new y-coordinate of the entity's position
     */
    public void setPositionY(double y) {
        this.position.y = y;
    }

    /**
     * Retrieves the speed of the entity.
     *
     * @return The speed of the entity
     */
    public int getSPEED() {
        return SPEED;
    }
}
