package com.example.mario.enemies;

import javafx.scene.image.Image;

public class Flower extends Enemy {
    private final int firstLayOutY;
    private double enemyVelocity=1.1;
    public Flower(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY);
        this.firstLayOutY=blockY;
        Image image = new Image("Images/flower(cagney).png");
        this.setImage(image);
    }
    public void cagneyController() {
        if (this.getLayoutY() >= firstLayOutY + this.getFitHeight() || this.getLayoutY() < firstLayOutY)
            enemyVelocity *= -1;
        this.setLayoutY(this.getLayoutY() + enemyVelocity);
    }
}
