package com.example.mario.blocks;

import javafx.scene.image.Image;

public class SuperCoinBlock extends Block{
    private Image image;
    public SuperCoinBlock( int edgeX, int edgeY, int blockX, int blockY) {
        super( edgeX, edgeY, blockX, blockY);
        image = new Image("Images/brick.png");
        this.setImage(image);
    }
}
