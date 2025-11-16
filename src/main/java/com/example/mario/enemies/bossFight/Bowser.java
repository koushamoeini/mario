package com.example.mario.enemies.bossFight;

import com.example.mario.blocks.Block;
import com.example.mario.blocks.Stairs;
import com.example.mario.blocks.WinBlock;
import com.example.mario.enemies.Enemy;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Bowser extends Enemy {
    private ProgressBar progressBar;
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
        setProgressBar();
        pane.getChildren().add(progressBar);
        Image image = new Image(getClass().getResource("/Images/enemies/bowser/bowserLeft.png").toExternalForm());
        this.setImage(image);
        this.blocks = blocks;
        Stairs stairs1 = new Stairs(30, 30, 0, 450);
        this.blocks.add(stairs1);
        bowserBlocks.add(stairs1);
        pane.getChildren().add(stairs1);
        Stairs stairs = new Stairs(30, 30, 60, 300);
        this.blocks.add(stairs);
        bowserBlocks.add(stairs);
        pane.getChildren().add(stairs);
        this.updateXVelocity(4);
        checkPhase = new Timeline(checkPhaseKeyFrame);
        checkPhase.setCycleCount(Animation.INDEFINITE);
        checkPhase.play();
        checkDead = new Timeline(checkDeadKeyFrame);
        checkDead.setCycleCount(Animation.INDEFINITE);
        checkDead.play();
    }
    KeyFrame checkDeadKeyFrame = new KeyFrame(Duration.millis(1), event -> {
        progressBar.setProgress((double) this.getEnemyHp()/20);
        if(this.getEnemyHp()<1) {
            this.isDead=true;
            blocks.add(new WinBlock(90,1000,900,0));
            checkDead.stop();
        }
    });
    KeyFrame checkPhaseKeyFrame = new KeyFrame(Duration.millis(1), event -> {
        if(this.getEnemyHp()<11) {
            phase = 2;
            checkPhase.stop();
        }
    });
    public void setProgressBar(){
        progressBar=new ProgressBar((double) this.getEnemyHp()/20);
        progressBar.setPrefSize(600,30);
        progressBar.setLayoutX(200);
        progressBar.setLayoutY(80);
    }
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

    public List<Block> getBowserBlocks() {
        return bowserBlocks;
    }
}
