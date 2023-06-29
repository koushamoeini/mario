package com.example.mario.Items;

import com.example.mario.Mario.Mario;
import com.example.mario.controllers.GameLabelController;
import com.example.mario.user.GameData;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class ItemCollision {
    private Mario mario;
    private final List<Item> items;
    private final GameData gameData = GameData.getInstance();
    private Timeline timeline;
    private final GameLabelController gameLabelController = GameLabelController.getInstance();

    public ItemCollision(Mario mario, List<Item> items) {
        this.mario = mario;
        this.items = items;
        timeline=new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }

    public void allCollision() {
        coinCollision();
        mushroomCollision();
        magicFlowerCollision();
        starCollision();
    }
    public void coinCollision() {
        ArrayList<Item> removeItem = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Coin) {
                if (item.getBoundsInParent().intersects(mario.getBoundsInParent())) {
                    gameData.setCoin(gameData.getCoin() + 1);
                    gameLabelController.setCoinChange(gameData.getCoin());
                    gameData.setPoint(gameData.getPoint() + 10);
                    gameLabelController.setPointChange(gameData.getPoint());
                    removeItem.add(item);
                    item.setVisible(false);
                }
            }
        }
        items.removeAll(removeItem);
    }

    public void magicFlowerCollision() {
        ArrayList<Item> removeItem = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof MagicFlower) {
                if (item.getBoundsInParent().intersects(mario.getBoundsInParent())) {
                    if(mario.getMarioState()<2) mario.setMarioState(mario.getMarioState()+1);
                    gameData.setPoint(gameData.getPoint() + 20);
                    gameLabelController.setPointChange(gameData.getPoint());
                    removeItem.add(item);
                    item.setVisible(false);
                }
            }
        }
        items.removeAll(removeItem);
    }
    public void mushroomCollision() {
        ArrayList<Item> removeItem = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Mushroom) {
                if (item.getBoundsInParent().intersects(mario.getBoundsInParent())) {
                    if(mario.getMarioState()<2) mario.setMarioState(mario.getMarioState()+1);
                    gameData.setPoint(gameData.getPoint() + 30);
                    gameLabelController.setPointChange(gameData.getPoint());
                    removeItem.add(item);
                    item.setVisible(false);
                }
            }
        }
        items.removeAll(removeItem);
    }
    public void starCollision() {
        ArrayList<Item> removeItem = new ArrayList<>();
        for (Item item : items) {
            if (item instanceof Star) {
                if (item.getBoundsInParent().intersects(mario.getBoundsInParent())) {
                    if(mario.getMarioState()<2) mario.setMarioState(mario.getMarioState()+1);
                    gameData.setPoint(gameData.getPoint() + 40);
                    gameLabelController.setPointChange(gameData.getPoint());
                    removeItem.add(item);
                    mario.setInvincible(true);
                    item.setVisible(false);
                    timeline.play();
                }
            }
        }
        items.removeAll(removeItem);
    }
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(5), event -> {
        mario.setInvincible(false);
        timeline.stop();
    });
}
