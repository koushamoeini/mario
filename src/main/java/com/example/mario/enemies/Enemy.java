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
    private boolean isActive = false;
    private boolean isInvincible = false;
    private Timeline invincibleEnemy;

    public Enemy(int edgeX, int edgeY, int blockX, int blockY, boolean jumpDie, int enemyHp, int enemyScore, int xVelocity) {
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
        this.jumpDie = jumpDie;
        this.enemyHp = enemyHp;
        this.enemyScore = enemyScore;
        this.xVelocity = xVelocity;
        invincibleEnemy = new Timeline(invicibleEnemyKeyFrame);
        invincibleEnemy.setCycleCount(Animation.INDEFINITE);
    }

    KeyFrame invicibleEnemyKeyFrame = new KeyFrame(Duration.seconds(1), event -> {
        isInvincible = false;
        invincibleEnemy.stop();
    });


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
        if (!(this instanceof Flower)) {
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
                    if (this.getLayoutX() + this.getFitWidth() > block.getLayoutX() - xVelocity && this.getLayoutX() + this.getFitWidth() < block.getLayoutX() + block.getFitWidth() + xVelocity) {
                        for (int j = (int) this.getLayoutY(); j <= this.getLayoutY() + this.getFitHeight(); j++) {
                            if (j > block.getLayoutY() && j < block.getLayoutY() + block.getFitHeight()) {
                                goingLeft = true;
                                break;
                            }
                        }
                    }
                    if (this.getLayoutX() > block.getLayoutX() - xVelocity && this.getLayoutX() < block.getLayoutX() + block.getFitWidth() + xVelocity) {
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

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    public Timeline getInvincibleEnemy() {
        return invincibleEnemy;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public boolean isDownCollusion() {
        return downCollusion;
    }

    public boolean isGoingLeft() {
        return goingLeft;
    }

    public int getFallVelocity() {
        return fallVelocity;
    }

    public void setFallVelocity(int fallVelocity) {
        this.fallVelocity = fallVelocity;
    }

    public boolean isActive() {
        return isActive;
    }

    public int getxVelocity() {
        return xVelocity;
    }
}
