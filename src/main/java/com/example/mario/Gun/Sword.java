package com.example.mario.Gun;

import javafx.scene.image.Image;

public class Sword extends Gun{
    public Sword(int edgeX, int edgeY, int blockX, int blockY,boolean isLeft) {
        super(edgeX, edgeY, blockX, blockY, 6, 6, isLeft);
        Image image = new Image(getClass().getResource("/Images/Shots/sword.png").toExternalForm());
        this.setImage(image);
    }
}
