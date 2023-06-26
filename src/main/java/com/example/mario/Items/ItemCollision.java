package com.example.mario.Items;

import com.example.mario.Mario.Mario;
import com.example.mario.controllers.GameLabelController;
import com.example.mario.user.GameData;

import java.util.ArrayList;

public class ItemCollision {
    private Mario mario;
    private ArrayList<Item> items;
    private GameData gameData = GameData.getInstance();
    private final GameLabelController gameLabelController = GameLabelController.getInstance();

    public ItemCollision(Mario mario,ArrayList<Item> items) {
        this.mario=mario;
        this.items=items;
    }
    public void coinCollision() {
        ArrayList<Item> removeItem = new ArrayList<>();
        a:
        for (Item item : items) {
            for (int i = (int) mario.getLayoutY(); i <= mario.getLayoutY() + mario.getFitHeight(); i++) {
                if (i >= item.getLayoutY() && i <= item.getLayoutY() + item.getFitHeight()) {
                    for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                        if (j >= item.getLayoutX() && j <= item.getLayoutX() + item.getFitWidth()) {
                            gameData.setCoin(gameData.getCoin() + 1);
                            gameLabelController.setCoinChange(gameData.getCoin());
                            gameData.setPoint(gameData.getPoint() + 10);
                            gameLabelController.setPointChange(gameData.getPoint());
                            removeItem.add(item);
                            item.setVisible(false);
                            continue a;
                        }
                    }
                }
            }
        }
        items.removeAll(removeItem);
    }
    public void isMushroomCollision() {
        ArrayList<Item> removeItem = new ArrayList<>();
        a:
        for (Item item : items) {
            for (int i = (int) mario.getLayoutY(); i <= mario.getLayoutY() + mario.getFitHeight(); i++) {
                if (i >= item.getLayoutY() && i <= item.getLayoutY() + item.getFitHeight()) {
                    for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                        if (j >= item.getLayoutX() && j <= item.getLayoutX() + item.getFitWidth()) {
                            gameData.setCoin(gameData.getCoin() + 1);
                            gameLabelController.setCoinChange(gameData.getCoin());
                            gameData.setPoint(gameData.getPoint() + 10);
                            gameLabelController.setPointChange(gameData.getPoint());
                            removeItem.add(item);
                            item.setVisible(false);
                            continue a;
                        }
                    }
                }
            }
        }
        items.removeAll(removeItem);
    }
}
