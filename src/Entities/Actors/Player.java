package Entities.Actors;

import Entities.Collidables.Collectibles.Coin;
import Entities.FlyingPlatform;
import Entities.Platform;
import Utilities.Point;
import bagel.Image;
import bagel.Input;
import bagel.Keys;
import bagel.Window;

/**
 * Class for Player, an actor, which has the ability to shoot fireballs when within activation radius of EnemyBoss
 */
public class Player extends Actor {
    /**
     * Attributes
     */
    private final Image IMAGE_LEFT;
    private final Image IMAGE_RIGHT;
    private int speed;
    private int score;
    private boolean invincible;
    private Keys direction;
    private static Player PLAYER = null;

    /**
     * Constructor
     * @param position The position of the player.
     * @param speed The speed of the player.
     * @param radius The radius of the player.
     * @param health The health of the player.
     * @param height The height of the player.
     * @param imageLeft The image of the player facing left.
     * @param imageRight The image of the player facing right.
     */
    private Player(Point position, int speed, double radius, double health, int height, Image imageLeft,
                   Image imageRight) {
        super(position, speed, radius, health, height);
        this.IMAGE_LEFT = imageLeft;
        this.IMAGE_RIGHT = imageRight;

        this.score = 0;
        this.speed = -20;
        this.invincible = false;
        this.direction = Keys.RIGHT;
    }

    /**
     * Increments the speed value of the player.
     */
    public void increaseSpeed() {
        speed++;
    }

    /**
     * Deducts health from the player when not invincible.
     * @param damage The amount of damage to deduct.
     */
    public void loseHealth(int damage) {
        if (!invincible) {
            this.setHealth(this.getHealth() - damage);
        }
    }

    /**
     * Checks if the player is on a platform or flying platform.
     * @return True if the player is on a platform, false otherwise.
     */
    private static boolean onPlatform() {
        double initialHeight = Window.getHeight() - Platform.getPLATFORM().getIMAGE().getHeight() -
                PLAYER.getIMAGE_LEFT().getHeight() / 2 + 1;
        if (PLAYER.getPosition().y == initialHeight) {
            PLAYER.setSpeed(0);
            return true;
        } else {
            for (FlyingPlatform flyingPlatform : FlyingPlatform.getList()) {
                double lengthDifference = PLAYER.getPosition().x - flyingPlatform.getPosition().x;
                if (-flyingPlatform.getHALF_LENGTH() < lengthDifference &&
                        lengthDifference < flyingPlatform.getHALF_LENGTH()) {
                    double heightDifference = flyingPlatform.getPosition().y - PLAYER.getPosition().y;
                    if (flyingPlatform.getHALF_HEIGHT() - 1 <= heightDifference &&
                            heightDifference <= flyingPlatform.getHALF_HEIGHT()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Updates the height of the player based on whether they are jumping or on/not on a platform.
     * @param input The input from the player.
     * @param doingUp A boolean indicating if the player is moving up.
     */
    public static void updateHeight(Input input, boolean doingUp) {
        double initialHeight = Window.getHeight() - Platform.getPLATFORM().getIMAGE().getHeight() -
                PLAYER.getIMAGE_LEFT().getHeight() / 2 + 1;

        if (Player.onPlatform()) {
            if (input.wasPressed(Keys.UP)) {
                doingUp = true;
                PLAYER.setSpeed(-20);
            } else {
                PLAYER.setSpeed(0);
            }
        } else {
            if (doingUp && Player.getPLAYER().getSpeed() < 0) {
                PLAYER.increaseSpeed();
            } else if (Player.getPLAYER().getSpeed() == 0 && doingUp){
                doingUp = false;
                Player.getPLAYER().increaseSpeed();
            } else {
                if (Player.getPLAYER().getPosition().y + Player.getPLAYER().getIMAGE_LEFT().getHeight() / 2 +
                        PLAYER.getSpeed() >= Window.getHeight() - Platform.getPLATFORM().getIMAGE().getHeight()) {
                    PLAYER.setHeight(initialHeight);
                    PLAYER.setSpeed(0);
                } else {
                    PLAYER.increaseSpeed();
                }
            }
        }

        PLAYER.setPositionY(PLAYER.getPositionY() + PLAYER.speed);
    }

    /**
     * Increases the player's score by the value of the collected coin.
     * @param coin The collected coin.
     */
    public void updateScore(Coin coin) {
        score += coin.getVALUE() * Coin.getScoreMultiplier();
    }

    /**
     * Renders the image of the player according to the direction they are facing.
     */
    public void render() {
        if (this.direction == Keys.LEFT) {
            IMAGE_LEFT.draw(this.getPositionX(), this.getPositionY());
        } else {
            IMAGE_RIGHT.draw(this.getPositionX(), this.getPositionY());
        }
    }

    /**
     * Gets the instance of the player.
     * @return The instance of the player.
     */
    public static Player getPLAYER() {
        return PLAYER;
    }

    /**
     * Sets the instance of the player.
     * @param position The position of the player.
     * @param speed The speed of the player.
     * @param radius The radius of the player.
     * @param health The health of the player.
     * @param height The height of the player.
     * @param imageLeft The image of the player facing left.
     * @param imageRight The image of the player facing right.
     */
    public static void setPLAYER(Point position, int speed, double radius, double health, int height, Image imageLeft,
                                 Image imageRight) {
        if (PLAYER == null) {
            PLAYER = new Player(position, speed, radius, health, height, imageLeft, imageRight);
        }
    }

    /**
     * Resets the instance of the player.
     */
    public static void resetPLAYER() {
        PLAYER = null;
    }

    /**
     * Gets the image of the player facing left.
     * @return The image of the player facing left.
     */
    public Image getIMAGE_LEFT() {
        return IMAGE_LEFT;
    }

    /**
     * Sets the invincibility status of the player.
     * @param invincible The invincibility status.
     */
    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    /**
     * Gets the invincibility status of the player.
     * @return True if the player is invincible, false otherwise.
     */
    public boolean isInvincible() {
        return invincible;
    }

    /**
     * Gets the score of the player.
     * @return The score of the player.
     */
    public int getScore() {
        return score;
    }

    /**
     * Gets the direction the player is facing.
     * @return The direction the player is facing.
     */
    public Keys getDirection() {
        return direction;
    }

    /**
     * Sets the direction the player is facing.
     * @param direction The direction the player is facing.
     */
    public void setDirection(Keys direction) {
        this.direction = direction;
    }

    /**
     * Gets the speed of the player.
     * @return The speed of the player.
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Sets the speed of the player.
     * @param speed The new speed of the player.
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    /**
     * Sets the height of the player.
     * @param height The new height of the player.
     */
    public void setHeight(double height) {
        this.setPositionY(height);
    }
}
