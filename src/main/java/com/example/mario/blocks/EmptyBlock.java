package com.example.mario.blocks;

import javafx.scene.image.Image;

public class EmptyBlock extends Block{
    public EmptyBlock( int edgeX, int edgeY, int blockX, int blockY) {
        super( edgeX, edgeY, blockX, blockY);
        Image image = new Image("Images/emptyBlock.png");
        this.setImage(image);
    }
}
