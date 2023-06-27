package com.example.mario.enemies;

import com.example.mario.Mario.Mario;
import com.example.mario.controllers.GameLabelController;
import com.example.mario.user.GameData;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.ArrayList;

public class EnemyCollision {
    private final ArrayList<Enemy> enemies;
    private Mario mario;
    private Timeline invincibleMario;

    private final GameData gameData = GameData.getInstance();
    private final GameLabelController gameLabelController = GameLabelController.getInstance();

    public EnemyCollision(ArrayList<Enemy> enemies, Mario mario) {
        this.enemies = enemies;
        this.mario = mario;
        invincibleMario = new Timeline(invicibleKeyFrame);
        invincibleMario.setCycleCount(Animation.INDEFINITE);

    }

    public void enemyActivator() {
        for (Enemy enemy : enemies) {
            enemy.setActive(Math.pow(Math.pow(mario.getLayoutY() - enemy.getLayoutY(), 2) + Math.pow(mario.getLayoutX() - enemy.getLayoutX(), 2), 0.5) < 420);
        }
    }
    KeyFrame invicibleKeyFrame = new KeyFrame(Duration.seconds(1), event -> {
        mario.setInvincible(false);
        invincibleMario.stop();
    });


    public void isEnemyCollision() {
        enemyActivator();
        isUpCollision();
        if (!mario.isInvincible()) {
            for (Enemy enemy : enemies) {
                if (enemy.getBoundsInParent().intersects(mario.getBoundsInParent())) {
                    if (mario.getMarioState() == 0) {
                        mario.setDead(true);
                        return;
                    }
                    mario.setMarioState(mario.getMarioState() - 1);
                    mario.setInvincible(true);
                    invincibleMario.play();
                    return;
                }
            }
        }
    }

    public void isUpCollision() {
        ArrayList<Enemy> deadEnemies = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (mario.getLayoutY() + mario.getFitHeight() > enemy.getLayoutY() && mario.getLayoutY() + mario.getFitHeight() < enemy.getLayoutY() + enemy.getFitHeight()) {
                for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                    if (j > enemy.getLayoutX() && j < enemy.getLayoutX() + enemy.getFitWidth()) {
                        if (enemy.isJumpDie()) {
                           if(enemyDamaged(enemy))  deadEnemies.add(enemy);
                        }
                    }
                }
            }
        }
        enemies.removeAll(deadEnemies);
    }
    public boolean enemyDamaged(Enemy enemy){
        mario.setInvincible(true);
        invincibleMario.play();
        if(!enemy.isInvincible()) {
            enemy.setEnemyHp(enemy.getEnemyHp() - 1);
            enemy.setInvincible(true);
            if(enemy instanceof Koopa) {
                ((Koopa) enemy).doAngry();
            }
            enemy.getInvincibleEnemy().play();
            if (enemy.getEnemyHp() == 0) {
                enemy.setVisible(false);
                gameData.setPoint(gameData.getPoint() + enemy.getEnemyScore());
                gameLabelController.setPointChange(gameData.getPoint());
                return true;
            }

        }
        return false;
    }
}

