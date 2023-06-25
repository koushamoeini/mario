package com.example.mario.blocks;

import com.example.mario.Mario;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Block extends ImageView {
    public enum Type {
        Mystery, Surface, Brick, Stair, Pipe, WinBlock, killBlock,emptyBlock,coinBrick,superCoinBrick
    }

    private Image image;
    private int edgeX;
    private int edgeY;
    private int blockX;
    private int blockY;
    private final Type type;
    public Block(Type type, int edgeX, int edgeY, int blockX, int blockY) {
        this.edgeX = edgeX;
        this.edgeY = edgeY;
        this.blockX = blockX;
        this.blockY = blockY;
        this.type = type;
        if (type.equals(Type.Mystery)) {
            image = new Image("Images/prize_active.png");
        }
        if (type.equals(Type.Brick)||type.equals(Type.coinBrick)||type.equals(Type.superCoinBrick)) {
            image = new Image("Images/brick.png");
        }
        if (type.equals(Type.Surface)) {
            image = new Image("Images/block.png");
        }
        if (type.equals(Type.Stair)) {
            image = new Image("Images/stair.png");
        }
        if (type.equals(Type.Pipe)) {
            image = new Image("Images/pipe.png");
        }
        if(type.equals(Type.emptyBlock)){
            image = new Image("Images/emptyBlock.png");
        }
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
        this.setImage(image);
    }

    public Type getType() {
        return type;
    }

    public int getEdgeX() {
        return edgeX;
    }

    public void setEdgeX(int edgeX) {
        this.edgeX = edgeX;
    }

    public int getEdgeY() {
        return edgeY;
    }

    public void setEdgeY(int edgeY) {
        this.edgeY = edgeY;
    }

    public int getBlockX() {
        return blockX;
    }

    public void setBlockX(int blockX) {
        this.blockX = blockX;
    }

    public int getBlockY() {
        return blockY;
    }

    public void setBlockY(int blockY) {
        this.blockY = blockY;
    }

}
