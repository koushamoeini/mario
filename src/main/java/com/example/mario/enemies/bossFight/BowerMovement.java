package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Mario.Mario;
import com.example.mario.enemies.Enemy;

import java.util.List;

public class BowerMovement {
    private MotionHandler motionHandler;
    private Mario mario;
    private Bowser bowser;

    public BowerMovement(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
        this.mario = motionHandler.getMario();
        bowserFounder();
    }

    public void bowserFounder() {
        List<Enemy> enemies = motionHandler.getEnemies();
        for (Enemy enemy : enemies) {
            if (enemy instanceof Bowser bowser) this.bowser = bowser;
        }
    }

    public void horizontalMovement() {
        if (mario.getLayoutX() + mario.getFitHeight() - bowser.getLayoutX() < 0 && Math.abs(mario.getLayoutX() - bowser.getLayoutX()) > 120)
            bowser.setLayoutX(bowser.getLayoutX() - 2);
        else if (mario.getLayoutX() - bowser.getLayoutX() > 0 && Math.abs(mario.getLayoutX() - (bowser.getLayoutX() + bowser.getFitWidth())) > 120)
            bowser.setLayoutX(bowser.getLayoutX() + 2);
    }
    public void gravity(){
        this.
    }
}

