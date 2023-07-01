package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Gun.Gun;
import com.example.mario.Mario.Mario;
import com.example.mario.blocks.Block;
import com.example.mario.controllers.GameLabelController;
import com.example.mario.enemies.Enemy;
import com.example.mario.enemies.EnemyCollision;
import com.example.mario.user.GameData;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.List;

public class BowserShot extends ImageView {
    private final List<Block> blocks;
    private Mario mario;
    private Timeline timeline;
    private Bowser bowser;
    private int direction=-1;

    public BowserShot(MotionHandler motionHandler) {
        this.blocks = motionHandler.getBlocks();
        this.mario=motionHandler.getMario();
        this.bowser=motionHandler.bowserFounder();
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        if(bowser.isGoingLeft()) direction=-1;
        else direction=1;
    }
    KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event -> {

        try {
            this.setLayoutX(this.getLayoutX() + direction*mario.getxVelocity());
        } catch (Exception ignored) {
        }
    });
}
