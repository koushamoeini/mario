package com.example.mario.Gun;

import com.example.mario.controllers.GameLabelController;
import com.example.mario.user.GameData;
import javafx.animation.Timeline;
import javafx.scene.image.Image;

public class Sword extends Gun{
    public Sword(int edgeX, int edgeY, int blockX, int blockY,boolean isLeft) {
        super(edgeX, edgeY, blockX, blockY, 6, 6, isLeft);
        Image image = new Image("Images/Shots/sword.png");
        this.setImage(image);
    }
}
