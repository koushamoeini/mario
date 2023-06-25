package com.example.mario.enemies;

import javafx.scene.image.Image;

public class Spike extends Enemy {
    public Spike(int edgeX, int edgeY, int blockX, int blockY,boolean isRight) {
        super(edgeX, edgeY, blockX, blockY);
        Image image;
        if(isRight)  image = new Image("Images/spikeRight.png");
        else  image = new Image("Images/spikeLeft.png");
        this.setImage(image);
    }
}
