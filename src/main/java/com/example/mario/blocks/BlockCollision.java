package com.example.mario.blocks;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class BlockCollision {
    private Stage stage;
    private Pane pane;
    private ArrayList<Coin> coins;
    private ArrayList<Block> blocks;

    public BlockCollision(Stage stage, Pane pane, ArrayList<Coin> coins, ArrayList<Block> blocks) {
        this.stage = stage;
        this.pane = pane;
        this.coins = coins;
        this.blocks = blocks;
    }
    public void coinBrickBreak(Block block){
        if(block.getType().equals(Block.Type.coinBrick)) {
            Coin coin = new Coin(block.getEdgeX(), block.getEdgeY(), block.getBlockX(), block.getBlockY() - block.getEdgeY());
            pane.getChildren().add(coin);
            coins.add(coin);
        }
    } public void superCoinBrickBreak(Block block){
        if(block.getType().equals(Block.Type.superCoinBrick)) {
            int max = 5;
            int min = 2;
            int range = max - min + 1;
            int rand = (int) (Math.random() * range) + min;
            for (int i = 0; i < rand; i++) {
                Coin coin =new Coin(block.getEdgeX(), block.getEdgeY(), block.getBlockX(), block.getBlockY() - block.getEdgeY());
                pane.getChildren().add(coin);
                coins.add(coin);
            }
        }
    }
}
