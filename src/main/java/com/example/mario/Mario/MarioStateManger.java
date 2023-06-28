package com.example.mario.Mario;

import javafx.scene.effect.ColorAdjust;


public class MarioStateManger {
    private Mario mario;
    private ColorAdjust colorAdjust=new ColorAdjust();
    public MarioStateManger(Mario mario) {
        this.mario = mario;

    }
    public void backToMiniState(){
        colorAdjust.setHue(0);
        colorAdjust.setSaturation(0);
        colorAdjust.setContrast(0);
        mario.setFitHeight(30);
        mario.setMarioHp(0);
        mario.setCanBreakBlock(false);
        mario.setJumpVelocity(13);
        mario.setCanShoot(false);
        mario.setEffect(colorAdjust);
    }
    public void transformToMegaState(){
        colorAdjust.setHue(0);
        colorAdjust.setSaturation(0);
        colorAdjust.setContrast(0);
        mario.setFitHeight(60);
        mario.setMarioHp(1);
        mario.setCanBreakBlock(true);
        mario.setJumpVelocity(15);
        mario.setCanShoot(false);
        mario.setEffect(colorAdjust);
    }

    public void transformToFireState(){
        colorAdjust.setHue(0.0);
        colorAdjust.setSaturation(2);
        colorAdjust.setContrast(1.0);
        mario.setFitHeight(60);
        mario.setMarioHp(2);
        mario.setCanBreakBlock(true);
        mario.setJumpVelocity(17);
        mario.setCanShoot(false);
        mario.setEffect(colorAdjust);
    }
}
