package com.example.mario.blocks;

import javafx.scene.image.Image;

public class MysteryBlock extends Block{
    private Image image;
    public MysteryBlock( int edgeX, int edgeY, int blockX, int blockY) {
        super( edgeX, edgeY, blockX, blockY);
        this.image = new Image("Images/prize_active.png");
        this.setImage(image);
    }
}
