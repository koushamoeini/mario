package com.example.mario.Items;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MagicFlower extends ImageView {
    public MagicFlower(int edgeX, int edgeY, int blockX, int blockY) {
        Image image=new Image("Images/coin1.png");
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
        this.setImage(image);
    }
}
