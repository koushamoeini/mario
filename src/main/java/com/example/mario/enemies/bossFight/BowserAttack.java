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
    public BowserAttack(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
        this.mario = motionHandler.getMario();
        this.bowser=motionHandler.bowserFounder();
        grabStartTimer=new Timeline(grabStartKeyFrame);
        grabStartTimer.setCycleCount(Animation.INDEFINITE);
        grabAttackTimer=new Timeline(grabAttackKeyFrame);
        grabAttackTimer.setCycleCount(Animation.INDEFINITE);
    }
    KeyFrame grabStartKeyFrame = new KeyFrame(Duration.seconds(1), event -> {
        if(Math.abs(mario.getLayoutX()-bowser.getLayoutX())<90&&bowser.getLayoutY()-mario.getLayoutY()<60){
            mario.setGrabbing(true);
            grabAttackTimer.play();
            grabStartTimer.stop();
        }
    });
    KeyFrame grabAttackKeyFrame = new KeyFrame(Duration.seconds(3), event -> {
        if(mario.isGrabbing()){
            try {
                motionHandler.doDead();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        grabAttackTimer.stop();
    });
    public void grabAttack(){
        mario.setInvincible(true);
        if(bowser.isBowerGoingLeft()) {
            bowser.setImage(new Image("Images/enemies/bowser/grabAttackLeft.png"));
            grabStartTimer.play();
        }
    }
}
