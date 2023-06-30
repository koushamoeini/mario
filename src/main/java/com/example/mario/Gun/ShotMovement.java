package com.example.mario.Gun;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class ShotMovement {
    private Gun gun;
    private Timeline timeline;
    private int velocity = 3;

    public ShotMovement(Gun gun) {
        this.gun = gun;
        if(gun.isLeft()) velocity*=-1;
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    KeyFrame keyFrame = new KeyFrame(Duration.millis(5), event -> {
        try {
            if (gun.getDistanceTraveled() == gun.getRange() * 30 + velocity+1) {
                gun.setDistanceTraveled(0);
                velocity *= -1;
            }
            if (gun.getDistanceTraveled() == gun.getReturnRange()*30) {
                gun.setVisible(false);
                gun = null;
                timeline.stop();
            }
            gun.setDistanceTraveled(gun.getDistanceTraveled() + 3);
            gun.setLayoutX(gun.getLayoutX() + velocity);
        } catch (Exception ignored) {
        }
    });
}
