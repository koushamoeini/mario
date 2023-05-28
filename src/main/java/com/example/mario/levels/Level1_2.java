package com.example.mario.levels;

import com.example.mario.*;
import com.example.mario.blocks.BackGround;
import com.example.mario.blocks.Block;
import com.example.mario.blocks.Coin;
import com.example.mario.enemies.Enemy;
import com.example.mario.enemies.Flower;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class Level1_2 {
    private Stage stage;

    public Level1_2() throws IOException {
        setStage(SuperMario.getLevelStage());
        Pane pane = new Pane();
        ArrayList<Coin> coins = new ArrayList<>();
        ArrayList<BackGround> images = new ArrayList<>();
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();
        addBackGround:
        while (true) {
            BackGround backGround = new BackGround(1020, 600, 0, 0, new Image("Images/mahan.png"));
            pane.getChildren().add(backGround);
            BackGround cloud = new BackGround(170, 80, 200, 40, new Image("Images/cloud2.png"));
            pane.getChildren().add(cloud);
            images.add(cloud);
            BackGround cloud2 = new BackGround(160, 70, 620, -20, new Image("Images/cloud1.png"));
            pane.getChildren().add(cloud2);
            images.add(cloud2);
            BackGround cloud3 = new BackGround(160, 80, 990, 120, new Image("Images/cloud3.png"));
            pane.getChildren().add(cloud3);
            images.add(cloud3);
            BackGround cloud4 = new BackGround(170, 80, 1360, 60, new Image("Images/cloud1.png"));
            pane.getChildren().add(cloud4);
            images.add(cloud4);
            BackGround cloud5 = new BackGround(160, 70, 1610, -30, new Image("Images/cloud2.png"));
            pane.getChildren().add(cloud5);
            images.add(cloud5);
            BackGround cloud6 = new BackGround(160, 70, 1710, 110, new Image("Images/cloud3.png"));
            pane.getChildren().add(cloud6);
            images.add(cloud6);
            BackGround cloud7 = new BackGround(150, 70, 2200, 150, new Image("Images/cloud2.png"));
            pane.getChildren().add(cloud7);
            images.add(cloud7);
            BackGround cloud8 = new BackGround(170, 80, 2530, 40, new Image("Images/cloud2.png"));
            pane.getChildren().add(cloud8);
            images.add(cloud8);
            BackGround cloud9 = new BackGround(160, 70, 2950, -30, new Image("Images/cloud1.png"));
            pane.getChildren().add(cloud9);
            images.add(cloud9);
            BackGround cloud10 = new BackGround(160, 80, 3320, 120, new Image("Images/cloud3.png"));
            pane.getChildren().add(cloud10);
            images.add(cloud10);
            BackGround cloud11 = new BackGround(170, 80, 3690, 90, new Image("Images/cloud1.png"));
            pane.getChildren().add(cloud11);
            images.add(cloud11);
            BackGround cloud12 = new BackGround(160, 70, 3940, -40, new Image("Images/cloud2.png"));
            pane.getChildren().add(cloud12);
            images.add(cloud12);
            BackGround cloud13 = new BackGround(160, 70, 4040, 140, new Image("Images/cloud3.png"));
            pane.getChildren().add(cloud13);
            images.add(cloud13);
            BackGround cloud14 = new BackGround(150, 70, 4530, 80, new Image("Images/cloud2.png"));
            pane.getChildren().add(cloud14);
            images.add(cloud14);
            BackGround hill1 = new BackGround(400, 200, 100, 410, new Image("Images/hill1.png"));
            pane.getChildren().add(hill1);
            images.add(hill1);
            BackGround bush1 = new BackGround(130, 60, 1490, 470, new Image("Images/bush1.png"));
            pane.getChildren().add(bush1);
            images.add(bush1);
            BackGround bush2 = new BackGround(130, 60, 750, 470, new Image("Images/bush1.png"));
            pane.getChildren().add(bush2);
            images.add(bush2);
            BackGround hill2 = new BackGround(400, 200, 3060, 410, new Image("Images/hill1.png"));
            pane.getChildren().add(hill2);
            images.add(hill2);
            BackGround eye1=new BackGround(60,60,4210,310,new Image("Images/white.png"));
            pane.getChildren().add(eye1);
            images.add(eye1);
            BackGround eye2=new BackGround(100,100,3970,320,new Image("Images/white.png"));
            pane.getChildren().add(eye2);
            images.add(eye2);
            BackGround castle=new BackGround(400,400,3900,200,new Image("Images/castle.png"));
            pane.getChildren().add(castle);
            images.add(castle);
            break;
        }
        otherBlock:
        while (true) {
            Flower enemyPipe = new Flower(50, 100, 520, 30);
            Block pipe = new Block(Block.Type.Pipe, 90, 150, 500, 90);
            pane.getChildren().add(enemyPipe);
            enemies.add(enemyPipe);
            pane.getChildren().add(pipe);
            blocks.add(pipe);
            for (int i = 2760; i <= 3030; i += 90) {
                if (i != 2940) {
                    Flower enemyPipe3 = new Flower(50, 100, i + 20, 390 - (i - 2760) / 3);
                    pane.getChildren().add(enemyPipe3);
                    enemies.add(enemyPipe3);
                }
                Block pipe3 = new Block(Block.Type.Pipe, 90, 100 + (i - 2760) / 5, i, 450 - (i - 2760) / 3);
                pane.getChildren().add(pipe3);
                blocks.add(pipe3);
            }
            Block mystery = new Block(Block.Type.Mystery, 30, 30, 240, 370);
            pane.getChildren().add(mystery);
            blocks.add(mystery);
            /////////////////////////////////////////////////
            for (int i = 1800; i <= 1980; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, i, 360);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            for (int i = 1890; i <= 1980; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, i, 270);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            for (int i = 270; i <= 360; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, 1800, i);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            for (int i = 360; i <= 450; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, 1980, i);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            for (int i = 1770; i <= 1890; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, i, 450);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            for (int i = 300; i <= 450; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, 1890, i);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            /////////////////////////////////////////
            for (int i = 2160; i <= 2340; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, i, 300);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            for (int i = 2250; i <= 2340; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, i, 210);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            for (int i = 210; i <= 300; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, 2160, i);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            for (int i = 300; i <= 390; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, 2340, i);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            for (int i = 2160; i <= 2250; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, i, 390);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            for (int i = 240; i <= 390; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, 2250, i);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            /////////////////////////////////////////
            for (int i = 240; i <= 450; i += 30) {
                Block brick = new Block(Block.Type.Stair, 30, 30, 2430, i);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            for (int i = 2550; i < 2700; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, i, 390);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            for (int i = 2820; i <= 2970; i += 30) {
                Block brick = new Block(Block.Type.Mystery, 30, 30, i, 270);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            break;
        }
        addBlock:
        while (true) {
            for (int i = 300; i <= 600; i += 30) {
                for (int j = 180; j < 510; j += 30) {
                    if (i - 300 >= j - 180) {
                        Block brick = new Block(Block.Type.Stair, 30, 30, i, j);
                        pane.getChildren().add(brick);
                        blocks.add(brick);
                    }
                }
            }
            for (int i = 780; i <= 1080; i += 30) {
                for (int j = 180; j < 510; j += 30) {
                    if (i - 780 + j - 180 <= 300) {
                        Block brick = new Block(Block.Type.Stair, 30, 30, i, j);
                        pane.getChildren().add(brick);
                        blocks.add(brick);
                    }
                }
            }
            for (int i = 0; i <= 5800; i += 30) {
                if ((i >= 0 && i < 120) || (i >= 340 && i < 630) || (i >= 990 && i < 1110) || (i >= 1170 && i < 1260) || (i >= 1290 && i < 1350) || (i >= 1380 && i < 1440) || (i >= 1740 && i < 2100) || (i >= 3450 && i < 3690)) {
                    Block killBlock = new Block(Block.Type.killBlock, 30, 30, i, 600);
                    pane.getChildren().add(killBlock);
                    blocks.add(killBlock);
                }
            }
            for (int i = 510; i < 600; i += 30) {
                for (int j = 0; j <= 4200; j += 30) {
                    if (!(j >= 0 && j < 120) && !(j >= 340 && j < 630) && !(j >= 990 && j < 1110) && !(j >= 1170 && j < 1260) && !(j >= 1290 && j < 1350) && !(j >= 1380 && j < 1440) && !(j >= 1740 && j < 2100) && !(j >= 3450 && j < 3690)) {
                        Block block = new Block(Block.Type.Surface, 30, 30, j, i);
                        pane.getChildren().add(block);
                        blocks.add(block);
                    }
                }
            }
            for (int i = 660; i <= 3690; i += 30) {
                Block brick = new Block(Block.Type.Surface, 30, 30, i, -100);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            Block win=new Block(Block.Type.WinBlock,60,150,4020,330);
            pane.getChildren().add(win);
            blocks.add(win);
            break;
        }
        addCoins:
        while (true) {
            Coin coin = new Coin(30, 30, 460, 490);
            pane.getChildren().add(coin);
            coins.add(coin);
            Coin coin1 = new Coin(30, 30, 1950, 150);
            pane.getChildren().add(coin1);
            coins.add(coin1);
            Coin coin2 = new Coin(30, 30, 2970, 360);
            pane.getChildren().add(coin2);
            coins.add(coin2);
            break;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("GameLabels.fxml"));
        Parent root = loader.load();
        root.setLayoutX(0);
        root.setLayoutY(0);
        pane.getChildren().add(root);
        Scene scene1 = new Scene(pane);
        stage.setScene(scene1);
        stage.show();
        MotionHandler motionHandler = new MotionHandler(blocks, enemies, images, coins, stage, pane);
        motionHandler.setSection(2);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
