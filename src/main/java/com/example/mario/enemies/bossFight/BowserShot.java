package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Mario.Mario;
import com.example.mario.blocks.Block;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.List;

public class BowserShot extends ImageView {
    private final List<Block> blocks;
    private Mario mario;
    private Timeline timeline;
    private Bowser bowser;
    private int direction=1;

    public BowserShot(int edgeX, int edgeY, int blockX, int blockY,MotionHandler motionHandler,boolean direction) {
        this.setLayoutX(blockX);
        this.setLayoutY(blockY);
        this.setFitWidth(edgeX);
        this.setFitHeight(edgeY);
        this.setImage(new Image("Images/Shots/fireball.png"));
        this.blocks = motionHandler.getBlocks();
        this.mario=motionHandler.getMario();
        this.bowser=motionHandler.bowserFounder();
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        if(direction) this.direction=-1;
    }
    KeyFrame keyFrame = new KeyFrame(Duration.millis(7), event -> {
        try {
            this.setLayoutX(this.getLayoutX() + direction*2);
        } catch (Exception ignored) {
        }
    });
}
