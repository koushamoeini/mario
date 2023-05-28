package com.example.mario;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MotionHandler {
    Stage stage;
    private final int gravity = 1;
    private int velocity = 0;
    private boolean isLeft = false;
    private boolean isRight = false;
    private boolean isMapMoving = false;
    private int mapMoveCounter = 0;
    private int mapMoveDownCounter = 0;
    private boolean isMapMustMovingDown = false;
    private int counter = 0;
    private final Mario mario = new Mario(30, 60, 30, -60);
    private final ArrayList<Enemy> enemies;
    private final ArrayList<Block> blocks;
    private final ArrayList<BackGround> backGrounds;
    private final ArrayList<Coin> coins;
    private boolean rightCollusion;
    private boolean leftCollusion;
    private boolean upCollusion;
    private boolean downCollusion;
    private int section;
    private GameLabelController gameLabelController = GameLabelController.getInstance();
    private GameData gameData = GameData.getInstance();
    private UserData userData = UserData.getInstance();
    private List<Integer> saveData = new ArrayList<>();
    AnimationTimer timer;
    Timeline andBeginTime = new Timeline();
    private VoicePlayer andBegin = new VoicePlayer("C:\\Users\\koush\\IdeaProjects\\Mario\\src\\main\\resources\\Media\\and begin.mp3");
    private VoicePlayer beppi = new VoicePlayer("C:\\Users\\koush\\IdeaProjects\\Mario\\src\\main\\resources\\Media\\beppi.mp3");
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(4), event -> {
        andBegin.stop();
        beppi.play();
    });
    JsonManager jsonManager = new JsonManager("./src/main/resources/GamaData/users.json");
    JsonManager jsonManager1 = new JsonManager("./src/main/resources/GamaData/" + userData.getCurrentUser().getUserName() + "/game1.json");
    JsonManager jsonManager2 = new JsonManager("./src/main/resources/GamaData/" + userData.getCurrentUser().getUserName() + "/game2.json");
    JsonManager jsonManager3 = new JsonManager("./src/main/resources/GamaData/" + userData.getCurrentUser().getUserName() + "/game3.json");

    MotionHandler(ArrayList<Block> objects, ArrayList<Enemy> enemy, ArrayList<BackGround> images, ArrayList<Coin> coin, Stage stage0, Pane pane) throws IOException {
        stage = stage0;
        gameLabelController.setPointChange(gameData.getPoint());
        gameLabelController.setHpChange(gameData.getHp());
        gameLabelController.setCoinChange(gameData.getCoin());
        andBeginTime.getKeyFrames().addAll(keyFrame);
        andBeginTime.setCycleCount(Animation.INDEFINITE);
        andBeginTime.play();
        andBegin.play();
        blocks = objects;
        enemies = enemy;
        backGrounds = images;
        coins = coin;
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
            mapMoveDownCounter = jsonManager1.readArray(JsonManager.integerReference).get(2);
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
            mapMoveDownCounter = jsonManager1.readArray(JsonManager.integerReference).get(3);
        }
        pane.getChildren().add(mario);
        stage.getScene().setOnKeyPressed(event -> {
            switch (event.getCode()) {
                case RIGHT -> isRight = true;
                case LEFT -> isLeft = true;
                case UP -> {
                    if (downCollusion) {
                        mario.setJumping(true);
                        mario.startMoving();
                        velocity = 20;
                        mario.setLayoutY(mario.getLayoutY() - 5);
                    }
                }
                case ESCAPE -> {
                    GameLabelController.timeline.stop();
                    andBegin.stop();
                    andBeginTime.stop();
                    beppi.stop();
                    timer.stop();
                    gameData = GameData.resetInstance();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("MainMenu.fxml"));
                    Parent content;
                    try {
                        content = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(content);
                    stage.setScene(scene);
                    stage.setHeight(SuperMario.getHeight());
                    stage.setWidth(1020);
                    stage.getIcons().add(SuperMario.getIcon());
                    stage.setResizable(false);
                    stage.setTitle(SuperMario.getStageTitle());
                    stage.setX(SuperMario.getStageX());
                    stage.setY(SuperMario.getStageY());
                    stage.show();
                }
                case S -> {
                    saveData.add(mapMoveCounter);
                    saveData.add(mapMoveDownCounter);
                    saveData.add(gameData.getPoint());
                    saveData.add(gameData.getCoin());
                    saveData.add(gameData.getTime());
                    saveData.add(gameData.getHp());
                    saveData.add((int) mario.getLayoutX());
                    saveData.add((int) mario.getLayoutY());
                    saveData.add(section);
                    GameLabelController.timeline.stop();
                    andBegin.stop();
                    andBeginTime.stop();
                    beppi.stop();
                    timer.stop();
                    gameData = GameData.resetInstance();
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass().getResource("MainMenu.fxml"));
                    Parent content;
                    try {
                        content = loader.load();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    Scene scene = new Scene(content);
                    stage.setScene(scene);
                    stage.setHeight(SuperMario.getHeight());
                    stage.setWidth(1020);
                    stage.getIcons().add(SuperMario.getIcon());
                    stage.setResizable(false);
                    stage.setTitle(SuperMario.getStageTitle());
                    stage.setX(SuperMario.getStageX());
                    stage.setY(SuperMario.getStageY());
                    stage.show();
                    try {
                        jsonManager3.writeArray(jsonManager2.readArray(JsonManager.integerReference));
                        jsonManager2.writeArray(jsonManager1.readArray(JsonManager.integerReference));
                        jsonManager1.writeArray(saveData);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                case R -> mario.setDead(true);
            }
        });
        stage.getScene().setOnKeyReleased(event -> {
            switch (event.getCode()) {
                case RIGHT -> {
                    isRight = false;
                    if (downCollusion) mario.stopMoving();
                    mario.setMoving(false);
                }

                case LEFT -> {
                    isLeft = false;
                    if (downCollusion) mario.stopMoving();
                    mario.setMarioMovingLeft(false);
                    mario.setMoving(false);
                }
            }
        });
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                isMapMoving = false;
                rightCollusion = false;
                leftCollusion = false;
                upCollusion = false;
                downCollusion = false;
                checkTime();
                if (mario.isDead()) {
                    mario.startMoving();
                }
                if (mario.isDyingFinished()) {
                    try {
                        doDead();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                if (!mario.isDead()) {
                    if (isWin()) {
                        andBeginTime.stop();
                        beppi.stop();
                        timer.stop();
                        gameData.setPoint(gameData.getPoint() + gameData.getTime());
                        gameData.setTime(100);
                        GameLabelController.timeline.stop();
                        gameLabelController.setPointChange(gameData.getPoint());
                        if (section == 1) {
                            try {
                                new Level1_2();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        } else {
                            gameData.setPoint(gameData.getPoint() + gameData.getHp() * 20);
                            userData.getCurrentUser().checkPoint(gameData.getPoint());
                            userData.getCurrentUser().setCoins(userData.getCurrentUser().getCoins() + gameData.getCoin());
                            FXMLLoader loader = new FXMLLoader();
                            loader.setLocation(getClass().getResource("MainMenu.fxml"));
                            Parent content;
                            try {
                                content = loader.load();
                                jsonManager.writeArray(userData.getUsers());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Scene scene = new Scene(content);
                            stage.setScene(scene);
                            stage.setHeight(SuperMario.getHeight());
                            stage.setWidth(1020);
                            stage.getIcons().add(SuperMario.getIcon());
                            stage.setResizable(false);
                            stage.setTitle(SuperMario.getStageTitle());
                            stage.setX(SuperMario.getStageX());
                            stage.setY(SuperMario.getStageY());
                            stage.show();
                        }
                    }
                    isMapMustMovingDown();
                    isEnemyCollision();
                    isCollision();
                    isCoinCollision();
                    mysteryController();
                    for (Enemy enemy : enemies) {
                        if (enemy instanceof Flower) ((Flower) enemy).cagneyController();
                    }
                    if (!rightCollusion && isRight && mario.getLayoutX() > (float) SuperMario.getWidth() / 2 && !isAllBlockMoveRight()) {
                        mapRightController();
                        isMapMoving = true;
                    }
                    a:
                    if (isRight && !rightCollusion) {
                        mario.setMoving(true);
                        mario.startMoving();
                        if (mario.getLayoutX() + mario.getFitWidth() > SuperMario.getWidth() - 20)
                            break a;
                        if (!isMapMoving)
                            mario.setLayoutX(mario.getLayoutX() + 4);
                    }
                    b:
                    if (isLeft && !leftCollusion) {
                        mario.setMarioMovingLeft(true);
                        mario.setMoving(true);
                        mario.startMoving();
                        if (mario.getLayoutX() < 0)
                            break b;
                        mario.setLayoutX(mario.getLayoutX() - 4);
                    }
                    if (!downCollusion && !mario.isDead()) {
                        if (isMapMustMovingDown && !isAllBlockMoveDown()) {
                            mapDownController();
                        } else {
                            if (upCollusion) velocity = 0;
                            velocity -= gravity;
                            mario.setLayoutY(mario.getLayoutY() - velocity);
                        }
                    } else {
                        isMapMustMovingDown = false;
                        velocity = 0;
                        if (mario.isJumping()) {
                            mario.setJumping(false);
                            mario.stopMoving();
                        }
                    }
                }
            }
        };

        timer.start();
    }

    public void isCollision() {
        for (Block block : blocks) {
            if (!block.getType().equals(Block.Type.WinBlock)) {
                if (mario.getLayoutY() + mario.getFitHeight() >= block.getLayoutY() && mario.getLayoutY() + mario.getFitHeight() <= block.getLayoutY() + block.getFitHeight()) {
                    for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                        if (j >= block.getLayoutX() && j <= block.getLayoutX() + block.getFitWidth()) {
                            if (block.getType().equals(Block.Type.killBlock)) {
                                mario.setDead(true);
                            } else {
                                mario.setLayoutY(block.getLayoutY() - mario.getFitHeight());
                                downCollusion = true;
                                break;
                            }
                        }
                    }
                }
                if (mario.getLayoutY() > block.getLayoutY() && mario.getLayoutY() < block.getLayoutY() + block.getFitHeight()) {
                    for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                        if (j > block.getLayoutX() && j < block.getLayoutX() + block.getFitWidth()) {
                            if (block.getType().equals(Block.Type.killBlock)) {
                                mario.setDead(true);
                            } else {
                                mario.setLayoutY(block.getLayoutY() + block.getFitHeight());
                                upCollusion = true;
                                break;
                            }
                        }
                    }
                }
                if (mario.getLayoutX() + mario.getFitWidth() > block.getLayoutX() - 5 && mario.getLayoutX() + mario.getFitWidth() < block.getLayoutX() + block.getFitWidth() + 5) {
                    for (int j = (int) mario.getLayoutY(); j <= mario.getLayoutY() + mario.getFitHeight(); j++) {
                        if (j > block.getLayoutY() && j < block.getLayoutY() + block.getFitHeight()) {
                            if (block.getType().equals(Block.Type.killBlock)) {
                                mario.setDead(true);
                            } else {
                                rightCollusion = true;
                                break;
                            }
                        }
                    }
                }
                if (mario.getLayoutX() > block.getLayoutX() - 5 && mario.getLayoutX() < block.getLayoutX() + block.getFitWidth() + 5) {
                    for (int j = (int) mario.getLayoutY(); j <= mario.getLayoutY() + mario.getFitHeight(); j++) {
                        if (j > block.getLayoutY() && j < block.getLayoutY() + block.getFitHeight()) {
                            if (block.getType().equals(Block.Type.killBlock)) {
                                mario.setDead(true);
                            } else {
                                leftCollusion = true;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void isEnemyCollision() {
        for (Enemy block : enemies) {
            for (int i = (int) mario.getLayoutY(); i <= mario.getLayoutY() + mario.getFitHeight(); i++) {
                if (i >= block.getLayoutY() && i <= block.getLayoutY() + block.getFitHeight()) {
                    for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                        if (j >= block.getLayoutX() && j <= block.getLayoutX() + block.getFitWidth()) {
                            mario.setDead(true);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void isCoinCollision() {
        ArrayList<Coin> removeCoin = new ArrayList<>();
        a:
        for (Coin coin : coins) {
            for (int i = (int) mario.getLayoutY(); i <= mario.getLayoutY() + mario.getFitHeight(); i++) {
                if (i >= coin.getLayoutY() && i <= coin.getLayoutY() + coin.getFitHeight()) {
                    for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                        if (j >= coin.getLayoutX() && j <= coin.getLayoutX() + coin.getFitWidth()) {
                            gameData.setCoin(gameData.getCoin() + 1);
                            gameLabelController.setCoinChange(gameData.getCoin());
                            gameData.setPoint(gameData.getPoint() + 10);
                            gameLabelController.setPointChange(gameData.getPoint());

                            removeCoin.add(coin);
                            coin.setVisible(false);
                            continue a;
                        }
                    }
                }
            }
        }
        coins.removeAll(removeCoin);
    }

    public boolean isWin() {
        for (Block win : blocks) {
            for (int i = (int) mario.getLayoutY(); i <= mario.getLayoutY() + mario.getFitHeight(); i++) {
                if (i >= win.getLayoutY() && i <= win.getLayoutY() + win.getFitHeight()) {
                    for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                        if (j >= win.getLayoutX() && j <= win.getLayoutX() + win.getFitWidth() && win.getType().equals(Block.Type.WinBlock)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public void doDead() throws IOException {
        mario.setDead(false);
        isMapMustMovingDown = false;
        mario.setDyingFinished(false);
        mario.setLayoutX(30);
        mario.setLayoutY(-60);
        gameData.setTime(100);
        gameData.setHp(gameData.getHp() - 1);
        if (gameData.getHp() < 1) {
            GameLabelController.timeline.stop();
            andBeginTime.stop();
            andBegin.stop();
            beppi.stop();
            timer.stop();
            GameLabelController.timeline.stop();
            userData.getCurrentUser().checkPoint(gameData.getPoint());
            jsonManager.writeArray(userData.getUsers());
            gameData = GameData.resetInstance();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("MainMenu.fxml"));
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
            SuperMario.menuSong.stop();
        }
        for (Block block : blocks) {
            block.setLayoutX(block.getLayoutX() + mapMoveCounter * 4);
        }
        for (Enemy enemy : enemies) {
            enemy.setLayoutX(enemy.getLayoutX() + mapMoveCounter * 4);
        }
        for (BackGround backGround : backGrounds) {
            backGround.setLayoutX(backGround.getLayoutX() + mapMoveCounter * 4);
        }
        for (Coin coin : coins) {
            coin.setLayoutX(coin.getLayoutX() + mapMoveCounter * 4);
        }
        for (Block block : blocks) {
            block.setLayoutY(block.getLayoutY() + mapMoveDownCounter * 5);
        }
        for (Enemy enemy : enemies) {
            enemy.setLayoutY(enemy.getLayoutY() + mapMoveDownCounter * 5);
        }
        for (BackGround backGround : backGrounds) {
            backGround.setLayoutY(backGround.getLayoutY() + mapMoveDownCounter * 5);
        }
        for (Coin coin : coins) {
            coin.setLayoutY(coin.getLayoutY() + mapMoveDownCounter * 5);
        }
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
            if (block.getLayoutY() > SuperMario.getHeight() && !block.getType().equals(Block.Type.WinBlock))
                return false;
        }
        return true;
    }

    public void mapRightController() {
        mapMoveCounter++;
        for (Block block : blocks) {
            block.setLayoutX(block.getLayoutX() - 4);
        }
        for (Enemy enemy : enemies) {
            enemy.setLayoutX(enemy.getLayoutX() - 4);
        }
        for (BackGround backGround : backGrounds) {
            backGround.setLayoutX(backGround.getLayoutX() - 4);
        }
        for (Coin coin : coins) {
            coin.setLayoutX(coin.getLayoutX() - 4);
        }
    }

    public void mapDownController() {
        mapMoveDownCounter++;
        mario.falling();
        if (mario.getLayoutY() >= 210) mario.setLayoutY(mario.getLayoutY() - 10);
        for (Block block : blocks) {
            block.setLayoutY(block.getLayoutY() - 5);
        }
        for (BackGround backGround : backGrounds) {
            backGround.setLayoutY(backGround.getLayoutY() - 5);
        }
        for (Enemy enemy : enemies) {
            enemy.setLayoutY(enemy.getLayoutY() - 5);
        }
        for (Coin coin : coins) {
            coin.setLayoutY(coin.getLayoutY() - 5);
        }
    }

    public void isMapMustMovingDown() {
        if (mario.getLayoutY() > SuperMario.getHeight() + 30) {
            isMapMustMovingDown = true;
        }
    }

    public void mysteryController() {
        counter++;
        for (Block block : blocks) {
            if (block.getType().equals(Block.Type.Mystery)) {
                if (counter % 50 < 25) {
                    block.setImage(new Image("Images/prize_active.png"));
                } else {
                    block.setImage(new Image("Images/prize_normal.png"));
                }
            }
        }
    }

    public void checkTime() {
        if (gameLabelController.getTime().getText().equals("0"))
            mario.setDead(true);
    }

    public void setSection(int section) {
        this.section = section;
    }

    public void mapMoverRight(int num) {
        for (Block block : blocks) {
            block.setLayoutX(block.getLayoutX() - num * 4);
        }
        for (Enemy enemy : enemies) {
            enemy.setLayoutX(enemy.getLayoutX() - num * 4);
        }
        for (BackGround backGround : backGrounds) {
            backGround.setLayoutX(backGround.getLayoutX() - num * 4);
        }
        for (Coin coin : coins) {
            coin.setLayoutX(coin.getLayoutX() - num * 4);
        }
    }

    public void mapMoverDown(int num) {
        for (Block block : blocks) {
            block.setLayoutY(block.getLayoutY() - num * 5);
        }
        for (Enemy enemy : enemies) {
            enemy.setLayoutY(enemy.getLayoutY() - num * 5);
        }
        for (BackGround backGround : backGrounds) {
            backGround.setLayoutY(backGround.getLayoutY() - num * 5);
        }
        for (Coin coin : coins) {
            coin.setLayoutY(coin.getLayoutY() - num * 5);
        }
    }
}
