import Entities.*;
import Entities.Actors.*;
import Entities.Collidables.*;
import Entities.Collidables.Collectibles.*;
import Levels.Level1;
import Levels.Level2;
import Levels.Level3;
import Texts.*;
import bagel.*;
import java.util.Properties;
import Utilities.*;
import bagel.util.Colour;

/**
 * Skeleton Code for SWEN20003 Project 2, Semester 1, 2024
 * Carlvince Tan, 1462092
 */
public class ShadowMario extends AbstractGame {
    // Main Render variables
    private final Image BACKGROUND_IMAGE;
    private final Level1 LEVEL_1; // Stores CSV with initial positions of all entities for Levels.Level 1
    private final Level2 LEVEL_2; // Stores CSV with initial positions of all entities for Levels.Level 2
    private final Level3 LEVEL_3; // Stores CSV with initial positions of all entities for Levels.Level 3

    // Fonts
    private Title title;
    private Instruction instruction;
    private Message message;
    private Score score;
    private Health playerHealth;
    private Health enemyBossHealth;

    // Properties
    private final Properties gameProps;

    // Logic variables
    private boolean gameStarted;
    private boolean displayWinPage;
    private boolean displayLosePage;
    private boolean numberPressed;
    private boolean doingUp;

    // Frame counter
    private int frameCount;

    /**
     * Constructor for ShadowMario game.
     *
     * @param game_props    Properties for game settings
     * @param message_props Properties for game messages
     */
    public ShadowMario(Properties game_props, Properties message_props) {
        super(Integer.parseInt(game_props.getProperty("windowWidth")),
              Integer.parseInt(game_props.getProperty("windowHeight")),
              message_props.getProperty("title"));

        // Gives Update function access to game_props for resetting entities
        this.gameProps = game_props;

        // Main Render Variables
        BACKGROUND_IMAGE = new Image(game_props.getProperty("backgroundImage"));
        LEVEL_1 = new Level1("res/level1.csv");
        LEVEL_2 = new Level2("res/level2.csv");
        LEVEL_3 = new Level3("res/level3.csv");

        // Logic Variables
        gameStarted = false;
        displayWinPage = false;
        displayLosePage = false;
        numberPressed = false;
        doingUp = false;

        // Initialize frame counter
        frameCount = 0;

        // Initializes font variables
        setupTexts(game_props, message_props);

    }

    /**
     * Entry point for the program.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        Properties game_props = IOUtils.readPropertiesFile("res/app.properties");
        Properties message_props = IOUtils.readPropertiesFile("res/message_en.properties");
        ShadowMario game = new ShadowMario(game_props, message_props);
        game.run();
    }

    /**
     * Performs a state update of the selected level.
     * Allows the game to exit when the escape key is pressed.
     * Handle screen navigation between levels and instruction pages here.
     *
     * @param input Input object for handling user input
     */
    @Override
    protected void update(Input input) {
        // Close window
        if (input.wasPressed(Keys.ESCAPE)){
            Window.close();
        }

        // Render Background
        BACKGROUND_IMAGE.draw(Window.getWidth() / 2.0, Window.getHeight() / 2.0);

        // Title Page
        if (!numberPressed) {
            // Draw Title
            title.render();

            // Draw Instructions
            instruction.render();

            if (input.wasPressed(Keys.NUM_1)) {
                LEVEL_1.setupEntities(gameProps);
                numberPressed = true;
            } else if (input.wasPressed(Keys.NUM_2)){
                LEVEL_2.setupEntities(gameProps);
                numberPressed = true;
            } else if (input.wasPressed(Keys.NUM_3)) {
                LEVEL_3.setupEntities(gameProps);
                numberPressed = true;
            }
        }
        // Game Page
        else {
            // Initial render
            if (!gameStarted) {
                initialRenderEntities();
            }
            // GameWon Page
            else if (displayWinPage) {
                message.renderGameWon();
                resetProperties(input);
            }
            // GameOver Page
            else if (displayLosePage) {
                message.renderGameOver();
                resetProperties(input);
            }
            // Game Page
            else {
                // Update position based on input
                if (Player.getPLAYER().checkAlive()) {
                    updatePositions(input);
                }

                // Check collisions with coins and enemies while updating health and score values
                checkCollisions();

                // Check if Win or Lost
                if (Player.getPLAYER().getPosition().y - Player.getPLAYER().getIMAGE_LEFT().getHeight() / 2 >
                        Window.getHeight()) {
                    displayLosePage = true;
                }

                if (Player.getPLAYER().checkCollision(EndFlag.getEND_FLAG())) {
                    if (EnemyBoss.getENEMY_BOSS() == null) {
                        displayWinPage = true;
                    } else if (EnemyBoss.getENEMY_BOSS().getHealth() <= 0){
                        displayWinPage = true;
                    }
                }

                // Load all updated entities
                renderEntities();
            }
        }

        // Update frameCount
        frameCount++;
    }

