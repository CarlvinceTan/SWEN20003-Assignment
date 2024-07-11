package Entities.Collidables;

import Entities.Actors.Actor;
import Entities.Actors.Player;
import Entities.Platform;
import Utilities.Point;
import bagel.Image;
import bagel.Keys;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents fireballs, which deal damage to actors.
 */
public class Fireball extends Collidable {

    private final int DAMAGE; // The damage inflicted by the fireball
    private final Keys DIRECTION; // The direction of the fireball
    private final Actor shooter; // The actor that fired the fireball
    private boolean hit; // Flag indicating if the fireball has hit a target
    private final static List<Fireball> list = new ArrayList<>(); // List of all fireball instances

    /**
     * Constructs a new Fireball object.
     *
     * @param position The position of the fireball
     * @param speed The speed of the fireball
     * @param image The image representing the fireball
     * @param radius The collision radius of the fireball
     * @param damage The damage inflicted by the fireball
     * @param actor The actor that fired the fireball
     * @param direction The direction of the fireball
     */
    public Fireball(Point position, int speed, Image image, double radius, double damage, Actor actor, Keys direction) {
        super(position, speed, image, radius);
        this.DAMAGE = (int) (damage * 100);
        this.DIRECTION = direction;
        this.shooter = actor;

        this.hit = false;
        list.add(this);
    }

    /**
     * Updates the position of the fireball based on the firing direction.
     *
     * @param platform The platform in the game
     * @param key The key pressed indicating the firing direction
     * @param player The player entity
     */
    @Override
    public void updatePosition(Platform platform, Keys key, Player player) {
        double platformWidth = platform.getIMAGE().getWidth();
        double playerWidth = player.getIMAGE_LEFT().getWidth();
        if (key == Keys.LEFT && platform.getPosition().x - platformWidth / 2 - this.getSPEED() <= player.getPosition().x - playerWidth / 2) {
            this.setPositionX(this.getPositionX() - this.getSPEED()); // Player moves left, so shoots in same direction
        } else if (key == Keys.RIGHT && platform.getPosition().x + platformWidth / 2 + this.getSPEED() >= player.getPosition().x + playerWidth / 2) {
            this.setPositionX(this.getPositionX() + this.getSPEED()); // Player moves right, so shoots in same direction
        }
    }

    /**
     * Updates the position of all fireballs.
     *
     * @param platform The platform in the game
     * @param player The player entity
     */
    public static void updatePositions(Platform platform, Player player) {
        for (Fireball fireball : list) {
            fireball.updatePosition(platform, fireball.DIRECTION, player);
        }
    }

    /**
     * Renders the image of all fireballs.
     */
    public static void renderAll() {
        for (Fireball fireball : list) {
            if (!fireball.isHit()) {
                fireball.getIMAGE().draw(fireball.getPositionX(), fireball.getPositionY());
            }
        }
    }

    /**
     * Clears the list of fireballs.
     */
    public static void clearList() {
        list.clear();
    }

    /**
     * Retrieves the list of all fireball instances.
     *
     * @return The list of fireball instances
     */
    public static List<Fireball> getList() {
        return list;
    }

    /**
     * Retrieves the actor that fired the fireball.
     *
     * @return The actor that fired the fireball
     */
    public Actor getShooter() {
        return shooter;
    }

    /**
     * Retrieves the damage inflicted by the fireball.
     *
     * @return The damage inflicted by the fireball
     */
    public int getDAMAGE() {
        return DAMAGE;
    }

    /**
     * Checks if the fireball has hit a target.
     *
     * @return True if the fireball has hit a target, otherwise false
     */
    public boolean isHit() {
        return hit;
    }

    /**
     * Sets the flag indicating if the fireball has hit a target.
     *
     * @param hit True if the fireball has hit a target, otherwise false
     */
    public void setHit(boolean hit) {
        this.hit = hit;
    }
}
