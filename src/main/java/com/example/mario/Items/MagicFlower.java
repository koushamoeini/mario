package com.example.mario.Items;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class MagicFlower extends Item {
    private Timeline timeline;
    private final int xVelocity = 0;
    private final int yVelocity = 0;

    public MagicFlower(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY);
        Image image = new Image("Images/Items/magicFlower.png");
        this.setImage(image);
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event -> {
        movement(xVelocity,yVelocity);
    });
}
