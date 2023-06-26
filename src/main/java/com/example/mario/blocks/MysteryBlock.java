package com.example.mario.blocks;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class MysteryBlock extends Block{
    private final Timeline timeline;
    private boolean isActive=true;
    private int counter=0;
    KeyFrame keyFrame = new KeyFrame(Duration.millis(100), event -> {
        if(isActive) {
            counter++;
            if (counter % 18 < 9)
                setImage(new Image("Images/blocks/prize_active.png"));
            else
                setImage(new Image("Images/blocks/prize_normal.png"));
        }
    });

    public MysteryBlock( int edgeX, int edgeY, int blockX, int blockY) {
        super( edgeX, edgeY, blockX, blockY);
        Image image = new Image("Images/blocks/prize_active.png");
        this.setImage(image);
        timeline=new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isActive() {
        return isActive;
    }
}
