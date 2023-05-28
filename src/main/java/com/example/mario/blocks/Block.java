package com.example.mario.blocks;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Block extends ImageView {
    public enum Type {
        Mystery, Surface, Brick, Stair, Pipe, WinBlock, killBlock,
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
        if (type.equals(Type.Brick)) {
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
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
        this.setImage(image);
    }

    public Type getType() {
        return type;
    }
}