    /**
     * Render Objects in initial positions
     */
    private void initialRenderEntities() {
        Player.getPLAYER().setDirection(Keys.RIGHT);
        Player.getPLAYER().render();
        if (EnemyBoss.getENEMY_BOSS() != null) {
            EnemyBoss.getENEMY_BOSS().render();
        }
        EndFlag.getEND_FLAG().render();
        Coin.renderAll();
        Enemy.renderAll();
        FlyingPlatform.renderAll();
        Cherry.renderAll();
        Star.renderAll();
        Platform.getPLATFORM().render();
        gameStarted = true;
    }

    /**
     * Stores all text fonts from message_en.properties
     *
     *
     * @param game_props    Properties for game settings
     * @param message_props Properties for game messages
     */
    private void setupTexts(Properties game_props, Properties message_props) {
        title = new Title (
                message_props.getProperty("title"),
                Integer.parseInt(game_props.getProperty("title.x")),
                Integer.parseInt(game_props.getProperty("title.y")),
                new Font(game_props.getProperty("font"), Integer.parseInt(game_props.getProperty("title.fontSize")))
        );
        score = new Score (
                message_props.getProperty("score"),
                Integer.parseInt(game_props.getProperty("score.x")),
                Integer.parseInt(game_props.getProperty("score.y")),
                new Font(game_props.getProperty("font"), Integer.parseInt(game_props.getProperty("score.fontSize")))
        );
        message = new Message (
                message_props.getProperty("gameOver"),
                message_props.getProperty("gameWon"),
                Integer.parseInt(game_props.getProperty("windowWidth")),
                Integer.parseInt(game_props.getProperty("message.y")),
                new Font(game_props.getProperty("font"), Integer.parseInt(game_props.getProperty("message.fontSize")))
        );
        instruction = new Instruction (
                message_props.getProperty("instruction"),
                Integer.parseInt(game_props.getProperty("windowWidth")),
                Integer.parseInt(game_props.getProperty("instruction.y")),
                new Font(game_props.getProperty("font"), Integer.parseInt(game_props.getProperty("instruction.fontSize")))
        );
        playerHealth = new Health (
                message_props.getProperty("health"),
                Integer.parseInt(game_props.getProperty("playerHealth.x")),
                Integer.parseInt(game_props.getProperty("playerHealth.y")),
                new Font(game_props.getProperty("font"), Integer.parseInt(game_props.getProperty("playerHealth.fontSize")))
        );
        enemyBossHealth = new Health (
                message_props.getProperty("health"),
                Integer.parseInt(game_props.getProperty("enemyBossHealth.x")),
                Integer.parseInt(game_props.getProperty("enemyBossHealth.y")),
                new Font(game_props.getProperty("font"), Integer.parseInt(game_props.getProperty("enemyBossHealth.fontSize")))
        );
    }

    /**
     * Update positions for all objects when arrow keys are pressed.
     *
     * @param input Input object for handling user input
     */
    private void updatePositions(Input input) {
        // Left arrow key
        if (input.isDown(Keys.LEFT)) {
            Platform.getPLATFORM().updatePosition(Platform.getPLATFORM(),Keys.LEFT, Player.getPLAYER());
            EndFlag.getEND_FLAG().updatePosition(Platform.getPLATFORM(), Keys.LEFT, Player.getPLAYER());
            if (EnemyBoss.getENEMY_BOSS() != null) {
                EnemyBoss.getENEMY_BOSS().updatePosition(Platform.getPLATFORM(), Keys.LEFT, Player.getPLAYER());
            }

            Coin.updatePositions(Platform.getPLATFORM(), Keys.LEFT, Player.getPLAYER());
            Star.updatePositions(Platform.getPLATFORM(), Keys.LEFT, Player.getPLAYER());
            Cherry.updatePositions(Platform.getPLATFORM(), Keys.LEFT, Player.getPLAYER());
            Enemy.updatePositions(Platform.getPLATFORM(), Keys.LEFT, Player.getPLAYER());
            FlyingPlatform.updatePositions(Platform.getPLATFORM(), Keys.LEFT, Player.getPLAYER());

            Player.getPLAYER().setDirection(Keys.LEFT);
        }
        // Right arrow key
        else if (input.isDown(Keys.RIGHT)) {
            Platform.getPLATFORM().updatePosition(Platform.getPLATFORM(), Keys.RIGHT, Player.getPLAYER());
            EndFlag.getEND_FLAG().updatePosition(Platform.getPLATFORM(), Keys.RIGHT, Player.getPLAYER());
            if (EnemyBoss.getENEMY_BOSS() != null) {
                EnemyBoss.getENEMY_BOSS().updatePosition(Platform.getPLATFORM(), Keys.RIGHT, Player.getPLAYER());
            }

            Coin.updatePositions(Platform.getPLATFORM(), Keys.RIGHT, Player.getPLAYER());
            Star.updatePositions(Platform.getPLATFORM(), Keys.RIGHT, Player.getPLAYER());
            Cherry.updatePositions(Platform.getPLATFORM(), Keys.RIGHT, Player.getPLAYER());
            Enemy.updatePositions(Platform.getPLATFORM(), Keys.RIGHT, Player.getPLAYER());
            FlyingPlatform.updatePositions(Platform.getPLATFORM(), Keys.RIGHT, Player.getPLAYER());

            Player.getPLAYER().setDirection(Keys.RIGHT);
        }

        // Create and update Fireballs from Player and enemyBoss
        Fireball.updatePositions(Platform.getPLATFORM(), Player.getPLAYER());
        if (EnemyBoss.getENEMY_BOSS()!= null) {
            EnemyBoss.battle(gameProps, input, frameCount);
        }

        // Updates height of player according to input and position of player
        Player.updateHeight(input, doingUp);

        // Update displacements of random moving entities
        Enemy.updateDisplacements();
        FlyingPlatform.updateDisplacements();
    }

