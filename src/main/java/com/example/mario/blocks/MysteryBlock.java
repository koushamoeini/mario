package com.example.mario.blocks;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class MysteryBlock extends Block{
    private Timeline timeline;
    int counter=0;
    KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
        counter++;
        if(counter%24<12)
        setImage(new Image("Images/prize_active.png"));
        else
            setImage(new Image("Images/prize_normal.png"));
    });

    public MysteryBlock( int edgeX, int edgeY, int blockX, int blockY) {
        super( edgeX, edgeY, blockX, blockY);
        Image image = new Image("Images/prize_active.png");
        this.setImage(image);
        timeline=new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public Timeline getTimeline() {
        return timeline;
    }
}
