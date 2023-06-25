package com.example.mario.enemies;

import javafx.scene.image.ImageView;

public abstract class Enemy extends ImageView {
    public Enemy(int edgeX, int edgeY, int blockX, int blockY) {
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
    }
}
