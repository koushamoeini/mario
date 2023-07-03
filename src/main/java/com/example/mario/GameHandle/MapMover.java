package com.example.mario.GameHandle;

import com.example.mario.Items.Item;
import com.example.mario.Mario.Mario;
import com.example.mario.SuperMario;
import com.example.mario.blocks.BackGround;
import com.example.mario.blocks.Block;
import com.example.mario.blocks.WinBlock;
import com.example.mario.enemies.Enemy;

import java.util.List;

public class MapMover {
    private List<Block> blocks;
    private List<Enemy> enemies;
    private List<Item> items;
    private Mario mario;
    private List<BackGround> backGrounds;
    private MotionHandler motionHandler;
    private int mapMoveCounter = 0;
    private int mapMoveDownCounter = 0;
    private boolean isMapMustMovingDown = false;
    private boolean isMapMoving = false;
    public MapMover(MotionHandler motionHandler) {
        this.blocks=motionHandler.getBlocks();
        this.enemies=motionHandler.getEnemies();
        this.items=motionHandler.getItems();
        this.backGrounds=motionHandler.getBackGrounds();
        this.mario=motionHandler.getMario();
        this.motionHandler = motionHandler;
    }
    public boolean isAllBlockMoveRight() {
        for (Block block : blocks) {
            if (block.getLayoutX() > SuperMario.getWidth())
                return false;
        }
        return true;
    }

    public boolean isAllBlockMoveDown() {
        for (Block block : blocks) {
            if (block.getLayoutY() > SuperMario.getHeight() && !(block instanceof WinBlock))
                return false;
        }
        return true;
    }

    public void mapRightController() {
        mapMoveCounter++;
        for (Block block : blocks) block.setLayoutX(block.getLayoutX() - 3);
        for (Enemy enemy : enemies) enemy.setLayoutX(enemy.getLayoutX() - 3);
        for (BackGround backGround : backGrounds) backGround.setLayoutX(backGround.getLayoutX() - 3);
        for (Item item : items) item.setLayoutX(item.getLayoutX() - 3);
    }

    public void mapDownController() {
        mapMoveDownCounter++;
        motionHandler.getMarioAnimation().falling();
        if (mario.getLayoutY() >= 210) mario.setLayoutY(mario.getLayoutY() - 10);
        for (Block block : blocks) block.setLayoutY(block.getLayoutY() - 4);
        for (BackGround backGround : backGrounds) backGround.setLayoutY(backGround.getLayoutY() - 4);
        for (Enemy enemy : enemies) enemy.setLayoutY(enemy.getLayoutY() - 4);
        for (Item item : items) item.setLayoutX(item.getLayoutX() - 4);

    }
    public void mapMoverRight(int num) {
        for (Block block : blocks) block.setLayoutX(block.getLayoutX() - num * 3);
        for (Enemy enemy : enemies) enemy.setLayoutX(enemy.getLayoutX() - num * 3);
        for (BackGround backGround : backGrounds) backGround.setLayoutX(backGround.getLayoutX() - num * 3);
        for (Item item : items) {
            item.setLayoutX(item.getLayoutX() - num * 3);
        }
    }

    public void mapMoverDown(int num) {
        for (Block block : blocks) block.setLayoutY(block.getLayoutY() - num * 5);
        for (Enemy enemy : enemies) enemy.setLayoutY(enemy.getLayoutY() - num * 5);
        for (BackGround backGround : backGrounds) backGround.setLayoutY(backGround.getLayoutY() - num * 5);
        for (Item item : items) item.setLayoutX(item.getLayoutX() - num * 5);
    }
    public void isMapMustMovingDownCheck() {
        if (mario.getLayoutY() > SuperMario.getHeight() + 30) {
            isMapMustMovingDown = true;
        }
    }

    public boolean isMapMustMovingDown() {
        return isMapMustMovingDown;
    }

    public int getMapMoveCounter() {
        return mapMoveCounter;
    }

    public int getMapMoveDownCounter() {
        return mapMoveDownCounter;
    }

    public boolean isMapMoving() {
        return isMapMoving;
    }

    public void setMapMoveCounter(int mapMoveCounter) {
        this.mapMoveCounter = mapMoveCounter;
    }

    public void setMapMoveDownCounter(int mapMoveDownCounter) {
        this.mapMoveDownCounter = mapMoveDownCounter;
    }

    public void setMapMustMovingDown(boolean mapMustMovingDown) {
        isMapMustMovingDown = mapMustMovingDown;
    }

    public void setMapMoving(boolean mapMoving) {
        isMapMoving = mapMoving;
    }
}
