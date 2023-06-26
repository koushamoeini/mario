package com.example.mario.Items;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Mushroom extends Item {
    private final int xVelocity = 1;
    private final int yVelocity = 0;
    private Timeline delayTimeLine;
    private Timeline timeline;

    public Mushroom(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY);
        Image image = new Image("Images/Items/mushroom.png");
        this.setImage(image);
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        delayTimeLine = new Timeline(delayKeyFrame);
        delayTimeLine.setCycleCount(Animation.INDEFINITE);
        delayTimeLine.play();
    }
    KeyFrame delayKeyFrame=new KeyFrame(Duration.seconds(3), event ->{
        timeline.play();
        delayTimeLine.stop();
    });
    KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event ->movement(xVelocity,yVelocity) );
}
