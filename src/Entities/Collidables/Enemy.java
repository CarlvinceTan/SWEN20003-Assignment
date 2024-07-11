package Entities.Collidables;

import Entities.Actors.Player;
import Entities.Platform;
import Entities.RandomGenerator;
import Utilities.Point;
import bagel.Image;
import bagel.Keys;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents enemies, which can deal damage to player once and move "randomly".
 */
public class Enemy extends Collidable implements RandomGenerator {

    private final int DAMAGE; // The damage inflicted by the enemy
    private final int MAX_RANDOM_DISPLACEMENT_X; // The maximum random displacement on the x-axis
    private final int RANDOM_SPEED; // The random speed of the enemy
    private int displacement; // The current displacement of the enemy
    private Keys direction; // The direction in which the enemy is moving
    private final static List<Enemy> list = new ArrayList<>(); // List of all enemy instances

    /**
     * Constructs a new Enemy object.
     *
     * @param position The position of the enemy
     * @param speed The speed of the enemy
     * @param image The image representing the enemy
     * @param radius The collision radius of the enemy
     * @param damage The damage inflicted by the enemy
     * @param maxRandomDisplacementX The maximum random displacement on the x-axis
     * @param randomSpeed The random speed of the enemy
     */
    public Enemy(Point position, int speed, Image image, double radius, double damage, int maxRandomDisplacementX,
                 int randomSpeed) {
        super(position, speed, image, radius);
        this.DAMAGE = (int) (damage * 100);
        this.MAX_RANDOM_DISPLACEMENT_X = maxRandomDisplacementX;
        this.RANDOM_SPEED = randomSpeed;

        this.displacement = 0;
        this.direction = randomDirection();
        list.add(this);
    }

    /**
     * Updates the displacement of all enemies.
     */
    public static void updateDisplacements() {
        for (Enemy enemy : list) {
            if (enemy.direction == Keys.LEFT) {
                enemy.setPositionX(enemy.getPositionX() - enemy.RANDOM_SPEED);
                enemy.displacement -= enemy.RANDOM_SPEED;
            } else {
                enemy.setPositionX(enemy.getPositionX() + enemy.RANDOM_SPEED);
                enemy.displacement += enemy.RANDOM_SPEED;
            }

            if (enemy.displacement == -enemy.MAX_RANDOM_DISPLACEMENT_X) {
                enemy.setPositionX(enemy.getPositionX() + enemy.RANDOM_SPEED);
                enemy.displacement += enemy.RANDOM_SPEED;
                enemy.direction = Keys.RIGHT;
            } else if (enemy.displacement == enemy.MAX_RANDOM_DISPLACEMENT_X) {
                enemy.setPositionX(enemy.getPositionX() - enemy.RANDOM_SPEED);
                enemy.displacement -= enemy.RANDOM_SPEED;
                enemy.direction = Keys.LEFT;
            }
        }
    }

    /**
     * Updates the position of all enemies.
     *
     * @param platform The platform in the game
     * @param key The key pressed by the player
     * @param player The player entity
     */
    public static void updatePositions(Platform platform, Keys key, Player player) {
        for (Enemy enemy : list) {
            enemy.updatePosition(platform, key, player);
        }
    }

    /**
     * Renders the image of all enemies.
     */
    public static void renderAll() {
        for (Enemy enemy : list) {
            enemy.getIMAGE().draw(enemy.getPositionX(), enemy.getPositionY());
        }
    }

    /**
     * Clears the list of enemies.
     */
    public static void clearList() {
        list.clear();
    }

    /**
     * Retrieves the list of all enemy instances.
     *
     * @return The list of enemy instances
     */
    public static List<Enemy> getList() {
        return list;
    }

    /**
     * Retrieves the damage inflicted by the enemy.
     *
     * @return The damage inflicted by the enemy
     */
    public int getDAMAGE() {
        return DAMAGE;
    }
}
