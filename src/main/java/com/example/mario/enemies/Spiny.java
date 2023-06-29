package com.example.mario.enemies;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Spiny extends Enemy {
    private boolean isSpinyGoingLeft = false;
    private final Timeline timeline2;
    private final BooleanProperty spinyChangeDirection = new SimpleBooleanProperty(false);
    private final BooleanProperty spinyActive = new SimpleBooleanProperty(false);

    public Spiny(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY, false, 1, 3, 3);

        Image image = new Image("Images/enemies/spiny.png");
        this.setImage(image);
        Timeline timeline = new Timeline(keyFrame0);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        timeline2 = new Timeline(keyFrame1);
        timeline2.setCycleCount(Animation.INDEFINITE);
        spinyChangeDirection.addListener((observable, oldValue, newValue) -> this.setxVelocity(3));
        spinyActive.addListener((observable, oldValue, newValue) -> {
            if(newValue) timeline2.play();
            else timeline2.stop();
        });
    }

    public void xMovement(double xVelocity) {
        if (isSpinyGoingLeft) xVelocity *= -1;
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

    KeyFrame keyFrame0 = new KeyFrame(Duration.millis(20), event -> movement(this.getxVelocity()));
    KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(1), event -> {
        double acceleration = 0.5;
        this.setxVelocity(this.getxVelocity() + acceleration);
    });

    public void movement(double xVelocity) {
        if (this.isActive()) xMovement(xVelocity);
        yMovement();
    }

    public void setSpinyGoingLeft(boolean goingLeft) {
        isSpinyGoingLeft = goingLeft;
    }

    public void setSpinyChangeDirection(boolean spinyChangeDirection) {
        this.spinyChangeDirection.set(spinyChangeDirection);
    }
    public void setSpinyActive(boolean spinyActive) {
        this.spinyActive.set(spinyActive);
    }
    public boolean isSpinyGoingLeft() {
        return isSpinyGoingLeft;
    }
}
