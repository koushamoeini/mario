package com.example.mario.blocks;

import javafx.scene.image.Image;

public class Pipe extends Block{
    private Image image;
    public Pipe( int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY);
        image = new Image("Images/blocks/pipe.png");
        this.setImage(image);
    }
}
