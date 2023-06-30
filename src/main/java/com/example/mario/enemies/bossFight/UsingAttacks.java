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
    private boolean isUsingAnotherAttack=false;
    private Timeline grabCoolDownTimer;
    private final BowserAttack bowserAttack;

    public UsingAttacks(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
        this.mario = motionHandler.getMario();
        this.bowser = motionHandler.bowserFounder();
        this.bowserAttack = motionHandler.getBowserAttack();
        grabCoolDownTimer = new Timeline(grabCoolDownKeyFrame);
        grabCoolDownTimer.setCycleCount(Animation.INDEFINITE);
    }
    public void useAttack(){
        try {
            useGrab();
        }catch (Exception ignored){}
    }
    KeyFrame grabCoolDownKeyFrame = new KeyFrame(Duration.seconds(7), event -> {
        isGrabCoolDown = false;
        grabCoolDownTimer.stop();
    });

    public void useGrab() {
        if (Math.abs(mario.getLayoutX() - bowser.getLayoutX()) < 90 && bowser.getLayoutY() - mario.getLayoutY() < 60&&!isUsingAnotherAttack&&!isGrabCoolDown) {
            isUsingAnotherAttack=true;
            isGrabCoolDown = true;
            bowserAttack.grabAttack();
        }
    }
}
