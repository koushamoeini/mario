package com.example.mario.Gun;

import javafx.scene.image.Image;

public class Shot extends Gun {
    public Shot(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY);
        Image image = new Image("Images/enemies/flower(cagney).png");
        this.setImage(image);
    }
}
