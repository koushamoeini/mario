package com.example.mario.Gun;

import javafx.scene.image.Image;

public class Shot extends Gun {
    public Shot(int edgeX, int edgeY, int blockX, int blockY,boolean isLeft) {
        super(edgeX, edgeY, blockX, blockY,16,0,isLeft);
        Image image = new Image("Images/Shots/fireball.png");
        this.setImage(image);
    }
}
