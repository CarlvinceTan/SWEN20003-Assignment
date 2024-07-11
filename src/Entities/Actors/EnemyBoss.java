package Entities.Actors;

import Entities.Collidables.Collidable;
import Entities.RandomGenerator;
import Utilities.Point;
import bagel.Image;
import bagel.Input;
import bagel.Keys;

import java.util.Properties;

/**
 * Class for EnemyBoss, an actor, which has the ability to shoot fireballs when player is within activation radius
 */
public class EnemyBoss extends Actor implements RandomGenerator {
    /**
     * Attributes
     */
    private final Image IMAGE;
    private final double ACTIVATION_RADIUS;
    private static EnemyBoss ENEMY_BOSS = null;

    /**
     * Constructor
     * @param position The position of the enemy boss.
     * @param speed The speed of the enemy boss.
     * @param radius The radius of the enemy boss.
     * @param health The health of the enemy boss.
     * @param height The height of the enemy boss.
     * @param image The image of the enemy boss.
     * @param activationRadius The activation radius of the enemy boss.
     */
    private EnemyBoss(Point position, int speed, double radius, double health, int height, Image image,
                      double activationRadius) {
        super(position, speed, radius, health, height);
        this.IMAGE = image;
        this.ACTIVATION_RADIUS = activationRadius;
    }

    /**
     * Checks if the enemy boss has collided with a collidable entity.
     * @param entity The collidable entity.
     * @return True if the enemy boss has collided with the entity, false otherwise.
     */
    public boolean checkCollision(Collidable entity) {
        return Point.withinRadius(this.getPosition(), entity.getPosition(), this.getRADIUS() + entity.getRADIUS());
    }

    /**
     * Deducts damage from the enemy boss's health when within range.
     * @param damage The amount of damage to deduct.
     */
    public void loseHealth(int damage) {
        this.setHealth(this.getHealth() - damage);
    }

    /**
     * Renders the image of the enemy boss.
     */
    public void render() {
        IMAGE.draw(this.getPositionX(), this.getPositionY());
    }

    /**
     * Handles the battle mode where the player and the enemy boss can engage in firing fireballs.
     * @param gameProps The properties of the game.
     * @param input The input from the player.
     * @param frameCount The current frame count of the game.
     */
    public static void battle(Properties gameProps, Input input, int frameCount) {
        if (Point.withinRadius(Player.getPLAYER().getPosition(), ENEMY_BOSS.getPosition(), ENEMY_BOSS.getACTIVATION_RADIUS())) {
            if (frameCount % 100 == 0) {
                if (ENEMY_BOSS.randomBoolean()) {
                    ENEMY_BOSS.fire(gameProps, Keys.LEFT);
                }
            }
            if (input.wasPressed(Keys.S)) {
                Player.getPLAYER().fire(gameProps, Player.getPLAYER().getDirection());
            }
        }
    }

    /**
     * Gets the instance of the enemy boss.
     * @return The instance of the enemy boss.
     */
    public static EnemyBoss getENEMY_BOSS() {
        return ENEMY_BOSS;
    }

    /**
     * Sets the instance of the enemy boss.
     * @param position The position of the enemy boss.
     * @param speed The speed of the enemy boss.
     * @param radius The radius of the enemy boss.
     * @param health The health of the enemy boss.
     * @param height The height of the enemy boss.
     * @param image The image of the enemy boss.
     * @param activationRadius The activation radius of the enemy boss.
     */
    public static void setENEMY_BOSS(Point position, int speed, double radius, double health, int height, Image image,
                                     double activationRadius) {
        ENEMY_BOSS = new EnemyBoss(position, speed, radius, health, height, image, activationRadius);
    }

    /**
     * Resets the instance of the enemy boss.
     */
    public static void resetENEMY_BOSS() {
        ENEMY_BOSS = null;
    }

    /**
     * Gets the activation radius of the enemy boss.
     * @return The activation radius of the enemy boss.
     */
    public double getACTIVATION_RADIUS() {
        return ACTIVATION_RADIUS;
    }
}
