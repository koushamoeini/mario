package com.example.mario.blocks;

import javafx.scene.image.Image;

public class CoinBlock extends Block{
    private Image image;
    public CoinBlock(int edgeX, int edgeY, int blockX, int blockY) {
        super( edgeX, edgeY, blockX, blockY);
        image = new Image("Images/brick.png");
        this.setImage(image);
    }
}
