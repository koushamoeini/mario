package com.example.mario.blocks;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BackGround extends ImageView {
    public BackGround(int edgeX, int edgeY, int blockX, int blockY, Image image) {
        this.setImage(image);
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
    }
}
