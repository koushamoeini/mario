package com.example.mario.enemies.bossFight;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Mario.Mario;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.image.Image;
import javafx.util.Duration;

import java.io.IOException;

public class BowserAttack {
    private MotionHandler motionHandler;
    private Mario mario;
    private Bowser bowser;
    private Timeline grabStartTimer;
    private Timeline grabAttackTimer;
    private Timeline finishJumpFounderTimer;
    private Timeline nauseaTimer;
    private Timeline fireBallTimer;
    private Timeline fireBallAnimationTimer;
    private int useFireballAnimationCounter=0;
    private int useFireballCounter = 0;
    private int grabLeftCounter = 0;
    private int grabRightCounter = 0;

    public BowserAttack(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
        this.mario = motionHandler.getMario();
        this.bowser = motionHandler.bowserFounder();
        fireBallTimer = new Timeline(fireBallAttackKeyFrame);
        fireBallTimer.setCycleCount(Animation.INDEFINITE);
    }

    public void resetInstance() {
        motionHandler.getUsingAttacks().setUsingAnotherAttack(false);
        bowser.setImage(new Image("Images/enemies/bowser/bowserLeft.png"));
        mario.setInvincible(false);
        bowser.setInvincible(false);
    }

    //grabAttack:
    KeyFrame grabStartKeyFrame = new KeyFrame(Duration.millis(500), event -> {
        if (Math.abs(mario.getLayoutX() + mario.getFitWidth() / 2 - (bowser.getLayoutX() + bowser.getFitWidth() / 2)) < 110 && bowser.getLayoutY() - mario.getLayoutY() < 60) {
            bowser.setInvincible(true);
            mario.setInvincible(true);
            mario.setLayoutX(bowser.getLayoutX() + (bowser.getFitWidth() - mario.getFitWidth()) / 2);
            mario.setLayoutY(bowser.getLayoutY() + 50);
            mario.setCanMove(false);
            motionHandler.setGravity(0);
            grabRightCounter = 0;
            grabLeftCounter = 0;
            grabAttackTimer.play();
            grabStartTimer.stop();
        } else {
            resetInstance();
        }
        grabStartTimer.stop();
    });
    KeyFrame grabAttackKeyFrame = new KeyFrame(Duration.seconds(3), event -> {
        if (!(grabLeftCounter > 9 && grabRightCounter > 9)) {
            try {
                motionHandler.doDead();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            mario.setLayoutX(bowser.getLayoutX() - bowser.getFitWidth());
            mario.setLayoutY(bowser.getLayoutY() + bowser.getFitHeight() - mario.getFitHeight());
        }
        resetInstance();
        mario.setCanMove(true);
        motionHandler.setGravity(0.5);
        grabAttackTimer.stop();
    });

    public void grabAttack() {
        if (bowser.isBowerGoingLeft()) bowser.setImage(new Image("Images/enemies/bowser/grabAttackLeft.png"));
        else bowser.setImage(new Image("Images/enemies/bowser/grabAttack.png"));
        grabStartTimer = new Timeline(grabStartKeyFrame);
        grabStartTimer.setCycleCount(Animation.INDEFINITE);
        grabAttackTimer = new Timeline(grabAttackKeyFrame);
        grabAttackTimer.setCycleCount(Animation.INDEFINITE);
        grabStartTimer.play();
    }

    public void setGrabLeftCounter(int grabLeftCounter) {
        this.grabLeftCounter = grabLeftCounter;
    }

    public void setGrabRightCounter(int grabRightCounter) {
        this.grabRightCounter = grabRightCounter;
    }

    public int getGrabLeftCounter() {
        return grabLeftCounter;
    }

    public int getGrabRightCounter() {
        return grabRightCounter;
    }

    //jumpAttack
    public void jumpAttack() {
        bowser.setFallVelocity(10);
        bowser.setJumping(true);
        finishJumpFounderTimer = new Timeline(finishJumpFounderKeyFrame);
        finishJumpFounderTimer.setCycleCount(Animation.INDEFINITE);
        finishJumpFounderTimer.play();
    }

    KeyFrame nauseaKeyFrame = new KeyFrame(Duration.seconds(3), event -> {
        mario.setNausea(false);
        nauseaTimer.stop();
    });
    KeyFrame finishJumpFounderKeyFrame = new KeyFrame(Duration.millis(1), event -> {
        if (bowser.getFallVelocity() < 0 && !bowser.isDownCollusion()) {
            if (mario.getLayoutY() + 2 * mario.getFitHeight() > bowser.getLayoutY() + bowser.getFitHeight()) {
                mario.setNausea(true);
                nauseaTimer = new Timeline(nauseaKeyFrame);
                nauseaTimer.setCycleCount(Animation.INDEFINITE);
                nauseaTimer.play();
            }
            bowser.setFallVelocity(0);
            bowser.setImage(new Image("Images/enemies/bowser/bowserLeft.png"));
            bowser.setJumping(false);
            motionHandler.getUsingAttacks().setUsingAnotherAttack(false);
            finishJumpFounderTimer.stop();
        }
    });

    //FireBallAttack
    public void fireBallAttack() {
        fireBallAnimationTimer = new Timeline(fireBallAnimationKeyFrame);
        fireBallAnimationTimer.setCycleCount(Animation.INDEFINITE);
        useFireballCounter++;
        if (useFireballCounter % 2 == 1) fireBallTimer.play();
        BowserShot bowserShot;
        int rand1 = 30 * (int) (Math.random() * 3);
        if (bowser.isBowerGoingLeft()) {
            bowser.setImage(new Image("Images/enemies/bowser/fireballAttackLeft.png"));
            bowserShot = new BowserShot(60, 58, (int) (bowser.getLayoutX() - 60), (int) (bowser.getLayoutY() + rand1+1), motionHandler,true);
        } else {
            bowser.setImage(new Image("Images/enemies/bowser/fireballAttack.png"));
            bowserShot = new BowserShot(60, 58, (int) (bowser.getLayoutX() + bowser.getFitWidth()), (int) (bowser.getLayoutY() + rand1+1), motionHandler,false);
        }
        motionHandler.getPane().getChildren().add(bowserShot);
    }

    KeyFrame fireBallAttackKeyFrame = new KeyFrame(Duration.millis(500), event -> {
        fireBallAttack();
        motionHandler.getUsingAttacks().setUsingAnotherAttack(false);
        if (bowser.isBowerGoingLeft()) bowser.setImage(new Image("Images/enemies/bowser/bowserLeft.png"));
        else bowser.setImage(new Image("Images/enemies/bowser/bowser.png"));
        fireBallTimer.stop();
    });
    KeyFrame fireBallAnimationKeyFrame = new KeyFrame(Duration.millis(1), event -> {
        useFireballAnimationCounter++;
        if(bowser.isBowerGoingLeft()) bowser.setImage(new Image("Images/enemies/bowser/fireballAttackLeft.png"));
        if(!bowser.isBowerGoingLeft()) bowser.setImage(new Image("Images/enemies/bowser/fireballAttack.png"));
        if(useFireballCounter>=300) {
            useFireballAnimationCounter=0;
            fireBallAnimationTimer.stop();
        }
    });
}
