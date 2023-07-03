package com.example.mario.GameHandle;

import com.example.mario.Items.*;
import com.example.mario.SuperMario;
import com.example.mario.blocks.*;
import com.example.mario.controllers.GameLabelController;
import com.example.mario.enemies.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class PauseHandle implements Initializable {
    @FXML
    public ImageView music;
    @FXML Button musicButton;
    @FXML
    public Button resume;
    @FXML
    public Button exit;
    private MotionHandler motionHandler;
    public PauseHandle(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!motionHandler.getGameSound().isMuteListener()){
            music.setImage(new Image("Images/backGrounds/mute.png"));
        }
        try {
            exit.setOnAction(e -> {
                try {
                    exitGame();
                } catch (Exception ignored) {
                }
            });
            resume.setOnAction(e -> gameStart());
            musicButton.setOnAction(e -> muteMusic());
        } catch (Exception ignored) {
        }
    }

    public void gameStart() {
        SuperMario.getLevelStage().setScene(motionHandler.getPane().getScene());
        motionHandler.setGamePause(false);
        motionHandler.getBossMover().play();
        GameLabelController.timeline.play();
        motionHandler.getTimer().start();
        //enemy stop:
        for (Enemy enemy : motionHandler.getEnemies()) {
            if (enemy instanceof Flower flower) flower.getTimeline().play();
            if (enemy instanceof Koopa koopa) {
                koopa.getKoopaTimeline().play();
                koopa.getKoopaAnimation().play();
                koopa.getKoopaAnimationStopper().play();
            }
            if (enemy instanceof Goompa goompa) goompa.getTimeline().play();
            if (enemy instanceof Spiny spiny) spiny.getTimeline2().play();
        }
        //block stop:
        for (Block block : motionHandler.getBlocks()) {
            if (block instanceof MysteryBlock mysteryBlock) mysteryBlock.getTimeline().play();
        }
        //item stop:
        for (Item item : motionHandler.getItems()) {
            if (item instanceof Coin coin) {
                coin.getTimeline().play();
                coin.getFallTimeline().play();
            }
            if (item instanceof Mushroom mushroom) {
                mushroom.getTimeline().play();
                mushroom.getDelayTimeLine().play();
            }
            if (item instanceof Star star) {
                star.getDelayTimeline().play();
                star.getTimeline().play();
            }
            if (item instanceof MagicFlower magicFlower) magicFlower.getTimeline().play();
        }
    }

    public void muteMusic() {
        if (urlEditor(music.getImage().getUrl()).equals("Images/backGrounds/unmute.png")) {
            music.setImage(new Image("Images/backGrounds/mute.png"));
            motionHandler.getGameSound().setMuteListener(false);
        } else {
            music.setImage(new Image("Images/backGrounds/unmute.png"));
            motionHandler.getGameSound().setMuteListener(true);
        }
    }
    public void exitGame() throws Exception {
        motionHandler.saveGame();
        motionHandler.loadMainMenu();
        motionHandler.getGameSound().setMuteListener(false);
        GameSound.setInstance(null);
    }
    public String urlEditor(String str){
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='I'&&str.charAt(i+1)=='m'){
                str=str.substring(i);
                return str;
            }
        }
        return "" ;
    }
}
