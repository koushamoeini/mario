package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Items.*;
import com.example.mario.Mario.Mario;
import com.example.mario.blocks.Block;
import com.example.mario.blocks.MysteryBlock;
import com.example.mario.controllers.GameLabelController;
import com.example.mario.enemies.*;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.ColorAdjust;
import javafx.util.Duration;

public class BossFightCutScene {
    private MotionHandler motionHandler;
    private Mario mario;
    private Bowser bowser;
    private Timeline startCutscene;
    private Timeline cutScene;
    private Timeline setXTimer;
    private int cutSceneCounter = 0;
    private int direction = 1;

    public BossFightCutScene(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
        this.mario = motionHandler.getMario();
        this.bowser = motionHandler.bowserFounder();
        startCutscene = new Timeline(startCutSceneKeyFrame);
        startCutscene.setCycleCount(Animation.INDEFINITE);
        startCutscene.play();
        cutScene = new Timeline(cutSceneKeyFrame);
        cutScene.setCycleCount(Animation.INDEFINITE);
        setXTimer = new Timeline(setXKeyFrame);
        setXTimer.setCycleCount(Animation.INDEFINITE);
    }

    KeyFrame startCutSceneKeyFrame = new KeyFrame(Duration.millis(1), event -> {
        if (bowser.getPhase() == 2) {
            motionHandler.setGamePause(true);
            gamePause();
            setXTimer.play();
            startCutscene.stop();
        }
    });
    KeyFrame setXKeyFrame = new KeyFrame(Duration.millis(1), event -> {
        if (bowser.getLayoutX() < 720) bowser.setLayoutX(bowser.getLayoutX() + 1);
        else if (bowser.getLayoutX() > 720) bowser.setLayoutX(bowser.getLayoutX() - 1);
        else {
            cutScene.play();
            setXTimer.stop();
        }
    });
    KeyFrame cutSceneKeyFrame = new KeyFrame(Duration.millis(5), event -> {
        cutSceneCounter++;
        if (cutSceneCounter==70) {
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setHue(2);
            colorAdjust.setSaturation(2);
            colorAdjust.setContrast(1.0);
            bowser.setEffect(colorAdjust);
            direction *= -1;
        }
        if(cutSceneCounter>=140){
            motionHandler.getBlocks().removeAll(bowser.getBowserBlocks());
            for(Block block:bowser.getBowserBlocks()) block.setVisible(false);
            gameStart();
            cutScene.stop();
        }
        bowser.setLayoutY(bowser.getLayoutY() - direction * 3);

    });

    public void gamePause() {
        GameLabelController.timeline.stop();
        motionHandler.getTimer().stop();
        //enemy stop:
        for (Enemy enemy : motionHandler.getEnemies()) {
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
        for (Block block : motionHandler.getBlocks()) {
            if (block instanceof MysteryBlock mysteryBlock) mysteryBlock.getTimeline().stop();
        }
        //item stop:
        for (Item item : motionHandler.getItems()) {
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

    public void gameStart() {
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
}
