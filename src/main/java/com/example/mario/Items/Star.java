package com.example.mario.Items;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Star extends Item {
    private final int xVelocity = 1;
    private Timeline delayTimeline;
    private Timeline timeline;

    public Star(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY,5);
        Image image = new Image("Images/Items/star.png");
        this.setImage(image);
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        delayTimeline = new Timeline(delayKeyFrame);
        delayTimeline.setCycleCount(Animation.INDEFINITE);
        delayTimeline.play();
    }
    KeyFrame delayKeyFrame=new KeyFrame(Duration.seconds(3), event ->{
        timeline.play();
        delayTimeline.stop();
    });
    KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event ->movement(xVelocity) );
}
