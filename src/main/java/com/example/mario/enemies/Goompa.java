package com.example.mario.enemies;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Goompa extends Enemy {
    private Timeline timeline;

    public Goompa(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY, true, 1, 1, 2);
        Image image = new Image(getClass().getResource("/Images/enemies/goompa.png").toExternalForm());
        this.setImage(image);
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    public void xMovement(int xVelocity) {
        if (this.isGoingLeft()) xVelocity *= -1;
        this.setLayoutX(this.getLayoutX() + xVelocity);
    }

    public void yMovement() {
        int random = (int) (Math.random() * 4 + 1);
        int gravity = 1;
        if (this.isDownCollusion()) {
            if (random % 4 == 0) this.setFallVelocity(this.getFallVelocity() - gravity);
            this.setLayoutY(this.getLayoutY() - this.getFallVelocity());
        }
    }

    KeyFrame keyFrame = new KeyFrame(Duration.millis(20), event -> movement(2));

    public void movement(int xVelocity) {
        if (this.isActive()) xMovement(xVelocity);
        yMovement();
    }

    public Timeline getTimeline() {
        return timeline;
    }
}
