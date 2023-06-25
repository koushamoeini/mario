package com.example.mario.user;

public class GameData {
    private static GameData object;
    private int coin;
    private int hp;
    private int point;
    private int time;

    private GameData() {
        coin = 0;
        point=0;
        hp = 3;
        time=100;
    }
    public static GameData getInstance(){
        if (object == null){
            object = new GameData();
        }
        return object;
    }
    public static GameData resetInstance(){
        object = new GameData();
        return object;
    }

    public int getCoin() {
        return coin;
    }
    public void setCoin(int coin) {
        this.coin = coin;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }
}
