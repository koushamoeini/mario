package com.example.mario.blocks;

import com.example.mario.Items.Coin;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BlockCollision {
    private final Pane pane;
    private final ArrayList<Coin> coins;


    public BlockCollision(Stage stage, Pane pane, ArrayList<Coin> coins) {
        this.pane = pane;
        this.coins = coins;
    }
    public boolean BrickBreak(Block block) {
        if (block instanceof Brick) {
            block.setVisible(false);
            return true;
        }
        return false;
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
            ((SuperCoinBlock) block).setPrizeNumbers(((SuperCoinBlock) block).getPrizeNumbers()-1);
            if(((SuperCoinBlock) block).getPrizeNumbers()<0) {
                block.setVisible(false);
                return true;
            }
            Coin coin = new Coin(block.getEdgeX(), block.getEdgeY(), block.getBlockX(), block.getBlockY() - block.getEdgeY());
            pane.getChildren().add(coin);
            coins.add(coin);

        }
        return false;
    }
}
