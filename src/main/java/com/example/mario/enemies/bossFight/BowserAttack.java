package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Mario.Mario;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.IOException;

public class BowserAttack {
    private MotionHandler motionHandler;
    private Mario mario;
    private Bowser bowser;
    private Timeline grabStartTimer;
    private Timeline grabAttackTimer;
    private int grabLeftCounter = 0;
    private int grabRightCounter = 0;

    public BowserAttack(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
        this.mario = motionHandler.getMario();
        this.bowser = motionHandler.bowserFounder();
    }

    public void resetInstance() {
        motionHandler.getUsingAttacks().setUsingAnotherAttack(false);
        bowser.setImage(new Image("Images/enemies/bowser/bowserLeft.png"));
        mario.setInvincible(false);
        bowser.setInvincible(false);
    }

    //grabAttack:
    KeyFrame grabStartKeyFrame = new KeyFrame(Duration.millis(500), event -> {
        if (Math.abs(mario.getLayoutX() + mario.getFitWidth() / 2 - (bowser.getLayoutX() + bowser.getFitWidth() / 2)) < 110 && bowser.getLayoutY() - mario.getLayoutY() < 60) {
            bowser.setInvincible(true);
            mario.setInvincible(true);
            mario.setLayoutX(bowser.getLayoutX() + (bowser.getFitWidth() - mario.getFitWidth()) / 2);
            mario.setLayoutY(bowser.getLayoutY() + 50);
            mario.setCanMove(false);
            motionHandler.setGravity(0);
            grabRightCounter = 0;
            grabLeftCounter = 0;
            grabAttackTimer.play();
            grabStartTimer.stop();
        }
        motionHandler.getUsingAttacks().setUsingAnotherAttack(false);
        grabStartTimer.stop();
    });
    KeyFrame grabAttackKeyFrame = new KeyFrame(Duration.seconds(3), event -> {
        if (!(grabLeftCounter > 9 && grabRightCounter > 9)) {
            try {
                motionHandler.doDead();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            mario.setLayoutX(bowser.getLayoutX() - bowser.getFitWidth());
            mario.setLayoutY(bowser.getLayoutY() + bowser.getFitHeight() - mario.getFitHeight());
        }
        resetInstance();
        mario.setCanMove(true);
        motionHandler.setGravity(0.5);
        grabAttackTimer.stop();
    });

    public void grabAttack() {
        if (bowser.isBowerGoingLeft()) bowser.setImage(new Image("Images/enemies/bowser/grabAttackLeft.png"));
        else bowser.setImage(new Image("Images/enemies/bowser/grabAttack.png"));
        grabStartTimer = new Timeline(grabStartKeyFrame);
        grabStartTimer.setCycleCount(Animation.INDEFINITE);
        grabAttackTimer = new Timeline(grabAttackKeyFrame);
        grabAttackTimer.setCycleCount(Animation.INDEFINITE);
        grabStartTimer.play();
    }

    public void setGrabLeftCounter(int grabLeftCounter) {
        this.grabLeftCounter = grabLeftCounter;
    }

    public void setGrabRightCounter(int grabRightCounter) {
        this.grabRightCounter = grabRightCounter;
    }

    public int getGrabLeftCounter() {
        return grabLeftCounter;
    }

    public int getGrabRightCounter() {
        return grabRightCounter;
    }

    //jumpAttack
    public void jumpAttack() {
        bowser.setFallVelocity(12);
    }
}