    /**
     * Check collisions with coins and enemies while updating health and score values
     */
    private void checkCollisions() {
        // Check collision with coin
        for (Coin coin : Coin.getList()) {
            if (Player.getPLAYER().checkCollision(coin) || coin.isCollided()) {
                coin.setCollided();
                if (!coin.isCollected()) {
                    Player.getPLAYER().updateScore(coin);
                    coin.setCollected(true);
                }
                coin.floatUp(); // Float animation
            }
        }

        // Check collision with start
        for (Star star : Star.getList()) {
            if (Player.getPLAYER().checkCollision(star) || star.isCollided()) {
                star.setCollided();
                if (!star.isTimerStarted()) {
                    Star.startTimer(Player.getPLAYER());
                    star.setTimerStarted(true);
                }
                star.floatUp(); // Float animation
            }
        }

        // Check collision with cherry
        for (Cherry cherry : Cherry.getList()) {
            if (Player.getPLAYER().checkCollision(cherry) || cherry.isCollided()) {
                cherry.setCollided();
                if(!cherry.isTimerStarted()) {
                    Cherry.startTimer();
                    cherry.setTimerStarted(true);
                }
                cherry.floatUp(); // Float animation
            }
        }

        // Check collisions with fireballs and updates health of actors
        for (Fireball fireball : Fireball.getList()) {
            if (Player.getPLAYER().checkCollision(fireball) && fireball.getShooter() == EnemyBoss.getENEMY_BOSS() && !fireball.isHit()) {
                Player.getPLAYER().loseHealth(fireball.getDAMAGE());
                fireball.setHit(true);
            }
            if (EnemyBoss.getENEMY_BOSS().checkCollision(fireball) && fireball.getShooter() == Player.getPLAYER() && !fireball.isHit()) {
                EnemyBoss.getENEMY_BOSS().loseHealth(fireball.getDAMAGE());
                fireball.setHit(true);
            }
        }

        // Update timers
        Star.updateTimer(Player.getPLAYER());
        Cherry.updateTimer();

        // Update score
        score.render(Player.getPLAYER().getScore());

        // Check collision with enemies
        for (Enemy enemy : Enemy.getList()) {
            if (Player.getPLAYER().checkCollision(enemy) && !enemy.isCollided() && Player.getPLAYER().checkAlive()) {
                Player.getPLAYER().loseHealth(enemy.getDAMAGE());
                enemy.setCollided();
            }
        }

        // Update Health
        playerHealth.render(Player.getPLAYER().getHealth(), new DrawOptions().setBlendColour(Colour.WHITE));
        if (EnemyBoss.getENEMY_BOSS() != null) {
            enemyBossHealth.render(EnemyBoss.getENEMY_BOSS().getHealth(), new DrawOptions().setBlendColour(Colour.RED));
        }

        // Fall if not alive anymore
        if (!Player.getPLAYER().checkAlive()) {
            Player.getPLAYER().fall(Window.getHeight()); // Fall animation
        }
        if (EnemyBoss.getENEMY_BOSS() != null) {
            if (!EnemyBoss.getENEMY_BOSS().checkAlive()) {
                EnemyBoss.getENEMY_BOSS().fall(Window.getHeight()); // Fall animation
            }
        }
    }

    /**
     *  Render all objects that have been updated
     */
    private void renderEntities() {
        Platform.getPLATFORM().render();
        EndFlag.getEND_FLAG().render();
        Coin.renderAll();
        Star.renderAll();
        Cherry.renderAll();
        Enemy.renderAll();
        Fireball.renderAll();
        FlyingPlatform.renderAll();
        Player.getPLAYER().render();
        if (EnemyBoss.getENEMY_BOSS() != null) {
            EnemyBoss.getENEMY_BOSS().render();
        }
    }

    /**
     * Resets all properties to default
     *
     * @param input Input object for handling user input
     */
    private void resetProperties(Input input) {
        if (input.wasPressed(Keys.SPACE)) {
            // Reset Logic variables
            gameStarted = false;
            displayWinPage = false;
            displayLosePage = false;
            numberPressed = false;
            doingUp = false;

            // Reset Objects
            Player.resetPLAYER();
            EnemyBoss.resetENEMY_BOSS();
            EndFlag.resetEND_FLAG();
            Platform.resetPLATFORM();
            Coin.clearList();
            Enemy.clearList();
            Cherry.clearList();
            Star.clearList();
            FlyingPlatform.clearList();
            Fireball.clearList();
        }
    }

}
