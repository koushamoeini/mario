package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.blocks.Block;
import com.example.mario.blocks.MysteryBlock;
import com.example.mario.blocks.Stairs;
import com.example.mario.enemies.Enemy;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.List;

public class Bowser extends Enemy {
     List<Block> blocks;
     Pane pane;

    public Bowser(int edgeX, int edgeY, int blockX, int blockY, List<Block> blocks,Pane pane) {
        super(edgeX, edgeY, blockX, blockY, true, 20, 100, 0);
        Image image = new Image("Images/enemies/bowser/grabAttack.png");
        this.setImage(image);
        this.blocks=blocks;
        this.pane=pane;
        for(int i=blockX-210;i<blockX-150;i+=30){
            this.blocks.add(new Stairs(30,30,i,300));
            pane.getChildren().add(new Stairs(30,30,i,300));
        }
        this.blocks.add(new MysteryBlock(30,30,blockX-120,300));
        pane.getChildren().add(new MysteryBlock(30,30,blockX-120,300));
    }
}
