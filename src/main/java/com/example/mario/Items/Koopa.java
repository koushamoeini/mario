package com.example.mario.Items;

import com.example.mario.enemies.Enemy;
import javafx.scene.image.Image;

public class Koopa extends Enemy {
    public Koopa(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY,true,1,1,2);
        Image image = new Image("Images/enemies/goompa.png");
        this.setImage(image);
    }
}
