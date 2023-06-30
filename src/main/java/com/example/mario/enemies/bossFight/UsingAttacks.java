package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Mario.Mario;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class UsingAttacks {
    private final MotionHandler motionHandler;
    private final Mario mario;
    private final Bowser bowser;
    private boolean isGrabCoolDown = false;
    private boolean isJumpCoolDown = false;
    private boolean isUsingAnotherAttack = false;
    private Timeline grabCoolDownTimer;
    private Timeline jumpCoolDownTimer;
    private final BowserAttack bowserAttack;

    public UsingAttacks(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
        this.mario = motionHandler.getMario();
        this.bowser = motionHandler.bowserFounder();
        this.bowserAttack = motionHandler.getBowserAttack();
        grabCoolDownTimer = new Timeline(grabCoolDownKeyFrame);
        grabCoolDownTimer.setCycleCount(Animation.INDEFINITE);
        jumpCoolDownTimer = new Timeline(jumpCoolDownKeyFrame);
        jumpCoolDownTimer.setCycleCount(Animation.INDEFINITE);
    }

    public void useAttack() {
        try {
            useGrab();
        } catch (Exception ignored) {
        }
    }

    KeyFrame grabCoolDownKeyFrame = new KeyFrame(Duration.seconds(9), event -> {
        isGrabCoolDown = false;
        grabCoolDownTimer.stop();
    });
    KeyFrame jumpCoolDownKeyFrame = new KeyFrame(Duration.seconds(4), event -> {
        isJumpCoolDown = true;
        jumpCoolDownTimer.stop();
    });

    public void useGrab() {
        if ((Math.abs(mario.getLayoutX()+mario.getFitWidth()/2 - (bowser.getLayoutX()+bowser.getFitWidth()/2)) < 110 &&mario.getLayoutY()-bowser.getLayoutY() >0
                && mario.getLayoutY()-bowser.getLayoutY() < 120 && !isUsingAnotherAttack && !isGrabCoolDown)) {
            isUsingAnotherAttack = true;
            isGrabCoolDown = true;
            bowserAttack.grabAttack();
            grabCoolDownTimer.play();
        }
        if(!isUsingAnotherAttack&&!isJumpCoolDown){
            bowserAttack.jumpAttack();
            jumpCoolDownTimer.play();
        }
    }

    public boolean isUsingAnotherAttack() {
        return isUsingAnotherAttack;
    }

    public void setUsingAnotherAttack(boolean usingAnotherAttack) {
        isUsingAnotherAttack = usingAnotherAttack;
    }
}
