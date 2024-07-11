package Entities;

import Entities.Actors.Player;
import Utilities.Point;
import bagel.Image;
import bagel.Keys;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents flying platforms, that can move "randomly".
 */
public class FlyingPlatform extends Entity implements RandomGenerator {

    private final Image IMAGE; // The image representing the flying platform
    private final int HALF_LENGTH; // Half of the length of the flying platform
    private final int HALF_HEIGHT; // Half of the height of the flying platform
    private final int MAX_RANDOM_DISPLACEMENT_X; // The maximum random displacement on the x-axis
    private final int RANDOM_SPEED; // The random speed of the flying platform
    private int displacement; // The current displacement of the flying platform
    private Keys direction; // The direction in which the flying platform is moving
    private final static List<FlyingPlatform> list = new ArrayList<>(); // List of all flying platform instances

    /**
     * Constructs a new FlyingPlatform object.
     *
     * @param position The position of the flying platform
     * @param speed The speed of the flying platform
     * @param image The image representing the flying platform
     * @param halfLength Half of the length of the flying platform
     * @param halfHeight Half of the height of the flying platform
     * @param maxRandomDisplacementX The maximum random displacement on the x-axis
     * @param randomSpeed The random speed of the flying platform
     */
    public FlyingPlatform(Point position, int speed, Image image, int halfLength, int halfHeight,
                          int maxRandomDisplacementX, int randomSpeed) {
        super(position, speed);
        this.IMAGE = image;
        this.HALF_LENGTH = halfLength;
        this.HALF_HEIGHT = halfHeight;
        this.MAX_RANDOM_DISPLACEMENT_X = maxRandomDisplacementX;
        this.RANDOM_SPEED = randomSpeed;

        this.displacement = 0;
        this.direction = randomDirection();
        list.add(this);
    }

    /**
     * Updates the displacement of all flying platforms.
     */
    public static void updateDisplacements() {
        for (FlyingPlatform flyingPlatform : list) {
            if (flyingPlatform.direction == Keys.LEFT) {
                flyingPlatform.setPositionX(flyingPlatform.getPositionX() - flyingPlatform.RANDOM_SPEED);
                flyingPlatform.displacement -= flyingPlatform.RANDOM_SPEED;
            } else {
                flyingPlatform.setPositionX(flyingPlatform.getPositionX() + flyingPlatform.RANDOM_SPEED);
                flyingPlatform.displacement += flyingPlatform.RANDOM_SPEED;
            }

            if (flyingPlatform.displacement == -flyingPlatform.MAX_RANDOM_DISPLACEMENT_X) {
                flyingPlatform.setPositionX(flyingPlatform.getPositionX() + flyingPlatform.RANDOM_SPEED);
                flyingPlatform.displacement += flyingPlatform.RANDOM_SPEED;
                flyingPlatform.direction = Keys.RIGHT;
            } else if (flyingPlatform.displacement == flyingPlatform.MAX_RANDOM_DISPLACEMENT_X) {
                flyingPlatform.setPositionX(flyingPlatform.getPositionX() - flyingPlatform.RANDOM_SPEED);
                flyingPlatform.displacement -= flyingPlatform.RANDOM_SPEED;
                flyingPlatform.direction = Keys.LEFT;
            }
        }
    }

    /**
     * Updates the position of all flying platforms.
     *
     * @param platform The platform in the game
     * @param key The key pressed indicating player movement
     * @param player The player entity
     */
    public static void updatePositions(Platform platform, Keys key, Player player) {
        for (FlyingPlatform flyingPlatform : list) {
            flyingPlatform.updatePosition(platform, key, player);
        }
    }

    /**
     * Renders the image of all flying platforms.
     */
    public static void renderAll() {
        for (FlyingPlatform flyingPlatform : list) {
            flyingPlatform.IMAGE.draw(flyingPlatform.getPositionX(), flyingPlatform.getPositionY());
        }
    }

    /**
     * Clears the list of flying platforms.
     */
    public static void clearList() {
        list.clear();
    }

    /**
     * Retrieves the list of all flying platform instances.
     *
     * @return The list of flying platform instances
     */
    public static List<FlyingPlatform> getList() {
        return list;
    }

    /**
     * Retrieves half of the length of the flying platform.
     *
     * @return Half of the length of the flying platform
     */
    public int getHALF_LENGTH() {
        return HALF_LENGTH;
    }

    /**
     * Retrieves half of the height of the flying platform.
     *
     * @return Half of the height of the flying platform
     */
    public int getHALF_HEIGHT() {
        return HALF_HEIGHT;
    }
}
