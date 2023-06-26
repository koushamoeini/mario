package com.example.mario.Mario;

import com.example.mario.blocks.Block;
import com.example.mario.blocks.BlockCollision;
import com.example.mario.blocks.KillBlock;

import java.util.ArrayList;

public class MarioCollision {
    private boolean rightCollusion;
    private boolean leftCollusion;
    private boolean upCollusion;
    private boolean downCollusion;
    private Mario mario;
    private ArrayList<Block> blocks;
    private BlockCollision blockCollision;
    public MarioCollision(Mario mario,ArrayList<Block> blocks,BlockCollision blockCollision) {
        this.mario=mario;
        this.blocks=blocks;
        this.blockCollision=blockCollision;
    }
    public void isCollision() {
        rightCollusion = false;
        leftCollusion = false;
        upCollusion = false;
        downCollusion = false;
        ArrayList<Block> removeBlocks = new ArrayList<>();
        for (Block block : blocks) {
            if (mario.getLayoutY() + mario.getFitHeight() >= block.getLayoutY() && mario.getLayoutY() + mario.getFitHeight() <= block.getLayoutY() + block.getFitHeight()) {
                for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                    if (j > block.getLayoutX() && j < block.getLayoutX() + block.getFitWidth()) {
                        if (block instanceof KillBlock) {
                            mario.setDead(true);
                        } else {
                            mario.setLayoutY(block.getLayoutY() - mario.getFitHeight());
                            downCollusion = true;
                            break;
                        }
                    }
                }
            }
            if (mario.getLayoutY() > block.getLayoutY() && mario.getLayoutY() < block.getLayoutY() + block.getFitHeight()) {
                for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                    if (j > block.getLayoutX() && j < block.getLayoutX() + block.getFitWidth()) {
                        if (block instanceof KillBlock) {
                            mario.setDead(true);
                        } else {
                            if (blockCollision.allMethodRun(block)) removeBlocks.add(block);
                            mario.setLayoutY(block.getLayoutY() + block.getFitHeight());
                            upCollusion = true;
                            break;
                        }
                    }
                }
            }
            if (mario.getLayoutX() + mario.getFitWidth() > block.getLayoutX() - 5 && mario.getLayoutX() + mario.getFitWidth() < block.getLayoutX() + block.getFitWidth() + 5) {
                for (int j = (int) mario.getLayoutY(); j <= mario.getLayoutY() + mario.getFitHeight(); j++) {
                    if (j > block.getLayoutY() && j < block.getLayoutY() + block.getFitHeight()) {
                        if (block instanceof KillBlock) {
                            mario.setDead(true);
                        } else {
                            rightCollusion = true;
                            break;
                        }
                    }
                }
            }
            if (mario.getLayoutX() > block.getLayoutX() - 5 && mario.getLayoutX() < block.getLayoutX() + block.getFitWidth() + 5) {
                for (int j = (int) mario.getLayoutY(); j <= mario.getLayoutY() + mario.getFitHeight(); j++) {
                    if (j > block.getLayoutY() && j < block.getLayoutY() + block.getFitHeight()) {
                        if (block instanceof KillBlock) {
                            mario.setDead(true);
                        } else {
                            leftCollusion = true;
                            break;
                        }
                    }
                }
            }
        }
        blocks.removeAll(removeBlocks);
    }

    public boolean isRightCollusion() {
        return rightCollusion;
    }

    public boolean isLeftCollusion() {
        return leftCollusion;
    }

    public boolean isUpCollusion() {
        return upCollusion;
    }

    public boolean isDownCollusion() {
        return downCollusion;
    }
}
