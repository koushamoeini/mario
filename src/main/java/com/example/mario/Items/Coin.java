package com.example.mario.Items;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Coin extends Item {
    private final Timeline timeline;
    private Timeline fallTimeline;
    private int counter=0;
    KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
        counter++;
        setImage(new Image("Images/Items/coin"+(counter%4+1)+".png"));
    });
    public Coin(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX,edgeY,blockX,blockY,0,0);
        timeline=new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        fallTimeline = new Timeline(fallKeyFrame);
        fallTimeline.setCycleCount(Animation.INDEFINITE);
        fallTimeline.play();
        Image image=new Image("Images/Items/coin1.png");
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
        this.setImage(image);
    }
    KeyFrame fallKeyFrame = new KeyFrame(Duration.millis(10), event ->movement() );
    public Timeline getTimeline() {
        return timeline;
    }

    public Timeline getFallTimeline() {
        return fallTimeline;
    }
}
