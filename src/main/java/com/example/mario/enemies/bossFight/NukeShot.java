package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Mario.Mario;
import com.example.mario.blocks.Block;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.List;

public class NukeShot extends ImageView {
    private final List<Block> blocks;
    private Mario mario;
    private Timeline timeline;
    private Bowser bowser;
    @SuppressWarnings("unused")
    private MotionHandler motionHandler;
    private Timeline checkCollision;
   // private boolean isActive=false;
    private final BooleanProperty isVisible = new SimpleBooleanProperty(true);

    public NukeShot(int edgeX, int edgeY, int blockX, int blockY, MotionHandler motionHandler) {
        this.setLayoutX(blockX);
        this.setLayoutY(blockY);
        this.setFitWidth(edgeX);
        this.setFitHeight(edgeY);
        this.motionHandler=motionHandler;
        this.setImage(new Image(getClass().getResource("/Images/Shots/nukeBomb.png").toExternalForm()));
        this.blocks = motionHandler.getBlocks();
        this.mario = motionHandler.getMario();
        this.bowser = motionHandler.bowserFounder();
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        checkCollision = new Timeline(checkCollisionKeyFrame);
        checkCollision.setCycleCount(Animation.INDEFINITE);
        checkCollision.play();
        isVisible.addListener((observable, oldValue, newValue) -> {
            if ((Math.pow(Math.pow(mario.getLayoutY()+mario.getFitHeight()/2 - (this.getLayoutY()+this.getFitHeight()/2), 2) + Math.pow(mario.getLayoutX()+mario.getFitWidth()/2 - (this.getLayoutX()+this.getFitWidth()/2), 2), 0.5) < 90)){
                if (mario.getMarioState() == 0) mario.setDead(true);
                else mario.setMarioState(mario.getMarioState() - 1);
            }
            if ((Math.pow(Math.pow(bowser.getLayoutY()+bowser.getFitHeight()/2 - (this.getLayoutY()+this.getFitHeight()/2), 2) + Math.pow(bowser.getLayoutX()+bowser.getFitWidth()/2 - (this.getLayoutX()+this.getFitWidth()/2), 2), 0.5) < 90)){
                bowser.setEnemyHp(bowser.getEnemyHp()-1);
            }
        });
    }

    KeyFrame keyFrame = new KeyFrame(Duration.millis(3), event -> {
        try {
            this.setLayoutY(this.getLayoutY() + 3);
        } catch (Exception ignored) {
        }
    });
    KeyFrame checkCollisionKeyFrame = new KeyFrame(Duration.millis(1), event -> {
        if (this.isVisible()) {
            try {
                collision();
            } catch (Exception ignored) {
            }
        }
        else checkCollision.stop();
    });
    //collisions:

    public void collision() {
        for (Block block : blocks) {
            if (block.getBoundsInParent().intersects(this.getBoundsInParent())) {
                this.setVisible(false);
                isVisible.set(false);
            }
        }
        if (this.getBoundsInParent().intersects(mario.getBoundsInParent())) {
            this.setVisible(false);
            isVisible.set(false);
        }
        if (this.getBoundsInParent().intersects(bowser.getBoundsInParent())) {
            this.setVisible(false);
            isVisible.set(false);
        }
    }
}
