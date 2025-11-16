package com.example.mario.Gun;

import javafx.scene.image.Image;

public class Shot extends Gun {
    public Shot(int edgeX, int edgeY, int blockX, int blockY,boolean isLeft) {
        super(edgeX, edgeY, blockX, blockY,16,0,isLeft);
        Image image = new Image(getClass().getResource("/Images/Shots/fireball.png").toExternalForm());
        this.setImage(image);
    }
}
