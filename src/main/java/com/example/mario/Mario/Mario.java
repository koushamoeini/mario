package com.example.mario.Mario;

import com.example.mario.user.UserData;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class Mario extends ImageView {
    private boolean isDead = false;
    private int jumpVelocity = 15;
    private boolean isInvincible = false;
    private boolean canBreakBlock = false;
    private boolean canShoot = false;
    private boolean isSit = false;
    private boolean isGrabbing = false;
    private String str;
    private final MarioStateManger marioStateManger;
    private Timeline invincibleMario;
    private Timeline shotCoolDown;
    private Timeline swordCoolDown;
    private final BooleanProperty isSwordCoolDown = new SimpleBooleanProperty(false);
    private final BooleanProperty isShotCoolDown = new SimpleBooleanProperty(false);
    private final IntegerProperty marioState = new SimpleIntegerProperty(0);

    public Mario(int edgeX, int edgeY, int layoutX, int layoutY) {
        invincibleMario = new Timeline(invicibleKeyFrame);
        invincibleMario.setCycleCount(Animation.INDEFINITE);
        shotCoolDown = new Timeline(shotCoolDownKeyFrame);
        shotCoolDown.setCycleCount(Animation.INDEFINITE);
        swordCoolDown = new Timeline(swordCoolDownKeyFrame);
        swordCoolDown.setCycleCount(Animation.INDEFINITE);
        marioStateManger = new MarioStateManger(this);
        marioState.addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(0))
                marioStateManger.backToMiniState();
            else if ((newValue.equals(1) && oldValue.equals(0)) || (newValue.equals(1) && oldValue.equals(2)))
                marioStateManger.transformToMegaState();
            else if (newValue.equals(2))
                marioStateManger.transformToFireState();
        });
        isShotCoolDown.addListener((observable, oldValue, newValue) -> {
            if (newValue) shotCoolDown.play();
        });
        isSwordCoolDown.addListener((observable, oldValue, newValue) -> {
            if (newValue) swordCoolDown.play();
        });
        setLayoutX(layoutX);
        setLayoutY(layoutY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
        UserData userData = UserData.getInstance();
        if (userData.getCurrentUser().isCurrentSkin0()) str = "";
        else if (userData.getCurrentUser().isCurrentSkin1()) str = "/cuphead";
        else if (userData.getCurrentUser().isCurrentSkin2()) str = "/mugman";
        else if (userData.getCurrentUser().isCurrentSkin3()) str = "/chalice";
        else str = "/plane";
        Image marioImage = new Image("Images" + str + "/runner.png");
        this.setImage(marioImage);
    }

    KeyFrame invicibleKeyFrame = new KeyFrame(Duration.millis(500), event -> {
        this.setInvincible(false);
        invincibleMario.stop();
    });
    KeyFrame shotCoolDownKeyFrame = new KeyFrame(Duration.seconds(1), event -> {
        isShotCoolDown.set(false);
        shotCoolDown.stop();
    });
    KeyFrame swordCoolDownKeyFrame = new KeyFrame(Duration.seconds(3), event -> {
        isSwordCoolDown.set(false);
        swordCoolDown.stop();
    });

    public void doInvincible() {
        this.setInvincible(true);
        invincibleMario.play();
    }

    public boolean isDead() {
        return isDead;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public double getxVelocity() {
        return 3;
    }

    public int getJumpVelocity() {
        return jumpVelocity;
    }

    public void setJumpVelocity(int jumpVelocity) {
        this.jumpVelocity = jumpVelocity;
    }

    public boolean isCanBreakBlock() {
        return canBreakBlock;
    }

    public void setCanBreakBlock(boolean canBreakBlock) {
        this.canBreakBlock = canBreakBlock;
    }

    public void setMarioState(int marioState) {
        this.marioState.set(marioState);
    }

    public int getMarioState() {
        return marioState.get();
    }

    public boolean isCanShoot() {
        return canShoot;
    }

    public void setCanShoot(boolean canShoot) {
        this.canShoot = canShoot;
    }

    public String getStr() {
        return str;
    }

    public boolean isInvincible() {
        return isInvincible;
    }

    public void setInvincible(boolean invincible) {
        isInvincible = invincible;
    }

    public boolean isSit() {
        return isSit;
    }

    public void setSit(boolean sit) {
        isSit = sit;
    }

    public boolean isShotCoolDown() {
        return isShotCoolDown.get();
    }

    public void setIsShotCoolDown(boolean isShotCoolDown) {
        this.isShotCoolDown.set(isShotCoolDown);
    }

    public boolean isSwordCoolDown() {
        return isSwordCoolDown.get();
    }

    public void setIsSwordCoolDown(boolean isSwordCoolDown) {
        this.isSwordCoolDown.set(isSwordCoolDown);
    }

    public boolean isGrabbing() {
        return isGrabbing;
    }

    public void setGrabbing(boolean grabbing) {
        isGrabbing = grabbing;
    }
}
