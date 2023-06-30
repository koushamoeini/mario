package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Mario.Mario;
import com.example.mario.enemies.Enemy;
import javafx.scene.image.Image;

import java.util.List;

public class BowserMovement {
    private MotionHandler motionHandler;
    private Mario mario;
    private Bowser bowser;

    public BowserMovement(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
        this.mario = motionHandler.getMario();
        this.bowser=motionHandler.bowserFounder();
    }

    public void bowserAllMove(){
        horizontalMovement();
        verticalMovement();
    }
    public void horizontalMovement() {
        if (mario.getLayoutX() + mario.getFitHeight() - bowser.getLayoutX() < 0 && Math.abs(mario.getLayoutX() - bowser.getLayoutX()) > 90) {
            bowser.setBowerGoingLeft(true);
            bowser.setImage(new Image("Images/enemies/bowser/bowserLeft.png"));
            bowser.setLayoutX(bowser.getLayoutX() - 1);
        }
        else if (mario.getLayoutX() - bowser.getLayoutX() > 0 && Math.abs(mario.getLayoutX() - (bowser.getLayoutX() + bowser.getFitWidth())) > 90){
            bowser.setBowerGoingLeft(false);
            bowser.setImage(new Image("Images/enemies/bowser/bowser.png"));
            bowser.setLayoutX(bowser.getLayoutX() + 1);
        }
    }
    public void verticalMovement(){
        int random = (int) (Math.random() * 4);
        int gravity = 1;
        if (bowser.isDownCollusion()) {
            if (random % 4 == 0) bowser.setFallVelocity(bowser.getFallVelocity() - gravity);
            bowser.setLayoutY(bowser.getLayoutY() - bowser.getFallVelocity());
        }
    }
}

