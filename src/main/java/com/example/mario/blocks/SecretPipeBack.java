package com.example.mario.blocks;

import javafx.scene.image.Image;

public class SecretPipeBack extends Block{
    public SecretPipeBack( int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY);
        Image image = new Image("Images/blocks/pipe.png");
        this.setImage(image);
    }

}