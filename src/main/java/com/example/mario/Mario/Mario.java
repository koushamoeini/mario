package com.example.mario.Mario;

import com.example.mario.user.UserData;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mario extends ImageView {
    private boolean isDead=false;
    private int jumpVelocity=13;
    private boolean isInvincible=false;
    private boolean canBreakBlock=false;
    private boolean canShoot=false;
    private int marioHp=0;
    private boolean isSit=false;
    private String str;
    private final MarioStateManger marioStateManger;
    private final IntegerProperty marioState = new SimpleIntegerProperty(0);
    public Mario(int edgeX, int edgeY, int layoutX, int layoutY) {
        marioStateManger=new MarioStateManger(this);
        marioState.addListener((observable, oldValue, newValue) -> {
            if (newValue.equals(0))
                marioStateManger.backToMiniState();
            else if ((newValue.equals(1) && oldValue.equals(0))||(newValue.equals(1) && oldValue.equals(2)))
                marioStateManger.transformToMegaState();
            else if (newValue.equals(2))
                marioStateManger.transformToFireState();
        });
        setLayoutX(layoutX);
        setLayoutY(layoutY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
        UserData userData = UserData.getInstance();
        if(userData.getCurrentUser().isCurrentSkin0()) str="";
        else if (userData.getCurrentUser().isCurrentSkin1()) str="/cuphead";
        else if (userData.getCurrentUser().isCurrentSkin2()) str="/mugman";
        else if (userData.getCurrentUser().isCurrentSkin3()) str="/chalice";
        else str="/plane";
        Image marioImage = new Image("Images" + str + "/runner.png");
        this.setImage(marioImage);
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
    public void setMarioHp(int marioHp) {
        this.marioHp = marioHp;
    }
    public boolean isCanBreakBlock() {
        return canBreakBlock;
    }
    public void setCanBreakBlock(boolean canBreakBlock) {
        this.canBreakBlock = canBreakBlock;
    }
    public int getMarioHp() {
        return marioHp;
    }
    public void setMarioState(int marioState) {
        this.marioState.set(marioState);
    }

    public int getMarioState() {
        return marioState.get();
    }

    public IntegerProperty marioStateProperty() {
        return marioState;
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

    public void setStr(String str) {
        this.str = str;
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

}
