package com.example.mario.Gun;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Gun extends ImageView {
    private int range;
    private int returnRange;

    private int distanceTraveled=1;
    private boolean isLeft;

    public Gun(int edgeX, int edgeY, int blockX, int blockY,int range,int returnRange,boolean isLeft) {
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
        this.range=range;
        this.isLeft=isLeft;
        this.returnRange=returnRange;
        new ShotMovement(this);
    }

    public boolean isLeft() {
        return isLeft;
    }

    public int getRange() {
        return range;
    }
    public int getReturnRange() {
        return returnRange;
    }
    public int getDistanceTraveled() {
        return distanceTraveled;
    }
    public void setDistanceTraveled(int distanceTraveled) {
        this.distanceTraveled = distanceTraveled;
    }
}
