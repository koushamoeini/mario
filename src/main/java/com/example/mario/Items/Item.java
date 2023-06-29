package com.example.mario.Items;

import com.example.mario.blocks.Block;
import com.example.mario.blocks.KillBlock;
import com.example.mario.blocks.WinBlock;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.List;

public class Item extends ImageView {
    private boolean upCollusion = false;
    private boolean downCollusion = false;
    private int fallVelocity = 0;
    private int yVelocity;
    private int xVelocity;
    private final BooleanProperty changeDirection = new SimpleBooleanProperty(false);
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(2), event -> {
        if (downCollusion) fallVelocity = yVelocity;
    });

    public Item(int edgeX, int edgeY, int blockX, int blockY, int yVelocity, int xVelocity) {
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
        this.yVelocity = yVelocity;
        this.xVelocity = xVelocity;
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        changeDirection.addListener((observable, oldValue, newValue) -> changeDirection());
    }

    public void changeDirection() {
        xVelocity *= -1;
    }
    public void movement() {
        xMovement();
        yMovement();
    }

    public void xMovement() {
        this.setLayoutX(this.getLayoutX() + xVelocity);
    }

    public void yMovement() {
        int random = (int) (Math.random() * 4 + 1);
        int gravity = 1;
        if (!downCollusion) {if (random % 4 == 0) fallVelocity -= gravity;}
        else if (fallVelocity < 0) fallVelocity = 0;

        if (upCollusion) fallVelocity = 0;
        this.setLayoutY(this.getLayoutY() - fallVelocity);
    }

    public void itemCollision(List<Block> blocks) {
        downCollusion = false;
        upCollusion = false;
        for (Block block : blocks) {
            if (!(block instanceof WinBlock || block instanceof KillBlock)) {
                if (this.getLayoutY() + this.getFitHeight() >= block.getLayoutY() && this.getLayoutY() + this.getFitHeight() <= block.getLayoutY() + block.getFitHeight()) {
                    for (int j = (int) this.getLayoutX(); j <= this.getLayoutX() + this.getFitWidth(); j++) {
                        if (j > block.getLayoutX() && j < block.getLayoutX() + block.getFitWidth()) {
                            this.setLayoutY(block.getLayoutY() - this.getFitHeight());
                            downCollusion = true;
                            break;
                        }
                    }
                }
                if (this.getLayoutY() > block.getLayoutY() && this.getLayoutY() < block.getLayoutY() + block.getFitHeight()) {
                    for (int j = (int) this.getLayoutX(); j <= this.getLayoutX() + this.getFitWidth(); j++) {
                        if (j > block.getLayoutX() && j < block.getLayoutX() + block.getFitWidth()) {
                            this.setLayoutY(block.getLayoutY() + block.getFitHeight());
                            upCollusion = true;
                            break;
                        }
                    }
                }
                if (this.getLayoutX() + this.getFitWidth() > block.getLayoutX() - xVelocity && this.getLayoutX() + this.getFitWidth() < block.getLayoutX() + block.getFitWidth() + xVelocity) {
                    for (int j = (int) this.getLayoutY()+1; j <= this.getLayoutY() + this.getFitHeight()-1; j++) {
                        if (j > block.getLayoutY() && j < block.getLayoutY() + block.getFitHeight()) {
                            changeDirection.set(true);
                            break;
                        }
                    }
                }
                if (this.getLayoutX() > block.getLayoutX() + xVelocity && this.getLayoutX() < block.getLayoutX() + block.getFitWidth() - xVelocity) {
                    for (int j = (int) this.getLayoutY()+1; j <= this.getLayoutY() + this.getFitHeight()-1; j++) {
                        if (j > block.getLayoutY() && j < block.getLayoutY() + block.getFitHeight()) {
                            changeDirection.set(false);
                            break;
                        }
                    }
                }
            }
        }
    }
}
