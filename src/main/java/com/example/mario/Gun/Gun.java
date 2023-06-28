package com.example.mario.Gun;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.image.ImageView;

public class Gun extends ImageView {
    public Gun(int edgeX, int edgeY, int blockX, int blockY) {
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
    }
}
