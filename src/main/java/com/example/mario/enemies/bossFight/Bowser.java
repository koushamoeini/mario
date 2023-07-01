package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.blocks.Block;
import com.example.mario.blocks.MysteryBlock;
import com.example.mario.blocks.Stairs;
import com.example.mario.enemies.Enemy;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Bowser extends Enemy {
    private List<Block> blocks;
    private List<Block> bowserBlocks = new ArrayList<>();
    private boolean bowerGoingLeft = true;
    private boolean isJumping = false;
    private boolean isBowserActive = false;
    private boolean isDead=false;
    private int phase = 1;
    private Timeline checkDead;
    private Timeline checkPhase;
    public Bowser(int edgeX, int edgeY, int blockX, int blockY, List<Block> blocks, Pane pane) {
        super(edgeX, edgeY, blockX, blockY, true, 20, 100, 0);
        Image image = new Image("Images/enemies/bowser/bowserLeft.png");
        this.setImage(image);
        this.blocks = blocks;
        for (int i = blockX - 210; i < blockX - 120; i += 30) {
            Stairs stairs = new Stairs(30, 30, i, 300);
            this.blocks.add(stairs);
            bowserBlocks.add(stairs);
            pane.getChildren().add(stairs);
        }
        MysteryBlock mysteryBlock = new MysteryBlock(30, 30, blockX - 120, 300);
        this.blocks.add(mysteryBlock);
        bowserBlocks.add(mysteryBlock);
        pane.getChildren().add(mysteryBlock);
        this.updateXVelocity(4);
        checkPhase = new Timeline(checkPhaseKeyFrame);
        checkPhase.setCycleCount(Animation.INDEFINITE);
        checkPhase.play();
        checkDead = new Timeline(checkDeadKeyFrame);
        checkDead.setCycleCount(Animation.INDEFINITE);
        checkDead.play();
    }
    KeyFrame checkDeadKeyFrame = new KeyFrame(Duration.millis(1), event -> {
        if(this.getEnemyHp()<1) {
            this.isDead=true;
            checkDead.stop();
        }
    });
    KeyFrame checkPhaseKeyFrame = new KeyFrame(Duration.millis(1), event -> {
        if(this.getEnemyHp()<11) {
            phase = 2;
            blocks.removeAll(bowserBlocks);
            for(Block block:bowserBlocks) block.setVisible(false);
            ColorAdjust colorAdjust = new ColorAdjust();
            colorAdjust.setHue(2);
            colorAdjust.setSaturation(2);
            colorAdjust.setContrast(1.0);
            this.setEffect(colorAdjust);
            checkPhase.stop();
        }
    });
    public boolean isBowerGoingLeft() {
        return bowerGoingLeft;
    }

    public void setBowerGoingLeft(boolean bowerGoingLeft) {
        this.bowerGoingLeft = bowerGoingLeft;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }

    public boolean isBowserActive() {
        return isBowserActive;
    }

    public void setBowserActive(boolean bowserActive) {
        isBowserActive = bowserActive;
    }

    public int getPhase() {
        return phase;
    }

    public boolean isDead() {
        return isDead;
    }
}
