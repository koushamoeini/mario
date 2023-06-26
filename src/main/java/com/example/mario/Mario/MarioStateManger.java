package com.example.mario.Mario;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class MarioStateManger {
    private Mario mario;
    private Timeline timeline;

    public MarioStateManger(Mario mario) {
        this.mario = mario;
        timeline=new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    public void backToMiniState(){
        mario.setFitHeight(30);
        mario.setMarioHp(0);
        mario.setCanBreakBlock(false);
        mario.setJumpVelocity(10);
        mario.setCanShoot(false);
        mario.setCanSit(false);
    }
    public void transformToMegaState(){
        mario.setFitHeight(60);
        mario.setMarioHp(1);
        mario.setCanBreakBlock(true);
        mario.setJumpVelocity(15);
        mario.setCanShoot(false);
        mario.setCanSit(true);
    }
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(5), event -> {
        mario.setInvincible(false);
        timeline.stop();
    });
    public void transformToFireState(){
        mario.setFitHeight(60);
        mario.setMarioHp(2);
        mario.setCanBreakBlock(true);
        mario.setJumpVelocity(15);
        mario.setCanShoot(false);
        mario.setCanSit(true);
        mario.setInvincible(true);
        timeline.play();
    }
}
