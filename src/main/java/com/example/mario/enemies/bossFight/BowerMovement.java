package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.enemies.Enemy;
import java.util.List;
public class BowerMovement {
    private MotionHandler motionHandler;
    private Bowser bowser;
    public BowerMovement(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
        bowserFounder();
    }
    public void bowserFounder(){
        List<Enemy> enemies=motionHandler.getEnemies();
        for(Enemy enemy:enemies){
            if(enemy instanceof Bowser bowser) this.bowser=bowser;
        }
    }
    public void horizontalMovement(){
    }
}
