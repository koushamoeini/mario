package com.example.mario.enemies;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Flower extends Enemy {
    private int firstLayOutY;
    private double enemyVelocity= (Math.random() * 0.5) + 0.5;
    private Timeline timeline;
    private int counter=0;

    public Flower(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY);
        this.firstLayOutY=blockY;
        Image image = new Image("Images/flower(cagney).png");
        this.setImage(image);
        timeline=new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    KeyFrame keyFrame = new KeyFrame(Duration.millis(10), event -> {
        counter++;
        if ((this.getLayoutY() >= firstLayOutY + this.getFitHeight() || this.getLayoutY() < firstLayOutY)&&counter%5==0)
            enemyVelocity *= -1;
        this.setLayoutY(this.getLayoutY() + enemyVelocity);
    });

    public Timeline getTimeline() {
        return timeline;
    }
}
