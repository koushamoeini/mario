package com.example.mario.blocks;

import javafx.scene.image.Image;

public class Brick extends Block{
    private Image image;
    public Brick( int edgeX, int edgeY, int blockX, int blockY) {
        super( edgeX, edgeY, blockX, blockY);
        this.image = new Image("Images/brick.png");
        this.setImage(image);
    }
}
