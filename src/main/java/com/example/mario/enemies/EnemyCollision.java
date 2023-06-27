package com.example.mario.enemies;

import com.example.mario.Mario.Mario;
import com.example.mario.blocks.Block;
import com.example.mario.blocks.KillBlock;
import com.example.mario.controllers.GameLabelController;
import com.example.mario.user.GameData;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class EnemyCollision {
    private final ArrayList<Enemy> enemies;
    private Mario mario;
    private final Timeline invincibleTimer;
    private GameData gameData = GameData.getInstance();
    private final GameLabelController gameLabelController = GameLabelController.getInstance();

    public EnemyCollision( ArrayList<Enemy> enemies, Mario mario) {
        this.enemies = enemies;
        this.mario = mario;
        invincibleTimer = new Timeline(invicibleKeyFrame);
        invincibleTimer.setCycleCount(Animation.INDEFINITE);
    }

    KeyFrame invicibleKeyFrame = new KeyFrame(Duration.seconds(2), event -> mario.setInvincible(false));

    public void isEnemyCollision() {
        isUpCollision();
        if (!mario.isInvincible()) {
            for (Enemy block : enemies) {
                for (int i = (int) mario.getLayoutY(); i <= mario.getLayoutY() + mario.getFitHeight(); i++) {
                    if (i >= block.getLayoutY() && i <= block.getLayoutY() + block.getFitHeight()) {
                        for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                            if (j >= block.getLayoutX() && j <= block.getLayoutX() + block.getFitWidth()) {
                                if (mario.getMarioState() == 0) {
                                    mario.setDead(true);
                                    return;
                                }
                                mario.setMarioState(mario.getMarioState() - 1);
                                mario.setInvincible(true);
                                invincibleTimer.play();
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public void isUpCollision() {
        ArrayList<Enemy> deadEnemies=new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (mario.getLayoutY() + mario.getFitHeight() >= enemy.getLayoutY() && mario.getLayoutY() + mario.getFitHeight() <= enemy.getLayoutY() + enemy.getFitHeight()) {
                for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                    if (j > enemy.getLayoutX() && j < enemy.getLayoutX() + enemy.getFitWidth()) {
                        if(enemy.isJumpDie()){
                            deadEnemies.add(enemy);
                            enemy.setVisible(false);
                            gameData.setPoint(gameData.getPoint() + enemy.getEnemyScore());
                            gameLabelController.setPointChange(gameData.getPoint());
                        }
                    }
                }
            }
        }
    }
}
