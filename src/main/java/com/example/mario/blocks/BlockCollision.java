package com.example.mario.blocks;

import com.example.mario.Items.Coin;
import com.example.mario.Items.Item;
import com.example.mario.Items.MagicFlower;
import com.example.mario.Items.Mushroom;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class BlockCollision {
    private final Pane pane;
    private final ArrayList<Coin> coins;
    private final ArrayList<Item> items;


    public BlockCollision(Pane pane, ArrayList<Coin> coins, ArrayList<Item> items) {
        this.pane = pane;
        this.coins = coins;
        this.items = items;
    }

    public boolean brickBreak(Block block) {
        if (block instanceof Brick) {
            block.setVisible(false);
            return true;
        }
        return false;
    }

    public boolean allMethodRun(Block block) {
        mysteryBlockCollision(block);
        return superCoinBrickBreak(block) || coinBrickBreak(block) || brickBreak(block);
    }

    public boolean coinBrickBreak(Block block) {
        if (block instanceof CoinBlock) {
            Coin coin = new Coin(block.getEdgeX(), block.getEdgeY(), block.getBlockX(), block.getBlockY() - block.getEdgeY());
            block.setVisible(false);
            pane.getChildren().add(coin);
            coins.add(coin);
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
            coins.add(coin);
        }
        return false;
    }

    public void mysteryBlockCollision(Block block) {
        if (block instanceof MysteryBlock) {
            if (((MysteryBlock) block).isActive()) {
                ((MysteryBlock) block).setActive(false);
                block.setImage(new Image("Images/blocks/prize_inactive.png"));
                int randomNumber = (int) (Math.random() * 10) + 1;
                if (randomNumber < 6) {
                    MagicFlower magicFlower = new MagicFlower(block.getEdgeX(), block.getEdgeY(), (int) block.getLayoutX(), (int) (block.getLayoutY() - block.getEdgeY()));
                    pane.getChildren().add(magicFlower);
                    items.add(magicFlower);
                } else if (randomNumber < 10) {
                    Mushroom mushroom = new Mushroom(block.getEdgeX(), block.getEdgeY(), (int) block.getLayoutX(), (int) (block.getLayoutY() - block.getEdgeY()));
                    pane.getChildren().add(mushroom);
                    items.add(mushroom);
                }
            }
        }
    }
}
