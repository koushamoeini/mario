package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.enemies.Enemy;
import javafx.scene.image.Image;

public class Bowser extends Enemy {
    MotionHandler motionHandler;

    protected Bowser(int edgeX, int edgeY, int blockX, int blockY, MotionHandler motionHandler) {
        super(edgeX, edgeY, blockX, blockY, true, 20, 100, 0);
        Image image = new Image("Images/enemies/flower(cagney).png");
        this.setImage(image);
        this.motionHandler=motionHandler;
        motionHandler
    }
}
