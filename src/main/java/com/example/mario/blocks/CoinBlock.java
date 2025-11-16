package com.example.mario.blocks;

import javafx.scene.image.Image;

public class CoinBlock extends Block{
    public CoinBlock(int edgeX, int edgeY, int blockX, int blockY) {
        super( edgeX, edgeY, blockX, blockY);
        Image image = new Image(getClass().getResource("/Images/blocks/brick.png").toExternalForm());
        this.setImage(image);
    }
}
