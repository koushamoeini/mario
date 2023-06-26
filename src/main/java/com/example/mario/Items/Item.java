package com.example.mario.Items;

import com.example.mario.blocks.Block;
import com.example.mario.blocks.KillBlock;
import com.example.mario.blocks.WinBlock;
import javafx.scene.image.ImageView;

import java.util.ArrayList;

public class Item extends ImageView {
    private boolean upCollusion=false;
    private boolean downCollusion=false;
    private boolean goingLeft=false;
    private double gravity=1;
    private int fallVelocity=0;

    public Item(int edgeX, int edgeY, int blockX, int blockY) {
        setLayoutX(blockX);
        setLayoutY(blockY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
    }
    public void movement(int xVelocity,int yVelocity){
        xMovement(xVelocity);
        yMovement(yVelocity);
    }
    public void xMovement(int xVelocity) {
        if(goingLeft) xVelocity*=-1;
        this.setLayoutX(this.getLayoutX() + xVelocity);
    }

    public void yMovement(int yVelocity) {
        int random= (int) (Math.random()*4+1);
        if(!downCollusion) {
            if(random%4==0) fallVelocity+=gravity;
            this.setLayoutY(this.getLayoutY()+fallVelocity);
        }
    }
    public void itemCollision(ArrayList<Block> blocks) {
        downCollusion=false;
        upCollusion=false;
        for (Block block : blocks) {
            if(!(block instanceof WinBlock||block instanceof KillBlock)) {
                if (this.getLayoutY() + this.getFitHeight() >= block.getLayoutY() && this.getLayoutY() + this.getFitHeight() <= block.getLayoutY() + block.getFitHeight()) {
                    for (int j = (int) this.getLayoutX(); j <= this.getLayoutX() + this.getFitWidth(); j++) {
                        if (j >= block.getLayoutX() && j <= block.getLayoutX() + block.getFitWidth()) {
                            this.setLayoutY(block.getLayoutY() - this.getFitHeight());
                            downCollusion = true;
                            break;
                        }
                    }
                }
                if (this.getLayoutY() > block.getLayoutY() && this.getLayoutY() < block.getLayoutY() + block.getFitHeight()) {
                    for (int j = (int) this.getLayoutX(); j <= this.getLayoutX() + this.getFitWidth(); j++) {
                        if (j > block.getLayoutX() && j < block.getLayoutX() + block.getFitWidth()) {
                            this.setLayoutY(block.getLayoutY() + block.getFitHeight());
                            upCollusion = true;
                            break;
                        }
                    }
                }
                if (this.getLayoutX() + this.getFitWidth() > block.getLayoutX() - 2 && this.getLayoutX() + this.getFitWidth() < block.getLayoutX() + block.getFitWidth() + 2) {
                    for (int j = (int) this.getLayoutY(); j <= this.getLayoutY() + this.getFitHeight(); j++) {
                        if (j > block.getLayoutY() && j < block.getLayoutY() + block.getFitHeight()) {
                            goingLeft = true;
                            break;
                        }
                    }
                }
                if (this.getLayoutX() > block.getLayoutX() - 2 && this.getLayoutX() < block.getLayoutX() + block.getFitWidth() + 2) {
                    for (int j = (int) this.getLayoutY(); j <= this.getLayoutY() + this.getFitHeight(); j++) {
                        if (j > block.getLayoutY() && j < block.getLayoutY() + block.getFitHeight()) {
                            goingLeft = false;
                            break;
                        }
                    }
                }
            }
        }
    }
}
