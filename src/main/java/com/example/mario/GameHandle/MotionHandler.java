package com.example.mario.GameHandle;

import com.example.mario.Gun.*;
import com.example.mario.Items.*;
import com.example.mario.Mario.*;
import com.example.mario.SuperMario;
import com.example.mario.blocks.*;
import com.example.mario.controllers.*;
import com.example.mario.enemies.*;
import com.example.mario.enemies.bossFight.*;
import com.example.mario.levels.*;
import com.example.mario.manager.*;
import com.example.mario.user.*;
import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.util.*;

public class MotionHandler {
    Stage stage;
    Pane pane;
    private double gravity = 0.5;
    private double velocity = 0;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean jumpStop = false;
    private boolean isMapMoving = false;
    private boolean isGamePause = false;
    private int mapMoveCounter = 0;
    private int mapMoveDownCounter = 0;
    private boolean isMapMustMovingDown = false;
    private final Mario mario = new Mario(30, 30, 30, -60);
    private final List<Enemy> enemies;
    private final List<Block> blocks;
    private final List<BackGround> backGrounds;
    private final List<Item> items;
    private final List<Gun> shots = new ArrayList<>();
    private int level;
    private final GameLabelController gameLabelController = GameLabelController.getInstance();
    PauseHandle pauseHandle=PauseHandle.getInstance(this);
    private GameData gameData = GameData.getInstance();
    private final UserData userData = UserData.getInstance();
    private final List<Integer> saveData = new ArrayList<>();
    private final ItemCollision itemCollision;
    private final MarioCollision marioCollision;
    private final MarioAnimation marioAnimation;
    private final EnemyCollision enemyCollision;
    private final ShotCollision shotCollision;
    private BowserMovement bowserMovement;
    private final BowserAttack bowserAttack;
    private UsingAttacks usingAttacks;
    private GameSound gameSound=GameSound.getInstance(this);
    private SetLevel setLevel;
    private Bowser bowser;
    AnimationTimer timer;
    Timeline bossMover;

    KeyFrame bossMoverKeyFrame = new KeyFrame(Duration.millis(20), event -> {
        try {
            if (!usingAttacks.isUsingAnotherAttack() || bowser.isJumping()) {
                bowserMovement.bowserAllMove();
            }
        } catch (Exception ignored) {
        }
    });
    private final String path = "./src/main/resources/GamaData/";
    JsonManager jsonManager = new JsonManager(path + "users.json");
    JsonManager jsonManager1 = new JsonManager(path + userData.getCurrentUser().getUserName() + "/game1.json");
    JsonManager jsonManager2 = new JsonManager(path + userData.getCurrentUser().getUserName() + "/game2.json");
    JsonManager jsonManager3 = new JsonManager(path + userData.getCurrentUser().getUserName() + "/game3.json");

