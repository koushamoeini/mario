package com.example.mario.Gun;

import javafx.scene.image.ImageView;


public class Gun extends ImageView {
    private final int range;
    private final int returnRange;

    private int distanceTraveled=1;
    private final boolean isLeft;

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
