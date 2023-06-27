package com.example.mario.enemies;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Koopa extends Enemy {
    private Timeline koopaTimeline;
    private Timeline koopaAnimation;
    private Timeline koopaAnimationStopper;
    public Koopa(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY,true,2,2,3);
        Image image = new Image("Images/enemies/koopa.png");
        this.setImage(image);
        koopaTimeline=new Timeline(keyFrame);
        koopaTimeline.setCycleCount(Animation.INDEFINITE);
        koopaAnimation=new Timeline(keyFrame2);
        koopaAnimation.setCycleCount(Animation.INDEFINITE);
        koopaAnimationStopper=new Timeline(keyFrame3);
        koopaAnimationStopper.setCycleCount(Animation.INDEFINITE);
    }
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(3), event -> {
        this.setxVelocity(3);
        this.setEnemyHp(this.getEnemyHp()+1);
        koopaTimeline.stop();
    });
    KeyFrame keyFrame2 = new KeyFrame(Duration.millis(6), event -> this.setLayoutX(this.getLayoutX()+1));
    KeyFrame keyFrame3 = new KeyFrame(Duration.millis(500), event -> {
        koopaAnimation.stop();
        koopaTimeline.play();
        koopaAnimationStopper.stop();
    });
    public void doAngry(){
        koopaAnimation.play();
        koopaAnimationStopper.play();
        this.setxVelocity(0);
    }
}
