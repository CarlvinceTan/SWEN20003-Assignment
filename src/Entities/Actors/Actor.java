package Entities.Actors;

import Entities.Collidables.Collidable;
import Entities.Collidables.Fireball;
import Entities.Entity;
import Utilities.Point;
import bagel.Image;
import bagel.Keys;

import java.util.Properties;

/**
 * Class for actors e.g. Player, which have health and can receive damage and die
 */
public class Actor extends Entity {
    /**
     * Attributes
     */
    private final double RADIUS;
    private int health;
    private final int HEIGHT;

    /**
     * Constructor
     * @param position The position of the actor.
     * @param speed The speed of the actor.
     * @param radius The radius of the actor.
     * @param health The health of the actor.
     * @param height The height of the actor.
     */
    public Actor(Point position, int speed, double radius, double health, int height) {
        super(position, speed);
        this.RADIUS = radius;
        this.health = (int) health * 100;
        this.HEIGHT = height;
    }

    /**
     * Handles the falling animation of the actor.
     * @param windowHeight The height of the window.
     */
    public void fall(int windowHeight) {
        if (this.getPositionY() - HEIGHT <= windowHeight) {
            this.setPositionY(this.getPositionY() + 2);
        }
    }

    /**
     * Checks if the actor is alive based on their health.
     * @return True if the actor's health is above 0, false otherwise.
     */
    public boolean checkAlive() {
        return health > 0;
    }

    /**
     * Checks if the actor has collided with a collidable entity.
     * @param entity The collidable entity.
     * @return True if the actor has collided with the entity, false otherwise.
     */
    public boolean checkCollision(Collidable entity) {
        return Point.withinRadius(this.getPosition(), entity.getPosition(), RADIUS + entity.getRADIUS());
    }

    /**
     * Creates a fireball.
     * @param game_props The properties of the game.
     * @param direction The direction in which the fireball is fired.
     */
    public void fire(Properties game_props, Keys direction) {
        new Fireball(
                new Point(this.getPosition().x, this.getPosition().y),
                Integer.parseInt(game_props.getProperty("gameObjects.fireball.speed")),
                new Image(game_props.getProperty("gameObjects.fireball.image")),
                Double.parseDouble(game_props.getProperty("gameObjects.fireball.radius")),
                Double.parseDouble(game_props.getProperty("gameObjects.fireball.damageSize")),
                this,
                direction
        );
    }

    /**
     * Gets the health of the actor.
     * @return The health of the actor.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Gets the radius of the actor.
     * @return The radius of the actor.
     */
    public double getRADIUS() {
        return RADIUS;
    }

    /**
     * Sets the health of the actor.
     * @param health The new health of the actor.
     */
    public void setHealth(int health) {
        this.health = health;
    }
}
