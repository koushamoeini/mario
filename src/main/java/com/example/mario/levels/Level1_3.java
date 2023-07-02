package com.example.mario.levels;

import com.example.mario.*;
import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Items.Item;
import com.example.mario.blocks.*;
import com.example.mario.enemies.Enemy;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;


public class Level1_3 {
    private Stage stage;

    public Level1_3() throws Exception {
        setStage(SuperMario.getLevelStage());
        Pane pane = new Pane();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<BackGround> images = new ArrayList<>();
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();
        //addBackGround
        {
            BackGround backGround = new BackGround(1020, 600, 0, 0, new Image("Images/backGrounds/back.jpg"));
            pane.getChildren().add(backGround);
            BackGround cloud = new BackGround(170, 80, 200, 40, new Image("Images/backGrounds/cloud2.png"));
            pane.getChildren().add(cloud);
            images.add(cloud);
            BackGround cloud2 = new BackGround(160, 70, 620, -20, new Image("Images/backGrounds/cloud1.png"));
            pane.getChildren().add(cloud2);
            images.add(cloud2);
            BackGround cloud3 = new BackGround(160, 80, 990, 120, new Image("Images/backGrounds/cloud3.png"));
            pane.getChildren().add(cloud3);
            images.add(cloud3);
            BackGround cloud4 = new BackGround(170, 80, 1360, 60, new Image("Images/backGrounds/cloud1.png"));
            pane.getChildren().add(cloud4);
            images.add(cloud4);
            BackGround cloud5 = new BackGround(160, 70, 1610, -30, new Image("Images/backGrounds/cloud2.png"));
            pane.getChildren().add(cloud5);
            images.add(cloud5);
            BackGround cloud6 = new BackGround(160, 70, 1710, 110, new Image("Images/backGrounds/cloud3.png"));
            pane.getChildren().add(cloud6);
            images.add(cloud6);
            BackGround cloud7 = new BackGround(150, 70, 2200, 150, new Image("Images/backGrounds/cloud2.png"));
            pane.getChildren().add(cloud7);
            images.add(cloud7);
            BackGround hill1 = new BackGround(400, 200, 100, 410, new Image("Images/backGrounds/hill1.png"));
            pane.getChildren().add(hill1);
            images.add(hill1);
            BackGround eye1 = new BackGround(60, 60, 2700, 310, new Image("Images/backGrounds/white.png"));
            pane.getChildren().add(eye1);
            images.add(eye1);
            BackGround eye2 = new BackGround(100, 100, 2460, 320, new Image("Images/backGrounds/white.png"));
            pane.getChildren().add(eye2);
            images.add(eye2);
            BackGround castle = new BackGround(400, 400, 2390, 200, new Image("Images/backGrounds/castle.png"));
            pane.getChildren().add(castle);
            images.add(castle);
        }
        //surface 3900
        {
            for (int i = 0; i <= 2760; i += 30) {
                if ((i >= 990 && i < 2040)) {
                    KillBlock killBlock = new KillBlock(30, 30, i, 600);
                    pane.getChildren().add(killBlock);
                    blocks.add(killBlock);
                }
            }
            for (int i = 510; i < 600; i += 30) {
                for (int j = 0; j <= 2760; j += 30) {
                    if (!(j >= 990 && j < 2040)) {
                        SurfaceBlock surfaceBlock = new SurfaceBlock(30, 30, j, i);
                        pane.getChildren().add(surfaceBlock);
                        blocks.add(surfaceBlock);
                    }
                }
            }
        }
        //brick&&mysteryBlocks
        {
            for (int i = 300; i < 450; i += 30) {
                if (i == 330 || i == 420) {
                    CoinBlock coinBlock = new CoinBlock(30, 30, i, 390);
                    pane.getChildren().add(coinBlock);
                    blocks.add(coinBlock);
                }
                else if (i == 390||i==300) {
                    MysteryBlock mysteryBlock = new MysteryBlock(30, 30, i, 390);
                    pane.getChildren().add(mysteryBlock);
                    blocks.add(mysteryBlock);
                }
                else {
                    Brick block = new Brick(30, 30, i, 390);
                    pane.getChildren().add(block);
                    blocks.add(block);
                }
            }
            for (int i = 450; i < 570; i += 30) {
                if (i == 450 || i == 510) {
                    SuperCoinBlock superCoinBlock = new SuperCoinBlock(30, 30, i, 240);
                    pane.getChildren().add(superCoinBlock);
                    blocks.add(superCoinBlock);
                }
                else{
                    MysteryBlock mysteryBlock = new MysteryBlock(30, 30, i, 240);
                    pane.getChildren().add(mysteryBlock);
                    blocks.add(mysteryBlock);
                }
            }
            Brick brick = new Brick(30, 30,1170 , 450);
            pane.getChildren().add(brick);
            blocks.add(brick);
            Brick brick2 = new Brick(30, 30,1380 , 390);
            pane.getChildren().add(brick2);
            blocks.add(brick2);
            Brick brick3 = new Brick(30, 30,1170 , 330);
            pane.getChildren().add(brick3);
            blocks.add(brick3);
            Brick brick4 = new Brick(30, 30,1380 , 270);
            pane.getChildren().add(brick4);
            blocks.add(brick4);
            Brick brick5 = new Brick(30, 30,1590 , 210);
            pane.getChildren().add(brick5);
            blocks.add(brick5);
            Brick brick6 = new Brick(30, 30,1830 , 210);
            pane.getChildren().add(brick6);
            blocks.add(brick6);
        }
        WinBlock win = new WinBlock(60, 150, 2510, 330);
        pane.getChildren().add(win);
        blocks.add(win);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/GameLabels.fxml").toURI().toURL());
        Parent root = loader.load();
        root.setLayoutX(0);
        root.setLayoutY(0);
        pane.getChildren().add(root);
        Scene scene1 = new Scene(pane);
        stage.setScene(scene1);
        stage.show();
        MotionHandler motionHandler = new MotionHandler(blocks, enemies, images, items, stage, pane, 3);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
