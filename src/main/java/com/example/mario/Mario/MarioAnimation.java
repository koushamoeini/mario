package com.example.mario.Mario;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.SuperMario;
import javafx.scene.image.Image;

public class MarioAnimation {
    private final Mario mario;
    private boolean isMoving = false;
    private boolean isJumping = false;
    private int counter = 0;
    private int dyingCounter = 0;
    private double dyingVelocity = 15;
    private boolean isDyingFinished = false;
    private boolean isMarioMovingLeft = false;

    public MarioAnimation(MotionHandler motionHandler) {
        this.mario = motionHandler.getMario();
    }

    public void falling() {
        mario.setImage(new Image("Images" + mario.getStr() + "/marioFall.png"));
    }

    public void startMoving() {
        if (mario.isDead()) {
            marioDying();
        } else {
            if (isJumping) marioJumping();
            else if (isMoving) marioMoving();
        }
    }

    public void stopMoving() {
        mario.setImage(new Image("Images" + mario.getStr() + "/runner.png"));
    }

    public void marioMoving() {
        counter++;
        if (!isMarioMovingLeft) {
            if (counter % 15 < 5) {
                mario.setImage(new Image("Images" + mario.getStr() + "/run-1.png"));
            } else if (counter % 15 < 10) {
                mario.setImage(new Image("Images" + mario.getStr() + "/run-2.png"));
            } else
                mario.setImage(new Image("Images" + mario.getStr() + "/run-3.png"));
        } else {
            if (counter % 15 < 5) {
                mario.setImage(new Image("Images" + mario.getStr() + "/runLeft-1.png"));
            } else if (counter % 15 < 10) {
                mario.setImage(new Image("Images" + mario.getStr() + "/runLeft-2.png"));
            } else
                mario.setImage(new Image("Images" + mario.getStr() + "/runLeft-3.png"));
        }
    }

    public void marioJumping() {
        if (!isMarioMovingLeft) mario.setImage(new Image("Images" + mario.getStr() + "/jump.png"));
        else mario.setImage(new Image("Images" + mario.getStr() + "/jumpLeft.png"));
    }

    public void marioDying() {
        dyingCounter++;
        if (dyingCounter % 10 < 5)
            mario.setImage(new Image("Images" + mario.getStr() + "/marioDie.png"));
        else mario.setImage(new Image("Images/blocks/null.png"));
        if (dyingCounter == 1 && mario.getLayoutY() > SuperMario.getHeight()) {
            mario.setLayoutY(SuperMario.getHeight() - 60);
        }
        double gravity = 0.3;
        dyingVelocity -= gravity;
        mario.setLayoutY(mario.getLayoutY() - dyingVelocity);
        if (mario.getLayoutY() > SuperMario.getHeight() + 130) {
            isDyingFinished = true;
            mario.setImage(new Image("Images" + mario.getStr() + "/runner.png"));
            dyingCounter = 0;
            dyingVelocity = 15;
        }
    }

    public void marioSiting() {
        if (mario.isSit()) mario.setFitHeight(30);
        else mario.setFitHeight(60);
    }

    public boolean isMarioMovingLeft() {
        return isMarioMovingLeft;
    }

    public void setMarioMovingLeft(boolean marioMovingLeft) {
        isMarioMovingLeft = marioMovingLeft;
    }

    public boolean isDyingFinished() {
        return isDyingFinished;
    }

    public void setDyingFinished(boolean dyingFinished) {
        isDyingFinished = dyingFinished;
    }

    public void setMoving(boolean moving) {
        isMoving = moving;
    }

    public boolean isJumping() {
        return isJumping;
    }

    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }
}
