package com.example.mario.blocks;

import javafx.scene.image.Image;

public class Pipe extends Block{
    public Pipe( int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY);
        Image image = new Image(getClass().getResource("/Images/blocks/pipe.png").toExternalForm());
        this.setImage(image);
    }
}
