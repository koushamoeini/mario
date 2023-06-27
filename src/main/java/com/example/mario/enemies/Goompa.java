package com.example.mario.enemies;

import javafx.animation.Animation;
import javafx.animation.Timeline;
import javafx.scene.image.Image;

public class Goompa extends Enemy {
    public Goompa(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY,true,1,1,2);
        Image image = new Image("Images/enemies/goompa.png");
        this.setImage(image);
    }
}
