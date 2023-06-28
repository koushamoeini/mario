package com.example.mario.Mario;

import javafx.scene.effect.ColorAdjust;


public class MarioStateManger {
    private final Mario mario;
    public MarioStateManger(Mario mario) {
        this.mario = mario;

    }
    public void backToMiniState(){
        mario.setFitHeight(30);
        mario.setMarioHp(0);
        mario.setCanBreakBlock(false);
        mario.setJumpVelocity(13);
        mario.setCanShoot(false);
        mario.setEffect(new ColorAdjust());
    }
    public void transformToMegaState(){
        mario.setFitHeight(60);
        mario.setMarioHp(1);
        mario.setCanBreakBlock(true);
        mario.setJumpVelocity(15);
        mario.setCanShoot(false);
        mario.setEffect(new ColorAdjust());
    }

    public void transformToFireState(){
        ColorAdjust colorAdjust=new ColorAdjust();
        colorAdjust.setHue(0.0);
        colorAdjust.setSaturation(2);
        colorAdjust.setContrast(1.0);
        mario.setFitHeight(60);
        mario.setMarioHp(2);
        mario.setCanBreakBlock(true);
        mario.setJumpVelocity(17);
        mario.setCanShoot(true);
        mario.setEffect(colorAdjust);
    }
}
