package com.example.mario.Items;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Coin extends ImageView {
    private Timeline timeline;
    int counter=0;
    KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
        counter++;
        setImage(new Image("Images/coin"+(counter%4+1)+".png"));
    });
    public Coin(int edgeX, int edgeY, int blockX, int blockY) {
        timeline=new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        Image image=new Image("Images/coin1.png");
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
        this.setImage(image);
    }

    public Timeline getTimeline() {
        return timeline;
    }
}
