package com.example.mario.blocks;

import javafx.scene.image.Image;

public class Stairs extends Block{
    public Stairs( int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY);
        Image image = new Image("Images/stair.png");
        this.setImage(image);
    }
}
