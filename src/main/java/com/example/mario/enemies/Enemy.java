package com.example.mario.enemies;

import com.example.mario.blocks.Block;
import com.example.mario.blocks.KillBlock;
import com.example.mario.blocks.WinBlock;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.util.ArrayList;

public abstract class Enemy extends ImageView {
    private boolean jumpDie;
    private int enemyHp;
    private boolean downCollusion = false;
    private boolean goingLeft = false;
    private int fallVelocity = 0;
    private int enemyScore;
    private int xVelocity;
    private Timeline timeline;
    private boolean isActive=false;

    public Enemy(int edgeX, int edgeY, int blockX, int blockY, boolean jumpDie, int enemyHp, int enemyScore, int xVelocity) {
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
        this.jumpDie = jumpDie;
        this.enemyHp = enemyHp;
        this.enemyScore = enemyScore;
        this.xVelocity = xVelocity;
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    KeyFrame keyFrame = new KeyFrame(Duration.millis(20), event -> {
        movement(xVelocity);
    });

    public void movement(int xVelocity) {
        if(isActive) xMovement(xVelocity);
        if(!(this instanceof Flower||this instanceof Spike)) {
            yMovement();
        }
    }

    public void xMovement(int xVelocity) {
        if (goingLeft) xVelocity *= -1;
        this.setLayoutX(this.getLayoutX() + xVelocity);
    }

    public void yMovement() {
        int random = (int) (Math.random() * 4 + 1);
        int gravity = 1;
        if (!downCollusion) {
            if (random % 4 == 0) fallVelocity -= gravity;
            this.setLayoutY(this.getLayoutY() - fallVelocity);
        }
    }
    public int getEnemyHp() {
        return enemyHp;
    }

    public void setEnemyHp(int enemyHp) {
        this.enemyHp = enemyHp;
    }

    public boolean isJumpDie() {
        return jumpDie;
    }

    public int getEnemyScore() {
        return enemyScore;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public void enemyCollision(ArrayList<Block> blocks) {
        if(!(this instanceof Flower)) {
            downCollusion = false;
            for (Block block : blocks) {
                if (!(block instanceof WinBlock || block instanceof KillBlock)) {
                    if (this.getLayoutY() + this.getFitHeight() >= block.getLayoutY() && this.getLayoutY() + this.getFitHeight() <= block.getLayoutY() + block.getFitHeight()) {
                        for (int j = (int) this.getLayoutX(); j <= this.getLayoutX() + this.getFitWidth(); j++) {
                            if (j > block.getLayoutX() && j < block.getLayoutX() + block.getFitWidth()) {
                                this.setLayoutY(block.getLayoutY() - this.getFitHeight());
                                downCollusion = true;
                                break;
                            }
                        }
                    }
                    if (this.getLayoutX() + this.getFitWidth() > block.getLayoutX() - 2 && this.getLayoutX() + this.getFitWidth() < block.getLayoutX() + block.getFitWidth() + 2) {
                        for (int j = (int) this.getLayoutY(); j <= this.getLayoutY() + this.getFitHeight(); j++) {
                            if (j > block.getLayoutY() && j < block.getLayoutY() + block.getFitHeight()) {
                                goingLeft = true;
                                break;
                            }
                        }
                    }
                    if (this.getLayoutX() > block.getLayoutX() - 2 && this.getLayoutX() < block.getLayoutX() + block.getFitWidth() + 2) {
                        for (int j = (int) this.getLayoutY(); j <= this.getLayoutY() + this.getFitHeight(); j++) {
                            if (j > block.getLayoutY() && j < block.getLayoutY() + block.getFitHeight()) {
                                goingLeft = false;
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}
