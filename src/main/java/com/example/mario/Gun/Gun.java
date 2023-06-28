package com.example.mario.Gun;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;

public class Gun extends ImageView {
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
}
