package com.example.mario.blocks;

import javafx.animation.Timeline;
import javafx.scene.image.Image;

public class SecretPipe extends Block{
    Timeline checkActive;
    public SecretPipe( int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY);
        Image image = new Image("Images/blocks/pipe.png");
        this.setImage(image);
        checkActive.setCycleCount();
    }
}