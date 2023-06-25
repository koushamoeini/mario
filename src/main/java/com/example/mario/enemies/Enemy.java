package com.example.mario.enemies;

import javafx.scene.image.ImageView;

public abstract class Enemy extends ImageView {
    private int edgeX;
    private int edgeY;
    private int blockX;
    private int blockY;
    public Enemy(int edgeX, int edgeY, int blockX, int blockY) {
        this.edgeX = edgeX;
        this.edgeY = edgeY;
        this.blockX = blockX;
        this.blockY = blockY;
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
    }
}
