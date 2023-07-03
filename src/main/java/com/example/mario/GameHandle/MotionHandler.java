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
import com.fasterxml.jackson.databind.util.ISO8601Utils;
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
    private Scene stopScene;
    private double gravity = 0.5;
    private double velocity = 0;
    private boolean isLeft = false;
    private boolean isRight = false;
    private final boolean jumpStop = false;
    private boolean isGamePause = false;
    private final Mario mario = new Mario(30, 30, 30, -60);
    private final List<Enemy> enemies;
    private final List<Block> blocks;
    private final List<BackGround> backGrounds;
    private final List<Item> items;
    private final List<Gun> shots = new ArrayList<>();
    private final int level;
    private final GameLabelController gameLabelController = GameLabelController.getInstance();
    private PauseHandle pauseHandle =new PauseHandle(this);
    private GameData gameData = GameData.getInstance();
    private final UserData userData = UserData.getInstance();
    private final List<Integer> saveData = new ArrayList<>();
    private final ItemCollision itemCollision;
    private final MarioCollision marioCollision;
    private final MarioAnimation marioAnimation;
    private final EnemyCollision enemyCollision;
    private final ShotCollision shotCollision;
    private BowserMovement bowserMovement;
    private double progressRisk=0;
    private final BowserAttack bowserAttack;
    private UsingAttacks usingAttacks;
    private final MapMover mapMover;
    private  GameSound gameSound = GameSound.getInstance(this);
    private final SetLevel setLevel;
    private Bowser bowser;
    private final FxmlLoader fxmlLoader=new FxmlLoader();
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
                         List<Item> items, Stage stage, Pane pane, int level, int marioState) throws Exception {
        this.stage = stage;
        this.pane = pane;
        this.blocks = blocks;
        this.enemies = enemies;
        this.backGrounds = backGrounds;
        this.items = items;
        this.level = level;
        mario.setMarioState(marioState);
        marioAnimation = new MarioAnimation(this);
        marioCollision = new MarioCollision(mario, this.blocks, new BlockCollision(this.pane, this.items, mario));
        itemCollision = new ItemCollision(this);
        enemyCollision = new EnemyCollision(this);
        shotCollision = new ShotCollision(this.blocks, this.enemies, this.shots, enemyCollision);
        bowserMovement = new BowserMovement(this);
        bowserAttack = new BowserAttack(this);
        usingAttacks = new UsingAttacks(this);
        setLevel = new SetLevel(level);
        mapMover = new MapMover(this);
        bowser = bowserFounder();
        gameLabelController.setPointChange(gameData.getPoint());
        gameLabelController.setHpChange(gameData.getHp());
        gameLabelController.setCoinChange(gameData.getCoin());
        bossMover = new Timeline(bossMoverKeyFrame);
        bossMover.setCycleCount(Animation.INDEFINITE);
        bossMover.play();
        stopScene=loadStopFxml();
        jsonJob();
        pane.getChildren().add(mario);
        stage.getScene().setOnKeyPressed(event -> {
            if (mario.isCanMove() && !isGamePause) {
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
                checkSecretPipe();
                checkSecretPipeBack();
                updateProgressRisk();
                itemMovement();
                enemyMovement();
                mapMover.setMapMoving(false);
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
                            if (!setLevel.levelSet(mario.getMarioState())) {
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
                    mapMover.isMapMustMovingDownCheck();
                    enemyCollision.isEnemyCollision();
                    marioCollision.collision();
                    itemCollision.allCollision();
                    shotCollision.checkCollision();
                    if (!marioCollision.isRightCollusion() && isRight && mario.getLayoutX() > (float) SuperMario.getWidth() / 2 && !mapMover.isAllBlockMoveRight()) {
                        mapMover.mapRightController();
                        mapMover.setMapMoving(true);
                    }
                    a:
                    if (isRight && !marioCollision.isRightCollusion()) {
                        marioAnimation.setMoving(true);
                        marioAnimation.startMoving();
                        if (mario.getLayoutX() + mario.getFitWidth() > SuperMario.getWidth() - 20)
                            break a;
                        if (!mapMover.isMapMoving())
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
                        if (mapMover.isMapMustMovingDown() && !mapMover.isAllBlockMoveDown()) {
                            mapMover.mapDownController();
                        } else {
                            if (marioCollision.isUpCollusion()) velocity = 0;
                            velocity -= gravity;
                            mario.setLayoutY(mario.getLayoutY() - velocity);
                        }
                    } else {
                        mapMover.setMapMustMovingDown(false);
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
            if (win instanceof WinBlock && mario.getBoundsInParent().intersects(win.getBoundsInParent())) return true;
        }
        return false;
    }

    public void doDead() throws Exception {
        mario.setDead(false);
        mario.setMarioState(0);
        if (gameData.getPoint() >= 30) {
            gameData.setPoint(gameData.getPoint() - 30);
            gameLabelController.setPointChange(gameData.getPoint());
        }
        else {
            gameData.setPoint(0);
            gameLabelController.setPointChange(gameData.getPoint());
        }
        int deathCoinLost=(int)(gameData.getCoin()+progressRisk)/4;
        System.out.println(deathCoinLost);
        if (gameData.getCoin() >= deathCoinLost) {
            gameData.setCoin(gameData.getCoin() - deathCoinLost);
            gameLabelController.setCoinChange(gameData.getCoin());
        }
        else {
            gameData.setPoint(0);
            gameLabelController.setCoinChange(gameData.getCoin());
        }
        mapMover.setMapMustMovingDown(false);
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
        for (Block block : blocks) block.setLayoutX(block.getLayoutX() + mapMover.getMapMoveCounter() * 3);
        for (Block block : blocks) block.setLayoutY(block.getLayoutY() + mapMover.getMapMoveDownCounter() * 4);
        for (Enemy enemy : enemies) enemy.setLayoutX(enemy.getLayoutX() + mapMover.getMapMoveCounter() * 3);
        for (Enemy enemy : enemies) enemy.setLayoutY(enemy.getLayoutY() + mapMover.getMapMoveDownCounter() * 4);
        for (BackGround backGround : backGrounds)
            backGround.setLayoutX(backGround.getLayoutX() + mapMover.getMapMoveCounter() * 3);
        for (BackGround backGround : backGrounds)
            backGround.setLayoutY(backGround.getLayoutY() + mapMover.getMapMoveDownCounter() * 4);
        for (Item item : items) item.setLayoutX(item.getLayoutX() + mapMover.getMapMoveCounter() * 3);
        for (Item item : items) item.setLayoutY(item.getLayoutY() + mapMover.getMapMoveDownCounter() * 4);
        mapMover.setMapMoveDownCounter(0);
        mapMover.setMapMoveCounter(0);
    }

    public void checkTime() {
        if (gameLabelController.getTime().getText().equals("0"))
            mario.setDead(true);
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

    public void stopFxml() {
        stage.setScene(stopScene);
    }
    public Scene loadStopFxml() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/PauseGame.fxml").toURI().toURL());
        loader.setController(pauseHandle);
        Parent content = loader.load();
        Scene scene = new Scene(content);
        return scene;
    }
    public void loadMainMenu() throws Exception {
        gameSound.setMuteListener(false);
        GameSound.setInstance(null);
        stage.setScene(fxmlLoader.loadFxml("mainMenu"));
        stage.show();
    }

    public void itemMovement() {
        for (Item item : items) item.itemCollision(blocks);
    }

    public void enemyMovement() {
        for (Enemy enemy : enemies) enemy.enemyCollision(blocks);
    }

    public void saveGame() throws Exception {
        saveData.add(mapMover.getMapMoveCounter());
        saveData.add(mapMover.getMapMoveDownCounter());
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
            jsonHelper(jsonManager1);
        } else if (ChooseSaveController.isSecondSave()) {
            ChooseSaveController.setSecondSave(false);
            jsonHelper(jsonManager2);
        } else if (ChooseSaveController.isThirdSave()) {
            ChooseSaveController.setThirdSave(false);
            jsonHelper(jsonManager3);
        }
    }
    public void jsonHelper(JsonManager jsonManager)throws Exception{
        mapMover.mapMoverRight(jsonManager.readArray(JsonManager.integerReference).get(0));
        mapMover.mapMoverDown(jsonManager.readArray(JsonManager.integerReference).get(1));
        gameData.setPoint(jsonManager.readArray(JsonManager.integerReference).get(2));
        gameData.setCoin(jsonManager.readArray(JsonManager.integerReference).get(3));
        gameData.setTime(jsonManager.readArray(JsonManager.integerReference).get(4));
        gameData.setHp(jsonManager.readArray(JsonManager.integerReference).get(5));
        mario.setLayoutX(jsonManager.readArray(JsonManager.integerReference).get(6));
        mario.setLayoutY(jsonManager.readArray(JsonManager.integerReference).get(7));
        mapMover.setMapMoveCounter(jsonManager.readArray(JsonManager.integerReference).get(0));
        mapMover.setMapMoveDownCounter(jsonManager.readArray(JsonManager.integerReference).get(1));
        mario.setMarioState(jsonManager.readArray(JsonManager.integerReference).get(9));
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

    public List<BackGround> getBackGrounds() {
        return backGrounds;
    }

    public MarioAnimation getMarioAnimation() {
        return marioAnimation;
    }
    public void checkSecretPipe(){
        if(mario.isSit()&&mario.isOnSecretPipe()){
            mapMover.mapMoverDown(100);
            mapMover.setMapMoveDownCounter(100);
        }
    }
    public void checkSecretPipeBack(){
        if(mario.isSit()&&mario.isOnSecretPipeBack()){
            mario.setLayoutY(60);
            mario.setLayoutX(90);
            mapMover.mapMoverDown(-(mapMover.getMapMoveDownCounter()));
            mapMover.setMapMoveDownCounter(0);
            System.out.println(blocks.get(0).getLayoutY());
        }
    }
    public void updateProgressRisk(){
        progressRisk=(mario.getLayoutX()/winBlockLayout());
    }
    public int winBlockLayout(){
        for(Block block:blocks){
            if(block instanceof WinBlock) return (int) block.getLayoutX();
        }
        return 0;
    }
}
