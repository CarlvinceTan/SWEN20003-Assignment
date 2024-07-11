package Entities.Collidables.Collectibles;

import Entities.Actors.Player;
import Entities.Platform;
import Utilities.Point;
import bagel.Image;
import bagel.Keys;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for Star Power-Ups that enable Player to gain invincibility for a limited time
 */
public class Star extends Collectible {
    private final static List<Star> list = new ArrayList<>();
    private static int timerDuration;
    private static int timer;
    private boolean timerStarted;

    /**
     * Constructor for Star.
     *
     * @param position the position of the star
     * @param speed the speed of the star
     * @param image the image of the star
     * @param radius the radius of the star
     */
    public Star(Point position, int speed, Image image, double radius) {
        super(position, speed, image, radius);
        timerStarted = false;
        list.add(this);
    }

    /**
     * Updates positions for all stars.
     *
     * @param platform the platform on which the star is
     * @param key the key pressed
     * @param player the player interacting with the star
     */
    public static void updatePositions(Platform platform, Keys key, Player player) {
        for (Star star : list) {
            star.updatePosition(platform, key, player);
        }
    }

    /**
     * Renders the image of all stars.
     */
    public static void renderAll() {
        for (Star star : list) {
            star.getIMAGE().draw(star.getPositionX(), star.getPositionY());
        }
    }

    /**
     * Clears the list of stars.
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
     * Starts the timer and makes the player invincible.
     *
     * @param player the player to make invincible
     */
    public static void startTimer(Player player) {
        resetTimer();
        player.setInvincible(true);
        timer--;
    }

    /**
     * Updates the timer and player's invincibility status.
     *
     * @param player the player whose invincibility status is updated
     */
    public static void updateTimer(Player player) {
        if (player.isInvincible()) {
            if (timer > 0) {
                timer--;
            } else {
                player.setInvincible(false);
                resetTimer();
            }
        }
    }

    /**
     * Gets the list of stars.
     *
     * @return the list of stars
     */
    public static List<Star> getList() {
        return list;
    }

    /**
     * Sets the duration of the timer.
     *
     * @param timerDuration the duration to set for the timer
     */
    public static void setTimerDuration(int timerDuration) {
        Star.timerDuration = timerDuration;
    }

    /**
     * Checks if the timer has started.
     *
     * @return true if the timer has started, false otherwise
     */
    public boolean isTimerStarted() {
        return timerStarted;
    }

    /**
     * Sets the timer started status.
     *
     * @param timerStarted the status to set for timerStarted
     */
    public void setTimerStarted(boolean timerStarted) {
        this.timerStarted = timerStarted;
    }
}
