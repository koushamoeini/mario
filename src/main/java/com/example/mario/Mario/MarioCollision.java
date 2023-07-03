package com.example.mario.Mario;

import com.example.mario.blocks.*;

import java.util.ArrayList;
import java.util.List;

public class MarioCollision {
    private boolean rightCollusion;
    private boolean leftCollusion;
    private boolean upCollusion;
    private boolean downCollusion;
    private final Mario mario;
    private final List<Block> blocks;
    private final BlockCollision blockCollision;
    public MarioCollision(Mario mario,List<Block> blocks,BlockCollision blockCollision) {
        this.mario=mario;
        this.blocks=blocks;
        this.blockCollision=blockCollision;
    }
    public void collision() {
        rightCollusion = false;
        leftCollusion = false;
        upCollusion = false;
        downCollusion = false;
        mario.setOnSecretPipe(false);
        List<Block> removeBlocks = new ArrayList<>();
        for (Block block : blocks) {
            if (!(block instanceof WinBlock)) {
                if (mario.getLayoutY() + mario.getFitHeight() >= block.getLayoutY() && mario.getLayoutY() + mario.getFitHeight() <= block.getLayoutY() + block.getFitHeight()) {
                    for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                        if (j > block.getLayoutX() && j < block.getLayoutX() + block.getFitWidth()) {
                            if(block instanceof SecretPipe) mario.setOnSecretPipe(true);
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
