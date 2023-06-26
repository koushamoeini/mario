package com.example.mario.Mario;

import com.example.mario.SuperMario;
import com.example.mario.user.UserData;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Mario extends ImageView {
    private double xVelocity=3;
    private boolean isJumping=false;
    private boolean isMoving=false;
    private boolean isDead=false;
    private Image marioImage;
    private int counter=0;
    private  double dyingVelocity=15;
    private double gravity=0.3;
    private int dyingCounter=0;
    private boolean isDyingFinished=false;
    private boolean isMarioMovingLeft=false;
    private UserData userData = UserData.getInstance();
    private String str;
    private int edgeX;
    private int edgeY;
    private int blockX;
    private int blockY;
    public Mario(int edgeX, int edgeY, int layoutX, int layoutY) {
        this.edgeX = edgeX;
        this.edgeY = edgeY;
        this.blockX = layoutX;
        this.blockY = layoutY;
        setLayoutX(layoutX);
        setLayoutY(layoutY);
        setFitWidth(edgeX);
        setFitHeight(edgeY);
        if(userData.getCurrentUser().isCurrentSkin0()) str="";
        else if (userData.getCurrentUser().isCurrentSkin1()) str="/cuphead";
        else if (userData.getCurrentUser().isCurrentSkin2()) str="/mugman";
        else if (userData.getCurrentUser().isCurrentSkin3()) str="/chalice";
        else str="/plane";
        marioImage = new Image("Images"+str+"/runner.png");
        this.setImage(marioImage);
    }
    public void startMoving(){
        if(isDead){
            marioDying();
        }
        else if(isJumping){
            marioJumping();
        }
        else if(isMoving){
            marioMoving();
        }
    }
    public void stopMoving(){
        this.setImage(new Image("Images"+str+"/runner.png"));
    }
    public void marioMoving() {
        counter++;
        if(!this.isMarioMovingLeft) {
            if (counter % 15 < 5) {
                this.setImage(new Image("Images"+str+"/run-1.png"));
            } else if (counter % 15 < 10) {
                this.setImage(new Image("Images"+str+"/run-2.png"));
            } else
                this.setImage(new Image("Images"+str+"/run-3.png"));
        }
        else {
            if (counter % 15 < 5) {
                this.setImage(new Image("Images"+str+"/runLeft-1.png"));
            } else if (counter % 15 < 10) {
                this.setImage(new Image("Images"+str+"/runLeft-2.png"));
            } else
                this.setImage(new Image("Images"+str+"/runLeft-3.png"));
        }
    }
    public void marioJumping(){
        if(!this.isMarioMovingLeft) this.setImage(new Image("Images"+str+"/jump.png"));
        else this.setImage(new Image("Images"+str+"/jumpLeft.png"));
    }
    public void marioDying(){
        dyingCounter++;
        if(dyingCounter%10<5)
        this.setImage(new Image("Images"+str+"/marioDie.png"));
        else this.setImage(new Image("Images/blocks/null.png"));
        if(dyingCounter==1&&this.getLayoutY()> SuperMario.getHeight()){
            this.setLayoutY(SuperMario.getHeight()-60);
        }
        dyingVelocity -= gravity;
        this.setLayoutY(this.getLayoutY() - dyingVelocity);
        if(this.getLayoutY()>SuperMario.getHeight()+130) {
            isDyingFinished = true;
            this.setImage(new Image("Images"+str+"/runner.png"));
            dyingCounter=0;
            dyingVelocity=15;
        }
    }
    public boolean isJumping() {
        return isJumping;
    }
    public void falling(){
        this.setImage(new Image("Images"+str+"/marioFall.png"));
    }
    public void setJumping(boolean jumping) {
        isJumping = jumping;
    }
    public void setMoving(boolean moving) {
        isMoving = moving;
    }
    public boolean isDead() {
        return isDead;
    }
    public void setDead(boolean dead) {
        isDead = dead;
    }
    public boolean isDyingFinished() {
        return isDyingFinished;
    }
    public void setDyingFinished(boolean dyingFinished) {
        isDyingFinished = dyingFinished;
    }

    public void setMarioMovingLeft(boolean marioMovingLeft) {
        isMarioMovingLeft = marioMovingLeft;
    }

    public double getxVelocity() {
        return xVelocity;
    }
}
