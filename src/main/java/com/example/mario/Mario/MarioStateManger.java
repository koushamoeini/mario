package com.example.mario.Mario;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.ColorAdjust;
import javafx.util.Duration;

public class MarioStateManger {
    private Mario mario;
    private ColorAdjust colorAdjust=new ColorAdjust();
    public MarioStateManger(Mario mario) {
        this.mario = mario;

    }
    public void backToMiniState(){
        colorAdjust.setBrightness(0);
        mario.setFitHeight(30);
        mario.setMarioHp(0);
        mario.setCanBreakBlock(false);
        mario.setJumpVelocity(13);
        mario.setCanShoot(false);
        mario.setEffect(colorAdjust);
    }
    public void transformToMegaState(){
        colorAdjust.setBrightness(0);
        mario.setFitHeight(60);
        mario.setMarioHp(1);
        mario.setCanBreakBlock(true);
        mario.setJumpVelocity(15);
        mario.setCanShoot(false);
        mario.setEffect(colorAdjust);
    }

    public void transformToFireState(){
        colorAdjust.setBrightness(1);
        mario.setFitHeight(60);
        mario.setMarioHp(2);
        mario.setCanBreakBlock(true);
        mario.setJumpVelocity(17);
        mario.setCanShoot(false);
        mario.setEffect(colorAdjust);
    }
}
