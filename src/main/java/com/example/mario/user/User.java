package com.example.mario.user;

public class User {
    private String userName;
    private String password;
    private int Coins;
    private int point1=0;
    private int point2=0;
    private int point3=0;
    private final boolean skin0=true;
    private boolean skin1=false;
    private boolean skin2=false;
    private boolean skin3=false;
    private boolean skin4=false;
    private boolean currentSkin0=true;
    private boolean currentSkin1=false;
    private boolean currentSkin2=false;
    private boolean currentSkin3=false;
    private boolean currentSkin4=false;
    public User(){}
    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public int getCoins() {
        return Coins;
    }

    public void setCoins(int coins) {
        Coins = coins;
    }

    public int getPoint1() {
        return point1;
    }

    public int getPoint2() {
        return point2;
    }

    public int getPoint3() {
        return point3;
    }

    public void checkPoint(int num){
        if(num >point1){
            point3=point2;point2=point1;point1=num;
        }
        else if(num >point2){
            point3=point2;point2=num;
        }
        else if(num >point3){
            point3=num;
        }
    }
    public boolean isSkin0() {
        return skin0;
    }

    public boolean isSkin1() {
        return skin1;
    }

    public void setSkin1(boolean skin1) {
        this.skin1 = skin1;
    }

    public boolean isSkin2() {
        return skin2;
    }

    public void setSkin2(boolean skin2) {
        this.skin2 = skin2;
    }

    public boolean isSkin3() {
        return skin3;
    }

    public void setSkin3(boolean skin3) {
        this.skin3 = skin3;
    }

    public boolean isSkin4() {
        return skin4;
    }

    public void setSkin4(boolean skin4) {
        this.skin4 = skin4;
    }
    public boolean isCurrentSkin0() {
        return currentSkin0;
    }

    public void setCurrentSkin0(boolean currentSkin0) {
        this.currentSkin0 = currentSkin0;
    }

    public boolean isCurrentSkin1() {
        return currentSkin1;
    }

    public void setCurrentSkin1(boolean currentSkin1) {
        this.currentSkin1 = currentSkin1;
    }

    public boolean isCurrentSkin2() {
        return currentSkin2;
    }

    public void setCurrentSkin2(boolean currentSkin2) {
        this.currentSkin2 = currentSkin2;
    }

    public boolean isCurrentSkin3() {
        return currentSkin3;
    }

    public void setCurrentSkin3(boolean currentSkin3) {
        this.currentSkin3 = currentSkin3;
    }

    public boolean isCurrentSkin4() {
        return currentSkin4;
    }

    public void setCurrentSkin4(boolean currentSkin4) {
        this.currentSkin4 = currentSkin4;
    }
}
