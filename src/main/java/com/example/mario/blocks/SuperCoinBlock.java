package com.example.mario.blocks;

import javafx.scene.image.Image;

public class SuperCoinBlock extends Block{
    private int prizeNumbers=(int) (Math.random() * 4) + 2;
    public SuperCoinBlock( int edgeX, int edgeY, int blockX, int blockY) {
        super( edgeX, edgeY, blockX, blockY);
        Image image = new Image("Images/brick.png");
        this.setImage(image);
    }

    public int getPrizeNumbers() {
        return prizeNumbers;
    }

    public void setPrizeNumbers(int prizeNumbers) {
        this.prizeNumbers = prizeNumbers;
    }
}
