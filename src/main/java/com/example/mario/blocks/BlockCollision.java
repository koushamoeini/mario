package com.example.mario.blocks;

import com.example.mario.Items.*;
import com.example.mario.Mario.Mario;
import com.example.mario.controllers.GameLabelController;
import com.example.mario.user.GameData;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class BlockCollision {
    private final Pane pane;
    private final ArrayList<Item> items;
    private final Mario mario;
    private GameData gameData = GameData.getInstance();
    private final GameLabelController gameLabelController = GameLabelController.getInstance();

    public BlockCollision(Pane pane, ArrayList<Item> items,Mario mario) {
        this.pane = pane;
        this.items = items;
        this.mario=mario;
    }

    public boolean brickBreak(Block block) {
        if (block instanceof Brick) {
            block.setVisible(false);
            gameData.setPoint(gameData.getPoint() + 1);
            gameLabelController.setPointChange(gameData.getPoint());
            return true;
        }
        return false;
    }

    public boolean allMethodRun(Block block) {
        mysteryBlockCollision(block);
        if (mario.isCanBreakBlock()) {
            return superCoinBrickBreak(block) || coinBrickBreak(block) || brickBreak(block);
        }
        return false;
    }

    public boolean coinBrickBreak(Block block) {
        if (block instanceof CoinBlock) {
            Coin coin = new Coin(block.getEdgeX(), block.getEdgeY(), block.getBlockX(), block.getBlockY() - block.getEdgeY());
            block.setVisible(false);
            pane.getChildren().add(coin);
            items.add(coin);
            return true;
        }
        return false;
    }

    public boolean superCoinBrickBreak(Block block) {
        if (block instanceof SuperCoinBlock) {
            ((SuperCoinBlock) block).setPrizeNumbers(((SuperCoinBlock) block).getPrizeNumbers() - 1);
            if (((SuperCoinBlock) block).getPrizeNumbers() < 0) {
                block.setVisible(false);
                return true;
            }
            Coin coin = new Coin(block.getEdgeX(), block.getEdgeY(), (int) block.getLayoutX(), (int) (block.getLayoutY() - block.getEdgeY()));
            pane.getChildren().add(coin);
            items.add(coin);
        }
        return false;
    }

    public void mysteryBlockCollision(Block block) {
        if (block instanceof MysteryBlock) {
            if (((MysteryBlock) block).isActive()) {
                ((MysteryBlock) block).setActive(false);
                block.setImage(new Image("Images/blocks/prize_inactive.png"));
                int randomNumber = (int) (Math.random() * 10) + 1;
                if (randomNumber <5){
                    Coin coin = new Coin(block.getEdgeX(), block.getEdgeY(), (int) block.getLayoutX(), (int) (block.getLayoutY() - block.getEdgeY()));
                    pane.getChildren().add(coin);
                    items.add(coin);
                }
                else if (randomNumber < 8) {
                    MagicFlower magicFlower = new MagicFlower(block.getEdgeX(), block.getEdgeY(), (int) block.getLayoutX(), (int) (block.getLayoutY() - block.getEdgeY()));
                    pane.getChildren().add(magicFlower);
                    items.add(magicFlower);
                } else if (randomNumber < 10) {
                    Mushroom mushroom = new Mushroom(block.getEdgeX(), block.getEdgeY(), (int) block.getLayoutX(), (int) (block.getLayoutY() - block.getEdgeY()));
                    pane.getChildren().add(mushroom);
                    items.add(mushroom);
                }
                else {
                    Star star = new Star(block.getEdgeX(), block.getEdgeY(), (int) block.getLayoutX(), (int) (block.getLayoutY() - block.getEdgeY()));
                    pane.getChildren().add(star);
                    items.add(star);
                }
            }
        }
    }
}
