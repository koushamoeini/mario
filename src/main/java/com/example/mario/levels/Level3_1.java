package com.example.mario.levels;

import com.example.mario.*;
import com.example.mario.GameHandle.MotionHandler;
import com.example.mario.Items.Item;
import com.example.mario.enemies.*;
import com.example.mario.blocks.*;
import com.example.mario.enemies.bossFight.Bowser;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;

public class Level3_1 {
    private Stage stage;

    public Level3_1(int state) throws Exception {
        setStage(SuperMario.getLevelStage());
        Pane pane = new Pane();
        ArrayList<Item> items = new ArrayList<>();
        ArrayList<BackGround> images = new ArrayList<>();
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();
        BackGround backGround = new BackGround(1020, 600, 0, 0, new Image(getClass().getResource("/Images/backGrounds/back.jpg").toExternalForm()));
        pane.getChildren().add(backGround);
        for (int i = 510; i < 600; i += 30) {
            for (int j = 0; j <= 990; j += 30) {
                SurfaceBlock surfaceBlock = new SurfaceBlock(30, 30, j, i);
                pane.getChildren().add(surfaceBlock);
                blocks.add(surfaceBlock);
            }
        }
        Bowser bowser=new Bowser(60,120,600,360,blocks,pane);
        pane.getChildren().add(bowser);
        enemies.add(bowser);
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
        new MotionHandler(blocks, enemies, images, items, stage, pane, 9, state);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
