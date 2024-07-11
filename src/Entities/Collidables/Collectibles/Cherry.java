package Entities.Collidables.Collectibles;

import Entities.Actors.Player;
import Entities.Platform;
import Utilities.Point;
import bagel.Image;
import bagel.Keys;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Cherry Power-Ups that enable Player to gain double points for each coin collected for a limited time
 */
public class Cherry extends Collectible {
    /**
     * Attributes
     */
    private final static List<Cherry> list = new ArrayList<>();
    private static int timerDuration;
    private static int timer;
    private boolean timerStarted;

    /**
     * Constructor
     * @param position The position of the cherry.
     * @param speed The speed of the cherry.
     * @param image The image of the cherry.
     * @param radius The radius of the cherry.
     */
    public Cherry(Point position, int speed, Image image, double radius) {
        super(position, speed, image, radius);
        this.timerStarted = false;
        list.add(this);
    }

    /**
     * Updates the position of all cherries.
     * @param platform The platform on which the cherries are located.
     * @param key The key input.
     * @param player The player interacting with the cherries.
     */
    public static void updatePositions(Platform platform, Keys key, Player player) {
        for (Cherry cherry : list) {
            cherry.updatePosition(platform, key, player);
        }
    }

    /**
     * Renders the image of all cherries.
     */
    public static void renderAll() {
        for (Cherry cherry : list) {
            cherry.getIMAGE().draw(cherry.getPositionX(), cherry.getPositionY());
        }
    }

    /**
     * Clears the list of cherries.
     */
    public static void clearList() {
        list.clear();
    }

    /**
     * Resets the timer.
     */
    private static void resetTimer() {
        timer = timerDuration;
    }

    /**
     * Starts the timer and sets the score multiplier.
     */
    public static void startTimer() {
        resetTimer();
        Coin.setScoreMultiplier(2);
        timer--;
    }

    /**
     * Updates the timer and adjusts the score multiplier.
     */
    public static void updateTimer() {
        if (Coin.getScoreMultiplier() == 2) {
            if (timer > 0) {
                timer--;
            } else {
                Coin.setScoreMultiplier(1);
                resetTimer();
            }
        }
    }

    /**
     * Gets the list of cherries.
     * @return The list of cherries.
     */
    public static List<Cherry> getList() {
        return list;
    }

    /**
     * Sets the duration of the timer.
     * @param timerDuration The duration of the timer.
     */
    public static void setTimerDuration(int timerDuration) {
        Cherry.timerDuration = timerDuration;
    }

    /**
     * Checks if the timer has started.
     * @return True if the timer has started, false otherwise.
     */
    public boolean isTimerStarted() {
        return timerStarted;
    }

    /**
     * Sets the timer started status.
     * @param timerStarted The timer started status.
     */
    public void setTimerStarted(boolean timerStarted) {
        this.timerStarted = timerStarted;
    }
}
