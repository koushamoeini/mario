package com.example.mario.blocks;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class SecretPipe extends Block{
    Timeline checkActive;
    public SecretPipe( int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY);
        Image image = new Image("Images/blocks/pipe.png");
        this.setImage(image);
        checkActive=new Timeline(keyFrame);
        checkActive.setCycleCount(Animation.INDEFINITE);
        checkActive.play();
    }
    KeyFrame keyFrame = new KeyFrame(Duration.millis(1), event -> {

    });
}