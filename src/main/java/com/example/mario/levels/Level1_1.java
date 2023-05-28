package com.example.mario.levels;

import com.example.mario.*;
import com.example.mario.blocks.Block;
import com.example.mario.enemies.Enemy;
import com.example.mario.enemies.Flower;
import com.example.mario.enemies.Spike;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
public class Level1_1 {
    private Stage stage;
    public Level1_1() throws IOException {
        setStage(SuperMario.getLevelStage());
        Pane pane = new Pane();
        ArrayList<Coin> coins = new ArrayList<>();
        ArrayList<BackGround> images = new ArrayList<>();
        ArrayList<Block> blocks = new ArrayList<>();
        ArrayList<Enemy> enemies = new ArrayList<>();
        addBackGround:
        while (true) {
            BackGround backGround = new BackGround(1020, 600, 0, 0, new Image("Images/back.jpg"));
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
            BackGround sign = new BackGround(100, 120, 4730, 410, new Image("Images/sign.png"));
            pane.getChildren().add(sign);
            images.add(sign);
            BackGround sign2 = new BackGround(120, 100, 5380, 470, new Image("Images/sign2.png"));
            pane.getChildren().add(sign2);
            images.add(sign2);
            break;
        }
        otherBlock:
        while (true) {
            Block stair0 = new Block(Block.Type.Stair, 30, 30, 480, 480);
            Block stair1 = new Block(Block.Type.Stair, 30, 30, 510, 480);
            Block stair2 = new Block(Block.Type.Stair, 30, 30, 510, 450);
            Flower enemyPipe = new Flower(50, 100, 710, 300);
            Block pipe = new Block(Block.Type.Pipe, 90, 150, 690, 360);
            Block pipe2 = new Block(Block.Type.Pipe, 60, 90, 2250, 420);
            pane.getChildren().add(stair0);
            blocks.add(stair0);
            pane.getChildren().add(stair1);
            blocks.add(stair1);
            pane.getChildren().add(stair2);
            blocks.add(stair2);
            pane.getChildren().add(enemyPipe);
            enemies.add(enemyPipe);
            pane.getChildren().add(pipe);
            blocks.add(pipe);
            pane.getChildren().add(pipe2);
            blocks.add(pipe2);
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
            for (int i = 180; i < 390; i += 30) {
                if (i == 240 || i == 360) {
                    Block mystery = new Block(Block.Type.Mystery, 30, 30, i, 370);
                    pane.getChildren().add(mystery);
                    blocks.add(mystery);

                } else {
                    Block brick = new Block(Block.Type.Brick, 30, 30, i, 370);
                    pane.getChildren().add(brick);
                    blocks.add(brick);
                }
            }
            for (int i = 310; i < 490; i += 30) {
                if (i == 310 || i == 340) {
                    Block mystery = new Block(Block.Type.Mystery, 30, 30, i, 220);
                    pane.getChildren().add(mystery);
                    blocks.add(mystery);
                } else {
                    Block brick = new Block(Block.Type.Brick, 30, 30, i, 220);
                    pane.getChildren().add(brick);
                    blocks.add(brick);
                }
            }
            for (int i = 1800; i <= 1980; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, i, 390);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }

            for (int i = 1800; i <= 1980; i += 30) {
                Block brick = new Block(Block.Type.Brick, 30, 30, i, 390);
                pane.getChildren().add(brick);
                blocks.add(brick);
            }
            Block mystery1 = new Block(Block.Type.Mystery, 30, 30, 1860, 240);
            pane.getChildren().add(mystery1);
            blocks.add(mystery1);
            Block mystery2 = new Block(Block.Type.Mystery, 30, 30, 1950, 240);
            pane.getChildren().add(mystery2);
            blocks.add(mystery2);
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
            for (int j = 420; j < 510; j += 30) {
                for (int i = 2160; i <= 2220; i += 30) {
                    if (i - 2160 + j - 420 >= 60) {
                        Block brick = new Block(Block.Type.Stair, 30, 30, i, j);
                        pane.getChildren().add(brick);
                        blocks.add(brick);
                    }
                }
            }
            for (int i = 2310; i <= 2370; i += 30) {
                for (int j = 420; j < 510; j += 30) {
                    if (i - 2310 <= j - 420) {
                        Block brick = new Block(Block.Type.Stair, 30, 30, i, j);
                        pane.getChildren().add(brick);
                        blocks.add(brick);
                    }
                }
            }
            for (int j = 360; j < 510; j += 30) {
                for (int i = 3270; i <= 3420; i += 30) {
                    if (i - 3270 + j - 360 >= 120) {
                        Block brick = new Block(Block.Type.Stair, 30, 30, i, j);
                        pane.getChildren().add(brick);
                        blocks.add(brick);
                    }
                }
            }
            for (int i = 3630; i <= 3720; i += 30) {
                for (int j = 420; j < 510; j += 30) {
                    if (i - 3630 <= j - 420) {
                        Block brick = new Block(Block.Type.Stair, 30, 30, i, j);
                        pane.getChildren().add(brick);
                        blocks.add(brick);
                    }
                }
            }
            for (int j = 210; j < 510; j += 30) {
                for (int i = 4190; i <= 4490; i += 30) {
                    if (i - 4190 + j - 210 >= 270) {
                        Block brick = new Block(Block.Type.Stair, 30, 30, i, j);
                        pane.getChildren().add(brick);
                        blocks.add(brick);
                    }
                }
            }
            for (int i = 0; i <= 5800; i += 30) {
                if ((i >= 540 && i < 630) || (i >= 990 && i < 1110) || (i >= 1170 && i < 1260) || (i >= 1290 && i < 1350) || (i >= 1380 && i < 1440) || (i >= 1740 && i < 2100) || (i >= 3450 && i < 3630)) {
                    Block killBlock = new Block(Block.Type.killBlock, 30, 30, i, 600);
                    pane.getChildren().add(killBlock);
                    blocks.add(killBlock);
                }
            }
            for (int i = 510; i < 600; i += 30) {
                for (int j = 0; j <= 5910; j += 30) {
                    if (!(j >= 540 && j < 630) && !(j >= 990 && j < 1110) && !(j >= 1170 && j < 1260) && !(j >= 1290 && j < 1350) && !(j >= 1380 && j < 1440) && !(j >= 1740 && j < 2100) && !(j >= 3450 && j < 3630) && !(j > 5370 && j < 5610)) {
                        Block block = new Block(Block.Type.Surface, 30, 30, j, i);
                        pane.getChildren().add(block);
                        blocks.add(block);
                    }
                }
            }
            //////////////////////////////////////////////////////////
            for (int i = 600; i < 1300; i += 30) {
                Block block = new Block(Block.Type.Stair, 30, 30, 5370, i);
                pane.getChildren().add(block);
                blocks.add(block);
            }
            for (int i = 600; i < 1320; i += 30) {
                Block block = new Block(Block.Type.Stair, 30, 30, 5610, i);
                pane.getChildren().add(block);
                blocks.add(block);
            }
            for (int i = 900; i < 1320; i += 30) {
                Spike spike = new Spike(30, 30,5400,i,true);
                pane.getChildren().add(spike);
                enemies.add(spike);
            }
            for (int i = 900; i < 1320; i += 30) {
                Spike spike = new Spike(30, 30,5580,i,false);
                pane.getChildren().add(spike);
                enemies.add(spike);
            }
            //////////////////////////////////////////////////////////
            for (int i = 1320; i < 1860; i += 30) {
                Block block = new Block(Block.Type.Stair, 30, 30, 5430, i);
                pane.getChildren().add(block);
                blocks.add(block);
            }
            for (int i = 1320; i < 1860; i += 30) {
                Block block = new Block(Block.Type.Stair, 30, 30, 5670, i);
                pane.getChildren().add(block);
                blocks.add(block);
            }
            for (int i = 1320; i <1860; i += 30) {
                Spike spike = new Spike(30, 30,5460,i,true);
                pane.getChildren().add(spike);
                enemies.add(spike);
            }
            for (int i = 1320; i <1860; i += 30) {
                Spike spike = new Spike(30, 30,5640,i,false);
                pane.getChildren().add(spike);
                enemies.add(spike);
            }
            //////////////////////////////////////////////////////////
            for (int i = 1860; i < 2520; i += 30) {
                Block block = new Block(Block.Type.Stair, 30, 30, 5340, i);
                pane.getChildren().add(block);
                blocks.add(block);
            }
            for (int i = 1860; i < 2520; i += 30) {
                Block block = new Block(Block.Type.Stair, 30, 30, 5580, i);
                pane.getChildren().add(block);
                blocks.add(block);
            }
            for (int i = 1860; i < 2520; i += 30) {
                Spike spike = new Spike(30, 30,5370,i,true);
                pane.getChildren().add(spike);
                enemies.add(spike);
            }
            for (int i = 1860; i < 2520; i += 30) {
                Spike spike = new Spike(30, 30,5550,i,false);
                pane.getChildren().add(spike);
                enemies.add(spike);
            }
            Block win=new Block(Block.Type.WinBlock,120,60,5400,2600);
            pane.getChildren().add(win);
            blocks.add(win);
            break;
        }
        addCoins:
        while (true) {
            Coin coin = new Coin(30, 30, 460, 190);
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
        motionHandler.setSection(1);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
