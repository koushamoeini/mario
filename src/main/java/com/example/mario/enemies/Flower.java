package com.example.mario.enemies;

import javafx.scene.image.Image;

public class Flower extends Enemy {
    private final int firstLayOutY;
    private double enemyVelocity=0.7;
    private int c=0;
    public Flower(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY);
        this.firstLayOutY=blockY;
        Image image = new Image("Images/flower(cagney).png");
        this.setImage(image);
    }
    public void cagneyController() {
        c++;
        if ((this.getLayoutY() >= firstLayOutY + this.getFitHeight() || this.getLayoutY() < firstLayOutY)&&c%5==0)
            enemyVelocity *= -1;
        this.setLayoutY(this.getLayoutY() + enemyVelocity);
    }
}
