package com.example.mario.levels;

import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Items.Coin;
import com.example.mario.Items.Item;
import com.example.mario.SuperMario;
import com.example.mario.blocks.*;
import com.example.mario.enemies.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Level1_4 {
    private Stage stage;

    public Level1_4(int state) throws Exception {
        setStage(SuperMario.getLevelStage());
        Pane pane = new Pane();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<BackGround> images = new ArrayList<>();
        List<Block> blocks = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();

        //addBackGround
        {
            BackGround backGround = new BackGround(1020, 600, 0, 0, new Image(getClass().getResource("/Images/backGrounds/back.jpg").toExternalForm()));
            pane.getChildren().add(backGround);
            BackGround cloud = new BackGround(170, 80, 200, 40, new Image(getClass().getResource("/Images/backGrounds/cloud2.png").toExternalForm()));
            pane.getChildren().add(cloud);
            images.add(cloud);
            BackGround cloud2 = new BackGround(160, 70, 620, -20, new Image(getClass().getResource("/Images/backGrounds/cloud1.png").toExternalForm()));
            pane.getChildren().add(cloud2);
            images.add(cloud2);
            BackGround cloud3 = new BackGround(160, 80, 990, 120, new Image(getClass().getResource("/Images/backGrounds/cloud3.png").toExternalForm()));
            pane.getChildren().add(cloud3);
            images.add(cloud3);
            BackGround cloud4 = new BackGround(170, 80, 1360, 60, new Image(getClass().getResource("/Images/backGrounds/cloud1.png").toExternalForm()));
            pane.getChildren().add(cloud4);
            images.add(cloud4);
            BackGround cloud5 = new BackGround(160, 70, 1610, -30, new Image(getClass().getResource("/Images/backGrounds/cloud2.png").toExternalForm()));
            pane.getChildren().add(cloud5);
            images.add(cloud5);
            BackGround cloud6 = new BackGround(160, 70, 1710, 110, new Image(getClass().getResource("/Images/backGrounds/cloud3.png").toExternalForm()));
            pane.getChildren().add(cloud6);
            images.add(cloud6);
            BackGround hill1 = new BackGround(400, 200, 100, 410, new Image(getClass().getResource("/Images/backGrounds/hill1.png").toExternalForm()));
            pane.getChildren().add(hill1);
            images.add(hill1);
            BackGround bush1 = new BackGround(130, 60, 1490, 470, new Image(getClass().getResource("/Images/backGrounds/bush1.png").toExternalForm()));
            pane.getChildren().add(bush1);
            images.add(bush1);
            BackGround bush2 = new BackGround(130, 60, 750, 470, new Image(getClass().getResource("/Images/backGrounds/bush1.png").toExternalForm()));
            pane.getChildren().add(bush2);
            images.add(bush2);
        }
        //addEnemies
        {
            Spiny koopa = new Spiny(30, 30, 420, 450);
            pane.getChildren().add(koopa);
            enemies.add(koopa);
        }
        //otherBlock
        {
            Stairs stair0 = new Stairs(30, 30, 480, 480);
            Stairs stair1 = new Stairs(30, 30, 510, 480);
            Stairs stair2 = new Stairs(30, 30, 510, 450);

            Pipe pipe1 = new Pipe(90, 150, 690, 360);
            pane.getChildren().add(stair0);
            blocks.add(stair0);
            pane.getChildren().add(stair1);
            blocks.add(stair1);
            pane.getChildren().add(stair2);
            blocks.add(stair2);
            pane.getChildren().add(pipe1);
            blocks.add(pipe1);
            for (int i = 310; i < 490; i += 30) {
                if (i == 310 || i == 340) {
                    MysteryBlock mysteryBlock = new MysteryBlock(30, 30, i, 270);
                    pane.getChildren().add(mysteryBlock);
                    blocks.add(mysteryBlock);
                } else {
                    Brick brick = new Brick(30, 30, i, 270);
                    pane.getChildren().add(brick);
                    blocks.add(brick);
                }
            }
            for (int i = 1800; i <= 1980; i += 30) {
                SuperCoinBlock brick = new SuperCoinBlock(30, 30, i, 390);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }

            MysteryBlock mysteryBlock1 = new MysteryBlock(30, 30, 1860, 240);
            pane.getChildren().add(mysteryBlock1);
            blocks.add(mysteryBlock1);
            MysteryBlock mysteryBlock2 = new MysteryBlock(30, 30, 1950, 240);
            pane.getChildren().add(mysteryBlock2);
            blocks.add(mysteryBlock2);
        }
        //addBlock
        {
            for (int i = 2130; i <= 2250; i += 30) {
                Brick stairs = new Brick(30, 30, i, 90);
                pane.getChildren().add(stairs);
                blocks.add(stairs);
            }
            WinBlock winBlock = new WinBlock(30, 90, 2250, 0);
            pane.getChildren().add(winBlock);
            blocks.add(winBlock);
            for (int j = 420; j < 510; j += 30) {
                for (int i = 2190; i <= 2250; i += 30) {
                    if (i - 2190 + j - 420 >= 60) {
                        Stairs stairs = new Stairs(30, 30, i, j);
                        pane.getChildren().add(stairs);
                        blocks.add(stairs);
                    }
                }
            }
            for (int i = 0; i <= 2300; i += 30) {
                if ((i >= 540 && i < 630) || (i >= 990 && i < 1110) || (i >= 1170 && i < 1260) || (i >= 1290 && i < 1350) || (i >= 1380 && i < 1440) || (i >= 1740 && i < 2100)) {
                    KillBlock killBlock = new KillBlock(30, 30, i, 600);
                    pane.getChildren().add(killBlock);
                    blocks.add(killBlock);
                }
            }
            for (int i = 510; i < 600; i += 30) {
                for (int j = 0; j <= 2300; j += 30) {
                    if (!(j >= 540 && j < 630) && !(j >= 990 && j < 1110) && !(j >= 1170 && j < 1260) && !(j >= 1290 && j < 1350) && !(j >= 1380 && j < 1440) && !(j >= 1740 && j < 2100)) {
                        SurfaceBlock surfaceBlock = new SurfaceBlock(30, 30, j, i);
                        pane.getChildren().add(surfaceBlock);
                        blocks.add(surfaceBlock);
                    }
                }
            }
            SurfaceBlock surfaceBlock = new SurfaceBlock(30, 30, 0, 480);
            pane.getChildren().add(surfaceBlock);
            blocks.add(surfaceBlock);
            //////////////////////////////////////////////////////////
            //addCoins
            {
                Coin coin1 = new Coin(30, 30, 1950, 150);
                pane.getChildren().add(coin1);
                items.add(coin1);
            }
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(new File("./src/main/resources/com/example/mario/GameLabels.fxml").toURI().toURL());
            Parent root = loader.load();
            root.setLayoutX(0);
            root.setLayoutY(0);
            pane.getChildren().add(root);
            
            // Add enemies after root so they appear on top
            for (Enemy enemy : enemies) {
                enemy.toFront();
            }
            
            Scene scene1 = new Scene(pane);
            stage.setScene(scene1);
            stage.show();
            @SuppressWarnings("unused") MotionHandler motionHandler = new MotionHandler(blocks, enemies, images, items, stage, pane, 4,state);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

