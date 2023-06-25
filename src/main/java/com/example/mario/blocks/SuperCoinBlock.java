package com.example.mario.blocks;

import javafx.scene.image.Image;

public class SuperCoinBlock extends Block{
    public SuperCoinBlock( int edgeX, int edgeY, int blockX, int blockY) {
        super( edgeX, edgeY, blockX, blockY);
        Image image = new Image("Images/brick.png");
        this.setImage(image);
    }
}
