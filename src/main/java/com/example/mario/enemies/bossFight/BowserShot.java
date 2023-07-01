package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Gun.Gun;
import com.example.mario.Gun.Sword;
import com.example.mario.Mario.Mario;
import com.example.mario.blocks.Block;
import com.example.mario.enemies.Enemy;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BowserShot extends ImageView {
    private final List<Block> blocks;
    private Mario mario;
    private Timeline timeline;
    private Bowser bowser;
    private int direction = 1;
    private MotionHandler motionHandler;
    private Timeline checkCollision;

    public BowserShot(int edgeX, int edgeY, int blockX, int blockY, MotionHandler motionHandler, boolean direction) {
        this.setLayoutX(blockX);
        this.setLayoutY(blockY);
        this.setFitWidth(edgeX);
        this.setFitHeight(edgeY);
        this.motionHandler=motionHandler;
        this.setImage(new Image("Images/Shots/fireball.png"));
        this.blocks = motionHandler.getBlocks();
        this.mario = motionHandler.getMario();
        this.bowser = motionHandler.bowserFounder();
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        checkCollision = new Timeline(checkCollisionKeyFrame);
        checkCollision.setCycleCount(Animation.INDEFINITE);
        checkCollision.play();
        if (direction) this.direction = -1;
    }

    KeyFrame keyFrame = new KeyFrame(Duration.millis(7), event -> {
        try {
            this.setLayoutX(this.getLayoutX() + direction * 2);
        } catch (Exception ignored) {
        }
    });
    KeyFrame checkCollisionKeyFrame = new KeyFrame(Duration.millis(1), event -> {
        if (this.isVisible()) {
            try {
                blockCollision();
                enemyCollision();
            } catch (Exception ignored) {
            }
        }
        else checkCollision.stop();
    });
    //collisions:
    public void checkCollision() {
        if (this.isVisible()) {
            try {
                blockCollision();
                enemyCollision();
            } catch (Exception ignored) {
            }
        }
    }

    public void blockCollision() {
        for (Block block : blocks) {
            if (block.getBoundsInParent().intersects(this.getBoundsInParent())) {
                this.setVisible(false);
            }
        }
    }

    public void enemyCollision() throws IOException {
        if (this.getBoundsInParent().intersects(mario.getBoundsInParent())) {
            this.setVisible(false);
            motionHandler.doDead();
        }
    }
}
