package com.example.mario.enemies;

import javafx.animation.KeyFrame;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class Spiny extends Enemy{
    public Spiny(int edgeX, int edgeY, int blockX, int blockY) {
        super(edgeX, edgeY, blockX, blockY,false,1,3,3);
        Image image = new Image("Images/enemies/spiny.png");
        this.setImage(image);
    }
    public void doAngry(){
        this.setxVelocity(0);
    }
    public void xMovement(int xVelocity) {
        if (this.isGoingLeft()) xVelocity *= -1;
        this.setLayoutX(this.getLayoutX() + xVelocity);
    }

    public void yMovement() {
        int random = (int) (Math.random() * 4 + 1);
        int gravity = 1;
        if (!this.isDownCollusion()) {
            if (random % 4 == 0) this.setFallVelocity(this.getFallVelocity()-gravity);
            this.setLayoutY(this.getLayoutY() - this.getFallVelocity());
        }
    }
    KeyFrame keyFrame0 = new KeyFrame(Duration.millis(20), event -> {
        movement(this.getxVelocity());
    });
    public void movement(int xVelocity) {
        if (this.isActive()) xMovement(xVelocity);
        yMovement();
    }
}
