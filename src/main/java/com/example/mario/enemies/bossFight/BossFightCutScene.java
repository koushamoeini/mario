package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Mario.Mario;

public class BossFightCutScene {
    private MotionHandler motionHandler;
    private Mario mario;
    private Bowser bowser;

    public BossFightCutScene(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
        this.motionHandler = motionHandler;
        this.mario = motionHandler.getMario();
        this.bowser = motionHandler.bowserFounder();
        this.bowserAttack = motionHandler.getBowserAttack();
    }
}
