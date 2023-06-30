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
    private final Mario mario;
    private Bowser bowser ;
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
            useJump();
        } catch (Exception ignored) {
        }
    }

    public void useGrab() {
        if ((Math.abs(mario.getLayoutX()+mario.getFitWidth()/2 - (bowser.getLayoutX()+bowser.getFitWidth()/2)) < 110 &&mario.getLayoutY()-bowser.getLayoutY() >0
                && mario.getLayoutY()-bowser.getLayoutY() < 120 && !isUsingAnotherAttack && !isGrabCoolDown)) {
            isUsingAnotherAttack = true;
            isGrabCoolDown = true;
            bowserAttack.grabAttack();
            grabCoolDownTimer.play();
        }
    }
    KeyFrame grabCoolDownKeyFrame = new KeyFrame(Duration.seconds(9), event -> {
        isGrabCoolDown = false;
        grabCoolDownTimer.stop();
    });
    public void useJump(){
        if(!isUsingAnotherAttack&&!isJumpCoolDown){
            isUsingAnotherAttack=true;
            isJumpCoolDown=true;
            bowserAttack.jumpAttack();
            jumpCoolDownTimer.play();
        }
    }
    KeyFrame jumpCoolDownKeyFrame = new KeyFrame(Duration.seconds(6), event -> {
        isJumpCoolDown = false;
        jumpCoolDownTimer.stop();
    });
    public boolean isUsingAnotherAttack() {
        return isUsingAnotherAttack;
    }

    public void setUsingAnotherAttack(boolean usingAnotherAttack) {
        isUsingAnotherAttack = usingAnotherAttack;
    }
}
