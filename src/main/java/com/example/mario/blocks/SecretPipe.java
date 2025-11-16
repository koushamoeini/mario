package com.example.mario.blocks;

import javafx.scene.image.Image;

public class SecretPipe extends Block{
    public SecretPipe( int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY);
        Image image = new Image(getClass().getResource("/Images/blocks/pipe.png").toExternalForm());
        this.setImage(image);
    }

}