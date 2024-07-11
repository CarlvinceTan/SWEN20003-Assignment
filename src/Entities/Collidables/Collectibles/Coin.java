package Entities.Collidables.Collectibles;

import Entities.Actors.Player;
import Entities.Platform;
import Utilities.Point;
import bagel.Image;
import bagel.Keys;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Coins that are collectible by Player, when collected, the coin collected will float up and increase the
 * player's score
 */
public class Coin extends Collectible {
    /**
     * Attributes
     */
    private final int VALUE;
    private boolean collected;
    private static int scoreMultiplier;
    private final static List<Coin> list = new ArrayList<>();

    /**
     * Constructor
     * @param position The position of the coin.
     * @param speed The speed of the coin.
     * @param image The image of the coin.
     * @param radius The radius of the coin.
     * @param value The value of the coin.
     */
    public Coin(Point position, int speed, Image image, double radius, int value) {
        super(position, speed, image, radius);
        this.VALUE = value;

        collected = false;
        scoreMultiplier = 1;
        list.add(this);
    }

    /**
     * Updates the positions of all coins.
     * @param platform The platform on which the coins are located.
     * @param key The key pressed by the player.
     * @param player The player interacting with the coins.
     */
    public static void updatePositions(Platform platform, Keys key, Player player) {
        for (Coin coin : list) {
            coin.updatePosition(platform, key, player);
        }
    }

    /**
     * Renders the image of all coins.
     */
    public static void renderAll() {
        for (Coin coin : list) {
            coin.getIMAGE().draw(coin.getPositionX(), coin.getPositionY());
        }
    }

    /**
     * Clears the list of coins.
     */
    public static void clearList() {
        list.clear();
    }

    /**
     * Sets the score multiplier.
     * @param value The new score multiplier.
     */
    public static void setScoreMultiplier(int value) {
        scoreMultiplier = value;
    }

    /**
     * Gets the current score multiplier.
     * @return The current score multiplier.
     */
    public static int getScoreMultiplier() {
        return scoreMultiplier;
    }

    /**
     * Gets the list of all coins.
     * @return The list of all coins.
     */
    public static List<Coin> getList() {
        return list;
    }

    /**
     * Gets the value of the coin.
     * @return The value of the coin.
     */
    public int getVALUE() {
        return VALUE;
    }

    /**
     * Checks if the coin has been collected.
     * @return True if the coin has been collected, false otherwise.
     */
    public boolean isCollected() {
        return collected;
    }

    /**
     * Sets the collected status of the coin.
     * @param collected The new collected status.
     */
    public void setCollected(boolean collected) {
        this.collected = collected;
    }
}
