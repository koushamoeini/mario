package com.example.mario.blocks;

import javafx.scene.image.Image;

public class SurfaceBlock extends Block{
    public SurfaceBlock( int edgeX, int edgeY, int blockX, int blockY) {
        super( edgeX, edgeY, blockX, blockY);
        Image image = new Image("Images/block.png");
        this.setImage(image);
    }
}