    public MotionHandler(List<Block> blocks, List<Enemy> enemies, List<BackGround> backGrounds,
                         List<Item> items, Stage stage, Pane pane,int level,int marioState) throws Exception {
        this.stage = stage;
        this.pane = pane;
        this.blocks = blocks;
        this.enemies = enemies;
        this.backGrounds = backGrounds;
        this.items = items;
        this.level=level;
        mario.setMarioState(marioState);
        marioAnimation = new MarioAnimation(mario);
        marioCollision = new MarioCollision(mario, this.blocks, new BlockCollision(this.pane, this.items, mario));
        itemCollision = new ItemCollision(mario, items);
        enemyCollision = new EnemyCollision(this);
        shotCollision = new ShotCollision(this.blocks, this.enemies, this.shots, enemyCollision);
        bowserMovement = new BowserMovement(this);
        bowserAttack = new BowserAttack(this);
        usingAttacks = new UsingAttacks(this);
        setLevel=new SetLevel(level);
        bowser = bowserFounder();
        gameLabelController.setPointChange(gameData.getPoint());
        gameLabelController.setHpChange(gameData.getHp());
        gameLabelController.setCoinChange(gameData.getCoin());
        bossMover=new Timeline(bossMoverKeyFrame);
        bossMover.setCycleCount(Animation.INDEFINITE);
        bossMover.play();
        jsonJob();
        pane.getChildren().add(mario);
        stage.getScene().setOnKeyPressed(event -> {
            if (mario.isCanMove()&&!isGamePause) {
                switch (event.getCode()) {
                    case RIGHT -> {
                        if (mario.isNausea()) {
                            if (mario.getMarioState() != 0) {
                                mario.setSit(true);
                                marioAnimation.marioSiting();
                            }
                        } else isRight = true;
                    }
                    case LEFT -> {
                        if (mario.isNausea()) {
                            isRight = true;
                        } else isLeft = true;
                    }
                    case UP -> {
                        if (mario.isNausea()) {
                            isLeft = true;
                        } else if (marioCollision.isDownCollusion() && !jumpStop && !mario.isSit()) {
                            marioAnimation.setJumping(true);
                            marioAnimation.startMoving();
                            velocity = mario.getJumpVelocity();
                            mario.setLayoutY(mario.getLayoutY() - 5);
                        }
                    }
                    case DOWN -> {
                        if (mario.isNausea()) {
                            marioAnimation.setJumping(true);
                            marioAnimation.startMoving();
                            velocity = mario.getJumpVelocity();
                            mario.setLayoutY(mario.getLayoutY() - 5);
                        } else if (mario.getMarioState() != 0) {
                            mario.setSit(true);
                            marioAnimation.marioSiting();
                        }
                    }
                    case ESCAPE -> {
                        try {
                            gameStop();
                            stopFxml();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    case R -> mario.setDead(true);
                    case D -> {
                        if (mario.isCanShoot() && !mario.isShotCoolDown()) {
                            mario.setIsShotCoolDown(true);
                            Shot shot = new Shot(10, 16, (int) (mario.getLayoutX() + mario.getFitWidth() - 10), (int) (mario.getLayoutY() + mario.getFitHeight() / 2 - 8), marioAnimation.isMarioMovingLeft());
                            shots.add(shot);
                            pane.getChildren().add(shot);
                        }
                    }
                    case S -> {
                        if (!mario.isSwordCoolDown() && shotCollision.usageCost()) {
                            mario.setIsSwordCoolDown(true);
                            Sword sword = new Sword(30, 20, (int) (mario.getLayoutX() + mario.getFitWidth() - 10), (int) (mario.getLayoutY() + mario.getFitHeight() / 2 - 10), marioAnimation.isMarioMovingLeft());
                            shots.add(sword);
                            pane.getChildren().add(sword);
                        }
                    }
                }
            } else {
                switch (event.getCode()) {
                    case RIGHT -> bowserAttack.setGrabRightCounter(bowserAttack.getGrabRightCounter() + 1);
                    case LEFT -> bowserAttack.setGrabLeftCounter(bowserAttack.getGrabLeftCounter() + 1);
                }
            }
            if(event.getCode().equals(KeyCode.A)) {
                try {
                    saveGame();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        });
        stage.getScene().setOnKeyReleased(event -> {
            if (mario.isNausea()) {
                switch (event.getCode()) {
                    case LEFT -> {
                        isRight = false;
                        if (marioCollision.isDownCollusion()) marioAnimation.stopMoving();
                        marioAnimation.setMoving(false);
                    }

                    case UP -> {
                        isLeft = false;
                        if (marioCollision.isDownCollusion()) marioAnimation.stopMoving();
                        marioAnimation.setMarioMovingLeft(false);
                        marioAnimation.setMoving(false);
                    }
                    case RIGHT -> {
                        if (mario.getMarioState() != 0) {
                            mario.setSit(false);
                            marioAnimation.marioSiting();
                        }
                    }
                }
            } else {
                switch (event.getCode()) {
                    case RIGHT -> {
                        isRight = false;
                        if (marioCollision.isDownCollusion()) marioAnimation.stopMoving();
                        marioAnimation.setMoving(false);
                    }

                    case LEFT -> {
                        isLeft = false;
                        if (marioCollision.isDownCollusion()) marioAnimation.stopMoving();
                        marioAnimation.setMarioMovingLeft(false);
                        marioAnimation.setMoving(false);
                    }
                    case DOWN -> {
                        if (mario.getMarioState() != 0) {
                            mario.setSit(false);
                            marioAnimation.marioSiting();
                        }
                    }
                }
            }
        });
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                itemMovement();
                enemyMovement();
                isMapMoving = false;
                checkTime();
                if (mario.isDead()) {
                    marioAnimation.startMoving();
                }
                if (marioAnimation.isDyingFinished()) {
                    try {
                        doDead();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                if (!mario.isDead()) {
                    if (isWin()) {

                        timer.stop();
                        gameData.setPoint(gameData.getPoint() + gameData.getTime());
                        gameData.setTime(100);
                        GameLabelController.timeline.stop();
                        gameLabelController.setPointChange(gameData.getPoint());
                        try {
                            if(!setLevel.levelSet(mario.getMarioState())){
                                gameData.setPoint(gameData.getPoint() + gameData.getHp() * 20);
                                userData.getCurrentUser().checkPoint(gameData.getPoint());
                                userData.getCurrentUser().setCoins(userData.getCurrentUser().getCoins() + gameData.getCoin());
                                jsonManager.writeArray(userData.getUsers());
                                loadMainMenu();
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    usingAttacks.useAttack();
                    isMapMustMovingDown();
                    enemyCollision.isEnemyCollision();
                    marioCollision.collision();
                    itemCollision.allCollision();
                    shotCollision.checkCollision();
                    if (!marioCollision.isRightCollusion() && isRight && mario.getLayoutX() > (float) SuperMario.getWidth() / 2 && !isAllBlockMoveRight()) {
                        mapRightController();
                        isMapMoving = true;
                    }
                    a:
                    if (isRight && !marioCollision.isRightCollusion()) {
                        marioAnimation.setMoving(true);
                        marioAnimation.startMoving();
                        if (mario.getLayoutX() + mario.getFitWidth() > SuperMario.getWidth() - 20)
                            break a;
                        if (!isMapMoving)
                            mario.setLayoutX(mario.getLayoutX() + mario.getxVelocity());
                    }
                    b:
                    if (isLeft && !marioCollision.isLeftCollusion()) {
                        marioAnimation.setMarioMovingLeft(true);
                        marioAnimation.setMoving(true);
                        marioAnimation.startMoving();
                        if (mario.getLayoutX() < 0)
                            break b;
                        mario.setLayoutX(mario.getLayoutX() - mario.getxVelocity());
                    }
                    if (!marioCollision.isDownCollusion()) {
                        if (isMapMustMovingDown && !isAllBlockMoveDown()) {
                            mapDownController();
                        } else {
                            if (marioCollision.isUpCollusion()) velocity = 0;
                            velocity -= gravity;
                            mario.setLayoutY(mario.getLayoutY() - velocity);
                        }
                    } else {
                        isMapMustMovingDown = false;
                        velocity = 0;
                        if (marioAnimation.isJumping()) {
                            marioAnimation.setJumping(false);
                            marioAnimation.stopMoving();
                        }
                    }
                }
            }
        };
        timer.start();
    }

    public boolean isWin() {
        for (Block win : blocks) {
            if(win instanceof WinBlock &&mario.getBoundsInParent().intersects(win.getBoundsInParent()))return true;
        }
        return false;
    }

    public void doDead() throws IOException {
        mario.setDead(false);
        mario.setMarioState(0);
        if (gameData.getPoint() >= 20) {
            gameData.setPoint(gameData.getPoint() - 20);
            gameLabelController.setPointChange(gameData.getPoint());
        }
        isMapMustMovingDown = false;
        marioAnimation.setDyingFinished(false);
        mario.setLayoutX(30);
        mario.setLayoutY(-60);
        gameData.setTime(100);
        gameData.setHp(gameData.getHp() - 1);
        if (gameData.getHp() < 1) {
            GameLabelController.timeline.stop();

            timer.stop();
            GameLabelController.timeline.stop();
            userData.getCurrentUser().checkPoint(gameData.getPoint());
            jsonManager.writeArray(userData.getUsers());
            gameData = GameData.resetInstance();
            loadMainMenu();
            SuperMario.menuSong.stop();
        }
        for (Block block : blocks) block.setLayoutX(block.getLayoutX() + mapMoveCounter * 3);
        for (Block block : blocks) block.setLayoutY(block.getLayoutY() + mapMoveDownCounter * 4);
        for (Enemy enemy : enemies) enemy.setLayoutX(enemy.getLayoutX() + mapMoveCounter * 3);
        for (Enemy enemy : enemies) enemy.setLayoutY(enemy.getLayoutY() + mapMoveDownCounter * 4);
        for (BackGround backGround : backGrounds) backGround.setLayoutX(backGround.getLayoutX() + mapMoveCounter * 3);
        for (BackGround backGround : backGrounds)
            backGround.setLayoutY(backGround.getLayoutY() + mapMoveDownCounter * 4);
        for (Item item : items) item.setLayoutX(item.getLayoutX() + mapMoveCounter * 3);
        for (Item item : items) item.setLayoutY(item.getLayoutY() + mapMoveDownCounter * 4);
        mapMoveDownCounter = 0;
        mapMoveCounter = 0;
    }

    public boolean isAllBlockMoveRight() {
        for (Block block : blocks) {
            if (block.getLayoutX() > SuperMario.getWidth())
                return false;
        }
        return true;
    }

    public boolean isAllBlockMoveDown() {
        for (Block block : blocks) {
            if (block.getLayoutY() > SuperMario.getHeight() && !(block instanceof WinBlock))
                return false;
        }
        return true;
    }

    public void mapRightController() {
        mapMoveCounter++;
        for (Block block : blocks) block.setLayoutX(block.getLayoutX() - 3);
        for (Enemy enemy : enemies) enemy.setLayoutX(enemy.getLayoutX() - 3);
        for (BackGround backGround : backGrounds) backGround.setLayoutX(backGround.getLayoutX() - 3);
        for (Item item : items) item.setLayoutX(item.getLayoutX() - 3);
    }

    public void mapDownController() {
        mapMoveDownCounter++;
        marioAnimation.falling();
        if (mario.getLayoutY() >= 210) mario.setLayoutY(mario.getLayoutY() - 10);
        for (Block block : blocks) block.setLayoutY(block.getLayoutY() - 4);
        for (BackGround backGround : backGrounds) backGround.setLayoutY(backGround.getLayoutY() - 4);
        for (Enemy enemy : enemies) enemy.setLayoutY(enemy.getLayoutY() - 4);
        for (Item item : items) item.setLayoutX(item.getLayoutX() - 4);

    }

    public void isMapMustMovingDown() {
        if (mario.getLayoutY() > SuperMario.getHeight() + 30) {
            isMapMustMovingDown = true;
        }
    }

    public void checkTime() {
        if (gameLabelController.getTime().getText().equals("0"))
            mario.setDead(true);
    }

    public void mapMoverRight(int num) {
        for (Block block : blocks) block.setLayoutX(block.getLayoutX() - num * 3);
        for (Enemy enemy : enemies) enemy.setLayoutX(enemy.getLayoutX() - num * 3);
        for (BackGround backGround : backGrounds) backGround.setLayoutX(backGround.getLayoutX() - num * 3);
        for (Item item : items) {
            item.setLayoutX(item.getLayoutX() - num * 3);
        }
    }

    public void mapMoverDown(int num) {
        for (Block block : blocks) block.setLayoutY(block.getLayoutY() - num * 5);
        for (Enemy enemy : enemies) enemy.setLayoutY(enemy.getLayoutY() - num * 5);
        for (BackGround backGround : backGrounds) backGround.setLayoutY(backGround.getLayoutY() - num * 5);
        for (Item item : items) item.setLayoutX(item.getLayoutX() - num * 5);
    }

    public void gameStop() {
        setGamePause(true);
        getBossMover().stop();
        GameLabelController.timeline.stop();
        getTimer().stop();
        //enemy stop:
        for (Enemy enemy : getEnemies()) {
            if (enemy instanceof Flower flower) flower.getTimeline().stop();
            if (enemy instanceof Koopa koopa) {
                koopa.getKoopaTimeline().stop();
                koopa.getKoopaAnimation().stop();
                koopa.getKoopaAnimationStopper().stop();
            }
            if (enemy instanceof Goompa goompa) goompa.getTimeline().stop();
            if (enemy instanceof Spiny spiny) spiny.getTimeline2().stop();
        }
        //block stop:
        for (Block block : getBlocks()) {
            if (block instanceof MysteryBlock mysteryBlock) mysteryBlock.getTimeline().stop();
        }
        //item stop:
        for (Item item : getItems()) {
            if (item instanceof Coin coin) {
                coin.getTimeline().stop();
                coin.getFallTimeline().stop();
            }
            if (item instanceof Mushroom mushroom) {
                mushroom.getTimeline().stop();
                mushroom.getDelayTimeLine().stop();
            }
            if (item instanceof Star star) {
                star.getDelayTimeline().stop();
                star.getTimeline().stop();
            }
            if (item instanceof MagicFlower magicFlower) magicFlower.getTimeline().stop();

        }
    }
    public void stopFxml() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/PauseGame.fxml").toURI().toURL());
        loader.setController(pauseHandle);
        Parent content = loader.load();
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.setHeight(SuperMario.getHeight());
        stage.setWidth(SuperMario.getWidth());
        stage.getIcons().add(SuperMario.getIcon());
        stage.setResizable(false);
        stage.setTitle(SuperMario.getStageTitle());
        stage.setX(SuperMario.getStageX());
        stage.setY(SuperMario.getStageY());
        stage.show();

    }

    public void loadMainMenu() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/MainMenu.fxml").toURI().toURL());
        Parent content = loader.load();
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.setHeight(SuperMario.getHeight());
        stage.setWidth(SuperMario.getWidth());
        stage.getIcons().add(SuperMario.getIcon());
        stage.setResizable(false);
        stage.setTitle(SuperMario.getStageTitle());
        stage.setX(SuperMario.getStageX());
        stage.setY(SuperMario.getStageY());
        stage.show();
    }

    public void itemMovement() {
        for (Item item : items) item.itemCollision(blocks);
    }

    public void enemyMovement() {
        for (Enemy enemy : enemies) enemy.enemyCollision(blocks);
    }

    public void saveGame() throws Exception {
        saveData.add(mapMoveCounter);
        saveData.add(mapMoveDownCounter);
        saveData.add(gameData.getPoint());
        saveData.add(gameData.getCoin());
        saveData.add(gameData.getTime());
        saveData.add(gameData.getHp());
        saveData.add((int) mario.getLayoutX());
        saveData.add((int) mario.getLayoutY());
        saveData.add(level);
        saveData.add(mario.getMarioState());
        GameLabelController.timeline.stop();

        timer.stop();
        gameData = GameData.resetInstance();
        loadMainMenu();
        jsonManager3.writeArray(jsonManager2.readArray(JsonManager.integerReference));
        jsonManager2.writeArray(jsonManager1.readArray(JsonManager.integerReference));
        jsonManager1.writeArray(saveData);
    }



    public void jsonJob() throws Exception {
        if (ChooseSaveController.isFirstSave()) {
            ChooseSaveController.setFirstSave(false);
            mapMoverRight(jsonManager1.readArray(JsonManager.integerReference).get(0));
            mapMoverDown(jsonManager1.readArray(JsonManager.integerReference).get(1));
            gameData.setPoint(jsonManager1.readArray(JsonManager.integerReference).get(2));
            gameData.setCoin(jsonManager1.readArray(JsonManager.integerReference).get(3));
            gameData.setTime(jsonManager1.readArray(JsonManager.integerReference).get(4));
            gameData.setHp(jsonManager1.readArray(JsonManager.integerReference).get(5));
            mario.setLayoutX(jsonManager1.readArray(JsonManager.integerReference).get(6));
            mario.setLayoutY(jsonManager1.readArray(JsonManager.integerReference).get(7));
            mapMoveCounter = jsonManager1.readArray(JsonManager.integerReference).get(0);
            mapMoveDownCounter = jsonManager1.readArray(JsonManager.integerReference).get(1);
            mario.setMarioState(jsonManager1.readArray(JsonManager.integerReference).get(9));
        } else if (ChooseSaveController.isSecondSave()) {
            ChooseSaveController.setSecondSave(false);
            mapMoverRight(jsonManager2.readArray(JsonManager.integerReference).get(0));
            mapMoverDown(jsonManager2.readArray(JsonManager.integerReference).get(1));
            gameData.setPoint(jsonManager2.readArray(JsonManager.integerReference).get(2));
            gameData.setCoin(jsonManager2.readArray(JsonManager.integerReference).get(3));
            gameData.setTime(jsonManager2.readArray(JsonManager.integerReference).get(4));
            gameData.setHp(jsonManager2.readArray(JsonManager.integerReference).get(5));
            mario.setLayoutX(jsonManager2.readArray(JsonManager.integerReference).get(6));
            mario.setLayoutY(jsonManager2.readArray(JsonManager.integerReference).get(7));
            mapMoveCounter = jsonManager2.readArray(JsonManager.integerReference).get(0);
            mapMoveDownCounter = jsonManager2.readArray(JsonManager.integerReference).get(1);
            mario.setMarioState(jsonManager2.readArray(JsonManager.integerReference).get(9));
        } else if (ChooseSaveController.isThirdSave()) {
            ChooseSaveController.setThirdSave(false);
            mapMoverRight(jsonManager3.readArray(JsonManager.integerReference).get(0));
            mapMoverDown(jsonManager3.readArray(JsonManager.integerReference).get(1));
            gameData.setPoint(jsonManager3.readArray(JsonManager.integerReference).get(2));
            gameData.setCoin(jsonManager3.readArray(JsonManager.integerReference).get(3));
            gameData.setTime(jsonManager3.readArray(JsonManager.integerReference).get(4));
            gameData.setHp(jsonManager3.readArray(JsonManager.integerReference).get(5));
            mario.setLayoutX(jsonManager3.readArray(JsonManager.integerReference).get(6));
            mario.setLayoutY(jsonManager3.readArray(JsonManager.integerReference).get(7));
            mapMoveCounter = jsonManager3.readArray(JsonManager.integerReference).get(0);
            mapMoveDownCounter = jsonManager3.readArray(JsonManager.integerReference).get(1);
            mario.setMarioState(jsonManager3.readArray(JsonManager.integerReference).get(9));
        }
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public Mario getMario() {
        return mario;
    }

    public Bowser bowserFounder() {
        for (Enemy enemy : enemies) {
            if (enemy instanceof Bowser bowser) return bowser;
        }
        return null;
    }

    public BowserAttack getBowserAttack() {
        return bowserAttack;
    }

    public void setGravity(double gravity) {
        this.gravity = gravity;
    }

    public UsingAttacks getUsingAttacks() {
        return usingAttacks;
    }

    public AnimationTimer getTimer() {
        return timer;
    }

    public List<Item> getItems() {
        return items;
    }

    public Pane getPane() {
        return pane;
    }

    public void setGamePause(boolean gamePause) {
        isGamePause = gamePause;
    }

    public Timeline getBossMover() {
        return bossMover;
    }
    public GameSound getGameSound() {
        return gameSound;
    }
}
