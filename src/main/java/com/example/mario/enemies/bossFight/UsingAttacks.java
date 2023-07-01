package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Mario.Mario;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class UsingAttacks {
    private final MotionHandler motionHandler;
    private Mario mario;
    private Bowser bowser;
    private boolean isGrabCoolDown = false;
    private boolean isJumpCoolDown = false;
    private boolean isFireBallCoolDown = false;
    private boolean isNukeCoolDown = false;
    private boolean isUsingAnotherAttack = false;
    private Timeline grabCoolDownTimer;
    private Timeline jumpCoolDownTimer;
    private Timeline fireBallCoolDown;
    private Timeline nukeCoolDown;
    private Timeline activateBoss;
    private final BowserAttack bowserAttack;

    public UsingAttacks(MotionHandler motionHandler) {
        new BossFightCutScene(motionHandler);
        this.motionHandler = motionHandler;
        this.mario = motionHandler.getMario();
        this.bowser = motionHandler.bowserFounder();
        this.bowserAttack = motionHandler.getBowserAttack();
        grabCoolDownTimer = new Timeline(grabCoolDownKeyFrame);
        grabCoolDownTimer.setCycleCount(Animation.INDEFINITE);
        fireBallCoolDown = new Timeline(fireBallCoolDownKeyFrame);
        fireBallCoolDown.setCycleCount(Animation.INDEFINITE);
        jumpCoolDownTimer = new Timeline(jumpCoolDownKeyFrame);
        jumpCoolDownTimer.setCycleCount(Animation.INDEFINITE);
        nukeCoolDown = new Timeline(nukeCoolDownKeyFrame);
        nukeCoolDown.setCycleCount(Animation.INDEFINITE);
        activateBoss = new Timeline(activeBossKeyFrame);
        activateBoss.setCycleCount(Animation.INDEFINITE);
        activateBoss.play();
    }

    KeyFrame activeBossKeyFrame = new KeyFrame(Duration.millis(1), event -> {
        try {
            if (Math.abs(mario.getLayoutX() - bowser.getLayoutX()) < 16 * 30) bowser.setBowserActive(true);
            else bowser.setBowserActive(false);
        } catch (Exception ignored) {
        }
    });

    public void useAttack() {
        try {
            if (!bowser.isDead() && bowser.isBowserActive()) {
                useGrab();
                useFireBall();
                useJump();
                if (bowser.getPhase() == 2) useNuke();
            }
        } catch (Exception ignored) {
        }
    }

    public void useGrab() {
        if ((Math.abs(mario.getLayoutX() + mario.getFitWidth() / 2 - (bowser.getLayoutX() + bowser.getFitWidth() / 2)) < 110 && mario.getLayoutY() - bowser.getLayoutY() > 0
                && mario.getLayoutY() - bowser.getLayoutY() < 120 && !isUsingAnotherAttack && !isGrabCoolDown)) {
            isUsingAnotherAttack = true;
            isGrabCoolDown = true;
            bowserAttack.grabAttack();
            grabCoolDownTimer.play();
        }
    }

    KeyFrame grabCoolDownKeyFrame = new KeyFrame(Duration.seconds(8), event -> {
        isGrabCoolDown = false;
        grabCoolDownTimer.stop();
    });

    public void useJump() {
        if (!isJumpCoolDown && !isUsingAnotherAttack) {
            isUsingAnotherAttack = true;
            isJumpCoolDown = true;
            jumpCoolDownTimer.play();
            bowserAttack.jumpAttack();
        }
    }

    KeyFrame jumpCoolDownKeyFrame = new KeyFrame(Duration.seconds(6), event -> {
        isJumpCoolDown = false;
        jumpCoolDownTimer.stop();
    });

    public void useFireBall() {
        if (Math.abs(mario.getLayoutX() - bowser.getLayoutX()) >= 7 * 30 && !isFireBallCoolDown && !isUsingAnotherAttack) {
            isFireBallCoolDown = true;
            bowserAttack.fireBallAttack();
            isUsingAnotherAttack = true;
            fireBallCoolDown.play();
        }
    }

    KeyFrame fireBallCoolDownKeyFrame = new KeyFrame(Duration.seconds(7), event -> {
        isFireBallCoolDown = false;
        fireBallCoolDown.stop();
    });

    public void useNuke() {
        if(!isNukeCoolDown&& !isUsingAnotherAttack) {
            isNukeCoolDown = true;
            bowserAttack.nukeAttack();
            isUsingAnotherAttack = true;
            nukeCoolDown.play();
        }
    }

    KeyFrame nukeCoolDownKeyFrame = new KeyFrame(Duration.seconds(5), event -> {
        isNukeCoolDown = false;
        nukeCoolDown.stop();
    });

    public boolean isUsingAnotherAttack() {
        return isUsingAnotherAttack;
    }

    public void setUsingAnotherAttack(boolean usingAnotherAttack) {
        isUsingAnotherAttack = usingAnotherAttack;
    }
}
