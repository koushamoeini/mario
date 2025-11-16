package com.example.mario.levels;

import com.example.mario.GameHandle.MotionHandler;
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

public class Level1_2 {
    private Stage stage;

    public Level1_2(int state) throws Exception {
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
            BackGround eye1 = new BackGround(60, 60, 3310, 310, new Image(getClass().getResource("/Images/backGrounds/white.png").toExternalForm()));
            pane.getChildren().add(eye1);
            images.add(eye1);
            BackGround eye2 = new BackGround(100, 100, 3070, 320, new Image(getClass().getResource("/Images/backGrounds/white.png").toExternalForm()));
            pane.getChildren().add(eye2);
            images.add(eye2);
            BackGround castle = new BackGround(400, 400, 3000, 200, new Image(getClass().getResource("/Images/backGrounds/castle.png").toExternalForm()));
            pane.getChildren().add(castle);
            images.add(castle);
        }
        //addEnemies
        {
            Spiny spiny1 = new Spiny(60, 60, -150, 450);
            pane.getChildren().add(spiny1);
            enemies.add(spiny1);
            Spiny spiny2 = new Spiny(60, 60, 1500, 450);
            pane.getChildren().add(spiny2);
            enemies.add(spiny2);
            Spiny spiny3 = new Spiny(60, 60, 3000, 450);
            pane.getChildren().add(spiny3);
            enemies.add(spiny3);
        }
        {
            WinBlock winBlock = new WinBlock(30, 90, 2250, 0);
            pane.getChildren().add(winBlock);
            blocks.add(winBlock);
            for (int j = -150; j <= 3300; j += 30) {
                for (int i = 510; i < 600; i += 30) {
                    SurfaceBlock surfaceBlock = new SurfaceBlock(30, 30, j, i);
                    pane.getChildren().add(surfaceBlock);
                    blocks.add(surfaceBlock);
                }
               if(j==450||j==900||j==1350||j==1800||j==2250||j==2700){
                   for(int i=330;i<510;i+=30){
                       Stairs stairs = new Stairs(30, 30, j, i);
                       pane.getChildren().add(stairs);
                       blocks.add(stairs);
                   }
               }
            }
            WinBlock win = new WinBlock(60, 150, 3120, 330);
            pane.getChildren().add(win);
            blocks.add(win);
            //////////////////////////////////////////////////////////
            //addCoins
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
            @SuppressWarnings("unused") MotionHandler motionHandler = new MotionHandler(blocks, enemies, images, items, stage, pane, 2,state);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}

