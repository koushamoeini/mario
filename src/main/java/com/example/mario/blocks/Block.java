package com.example.mario.blocks;
import javafx.scene.image.ImageView;


public class Block extends ImageView {

    private int edgeX;
    private int edgeY;
    private int blockX;
    private int blockY;

    public Block(int edgeX, int edgeY, int blockX, int blockY) {
        this.edgeX = edgeX;
        this.edgeY = edgeY;
        this.blockX = blockX;
        this.blockY = blockY;
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
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
