package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Mario.Mario;
import com.example.mario.blocks.Block;
import com.example.mario.blocks.KillBlock;
import com.example.mario.blocks.WinBlock;
import com.example.mario.enemies.Enemy;
import javafx.scene.image.Image;

import java.util.List;

public class BowserMovement {
    private MotionHandler motionHandler;
    private Mario mario;
    private Bowser bowser;
    private List<Block> blocks;

    public BowserMovement(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
        this.mario = motionHandler.getMario();
        this.bowser = motionHandler.bowserFounder();
        this.blocks = motionHandler.getBlocks();
    }

    public void bowserAllMove() {
        if(!bowser.isDead()) {
            if (!bowser.isJumping() && bowser.isBowserActive()) {
                horizontalMovement();
            }
            verticalMovement();
            bowserDirection();
        }
    }

    public void bowserDirection() {
        if (mario.getLayoutX() - bowser.getLayoutX() < 0)
            bowser.setBowerGoingLeft(true);
        else bowser.setBowerGoingLeft(false);
    }

    public void horizontalMovement() {
        if (mario.getLayoutX() + mario.getFitHeight() - bowser.getLayoutX() < 0 && Math.abs(mario.getLayoutX() - bowser.getLayoutX()) > 90) {
            bowser.setImage(new Image(getClass().getResource("/Images/enemies/bowser/bowserLeft.png").toExternalForm()));
            bowser.setLayoutX(bowser.getLayoutX() - 2);
        } else if (mario.getLayoutX() - bowser.getLayoutX() > 0 && Math.abs(mario.getLayoutX() - (bowser.getLayoutX() + bowser.getFitWidth())) > 90) {
            bowser.setImage(new Image(getClass().getResource("/Images/enemies/bowser/bowser.png").toExternalForm()));
            bowser.setLayoutX(bowser.getLayoutX() + 2);
        }
    }

    public void verticalMovement() {
        int random = (int) (Math.random() * 4);
        int gravity = 1;
        if (bowser.isDownCollusion()) {
            if (bowser.getFallVelocity() < 0 && motionHandler.getUsingAttacks().isUsingAnotherAttack())
                bowser.setImage(new Image(getClass().getResource("/Images/enemies/bowser/jumpAttack.png").toExternalForm()));
            if (random % 4 == 0) bowser.setFallVelocity(bowser.getFallVelocity() - gravity);
        }
        if (isUpCollision()) bowser.setFallVelocity(0);
        bowser.setLayoutY(bowser.getLayoutY() - bowser.getFallVelocity());
    }

    public boolean isUpCollision() {
        for (Block block : blocks) {
            if (bowser.getLayoutY() > block.getLayoutY() && bowser.getLayoutY() < block.getLayoutY() + block.getFitHeight()) {
                for (int j = (int) bowser.getLayoutX(); j <= bowser.getLayoutX() + bowser.getFitWidth(); j++) {
                    if (j > block.getLayoutX() && j < block.getLayoutX() + block.getFitWidth()) {
                        bowser.setLayoutY(block.getLayoutY() + block.getFitHeight());
                        return true;
                    }
                }
            }
        }
        return false;
    }

}

