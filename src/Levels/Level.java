package Levels;

import Entities.Actors.EnemyBoss;
import Entities.Actors.Player;
import Entities.Collidables.Collectibles.Cherry;
import Entities.Collidables.Collectibles.Coin;
import Entities.Collidables.Collectibles.Star;
import Entities.Collidables.EndFlag;
import Entities.Collidables.Enemy;
import Entities.FlyingPlatform;
import Entities.Platform;
import Utilities.IOUtils;
import Utilities.Point;
import bagel.Image;

import java.util.Properties;

/**
 * Abstract class that represents a level in the game.
 */
public abstract class Level {

    private final String[][] CSV; // The CSV data for the level

    /**
     * Constructs a new Level object.
     *
     * @param filename The filename of the CSV file containing level data
     */
    public Level(String filename) {
        this.CSV = IOUtils.readCsv(filename);
    }

    /**
     * Sets up all entities for the level based on properties.
     *
     * @param gameProps The properties defining the game entities
     */
    public void setupEntities(Properties gameProps) {
        for (String[] strings : this.getCSV()) {
            switch (strings[0]) {
                case "PLATFORM":
                    Platform.setPLATFORM(
                            new Image(gameProps.getProperty("gameObjects.platform.image")),
                            new Point(Integer.parseInt(strings[1]), Integer.parseInt(strings[2])),
                            Integer.parseInt(gameProps.getProperty("gameObjects.platform.speed"))
                    );
                    break;
                case "FLYING_PLATFORM":
                    new FlyingPlatform(
                            new Point(Integer.parseInt(strings[1]), Integer.parseInt(strings[2])),
                            Integer.parseInt(gameProps.getProperty("gameObjects.flyingPlatform.speed")),
                            new Image(gameProps.getProperty("gameObjects.flyingPlatform.image")),
                            Integer.parseInt(gameProps.getProperty("gameObjects.flyingPlatform.halfLength")),
                            Integer.parseInt(gameProps.getProperty("gameObjects.flyingPlatform.halfHeight")),
                            Integer.parseInt(gameProps.getProperty("gameObjects.flyingPlatform.maxRandomDisplacementX")),
                            Integer.parseInt(gameProps.getProperty("gameObjects.flyingPlatform.randomSpeed"))
                    );
                    break;
                case "PLAYER":
                    Player.setPLAYER(
                            new Point(Integer.parseInt(strings[1]), Integer.parseInt(strings[2])),
                            20,
                            Double.parseDouble(gameProps.getProperty("gameObjects.player.radius")),
                            Double.parseDouble(gameProps.getProperty("gameObjects.player.health")),
                            (int) new Image(gameProps.getProperty("gameObjects.player.imageLeft")).getHeight(),
                            new Image(gameProps.getProperty("gameObjects.player.imageLeft")),
                            new Image(gameProps.getProperty("gameObjects.player.imageRight"))
                    );
                    break;
                case "ENEMY_BOSS":
                    EnemyBoss.setENEMY_BOSS(
                            new Point(Integer.parseInt(strings[1]), Integer.parseInt(strings[2])),
                            Integer.parseInt(gameProps.getProperty("gameObjects.enemyBoss.speed")),
                            Double.parseDouble(gameProps.getProperty("gameObjects.enemyBoss.radius")),
                            Double.parseDouble(gameProps.getProperty("gameObjects.enemyBoss.health")),
                            (int) new Image(gameProps.getProperty("gameObjects.enemyBoss.image")).getHeight(),
                            new Image(gameProps.getProperty("gameObjects.enemyBoss.image")),
                            Double.parseDouble(gameProps.getProperty("gameObjects.enemyBoss.activationRadius"))
                    );
                    break;
                case "COIN":
                    new Coin(
                            new Point(Integer.parseInt(strings[1]), Integer.parseInt(strings[2])),
                            Integer.parseInt(gameProps.getProperty("gameObjects.coin.speed")),
                            new Image(gameProps.getProperty("gameObjects.coin.image")),
                            Double.parseDouble(gameProps.getProperty("gameObjects.coin.radius")),
                            Integer.parseInt(gameProps.getProperty("gameObjects.coin.value"))
                    );
                    break;
                case "DOUBLE_SCORE":
                    new Cherry(
                            new Point(Integer.parseInt(strings[1]), Integer.parseInt(strings[2])),
                            Integer.parseInt(gameProps.getProperty("gameObjects.doubleScore.speed")),
                            new Image(gameProps.getProperty("gameObjects.doubleScore.image")),
                            Double.parseDouble(gameProps.getProperty("gameObjects.doubleScore.radius"))
                    );
                    break;
                case "INVINCIBLE_POWER":
                    new Star(
                            new Point(Integer.parseInt(strings[1]), Integer.parseInt(strings[2])),
                            Integer.parseInt(gameProps.getProperty("gameObjects.invinciblePower.speed")),
                            new Image(gameProps.getProperty("gameObjects.invinciblePower.image")),
                            Double.parseDouble(gameProps.getProperty("gameObjects.invinciblePower.radius"))
                    );
                    break;
                case "ENEMY":
                    new Enemy(
                            new Point(Integer.parseInt(strings[1]), Integer.parseInt(strings[2])),
                            Integer.parseInt(gameProps.getProperty("gameObjects.enemy.speed")),
                            new Image(gameProps.getProperty("gameObjects.enemy.image")),
                            Double.parseDouble(gameProps.getProperty("gameObjects.enemy.radius")),
                            Double.parseDouble(gameProps.getProperty("gameObjects.enemy.damageSize")),
                            Integer.parseInt(gameProps.getProperty("gameObjects.enemy.maxRandomDisplacementX")),
                            Integer.parseInt(gameProps.getProperty("gameObjects.enemy.randomSpeed"))
                    );
                    break;
                case "END_FLAG":
                    EndFlag.setEND_FLAG(
                            new Point(Integer.parseInt(strings[1]), Integer.parseInt(strings[2])),
                            Integer.parseInt(gameProps.getProperty("gameObjects.endFlag.speed")),
                            new Image(gameProps.getProperty("gameObjects.endFlag.image")),
                            Double.parseDouble(gameProps.getProperty("gameObjects.endFlag.radius"))
                    );
                    break;
            }
        }
        // Setup Timers
        Cherry.setTimerDuration(Integer.parseInt(gameProps.getProperty("gameObjects.doubleScore.maxFrames")));
        Star.setTimerDuration(Integer.parseInt(gameProps.getProperty("gameObjects.doubleScore.maxFrames")));
    }

    /**
     * Retrieves the CSV data for the level.
     *
     * @return The CSV data for the level
     */
    public String[][] getCSV() {
        return CSV;
    }
}
