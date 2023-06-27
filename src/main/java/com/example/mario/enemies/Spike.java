package com.example.mario.enemies;

import javafx.scene.image.Image;

public class Spike extends Enemy {
    public Spike(int edgeX, int edgeY, int blockX, int blockY,boolean isRight) {
        super(edgeX, edgeY, blockX, blockY,false,999999,0,0);
        Image image;
        if(isRight)  image = new Image("Images/enemies/spikeRight.png");
        else  image = new Image("Images/enemies/spikeLeft.png");
        this.setImage(image);
    }
}
