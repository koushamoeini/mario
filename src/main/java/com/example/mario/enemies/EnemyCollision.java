package com.example.mario.enemies;

import com.example.mario.Mario.Mario;
import com.example.mario.controllers.GameLabelController;
import com.example.mario.user.GameData;

import java.util.ArrayList;
import java.util.List;

public class EnemyCollision {
    private final List<Enemy> enemies;
    private final Mario mario;
    private final GameData gameData = GameData.getInstance();
    private final GameLabelController gameLabelController = GameLabelController.getInstance();
    public EnemyCollision(List<Enemy> enemies, Mario mario) {
        this.enemies = enemies;
        this.mario = mario;


    }

    public void enemyActivator() {
        for (Enemy enemy : enemies) {
            if ((Math.pow(Math.pow(mario.getLayoutY() - enemy.getLayoutY(), 2) + Math.pow(mario.getLayoutX() - enemy.getLayoutX(), 2), 0.5) < 420)) {
                enemy.setActive(true);
                if(enemy instanceof Spiny spiny) spiny.setSpinyActive(true);
            }
             else {
                if(enemy instanceof Spiny spiny) spiny.setSpinyActive(false);
                enemy.setActive(false);
            }
        }
    }
    public void spinyDirectionFinder(){
        for (Enemy enemy : enemies) {
            if (enemy instanceof Spiny spiny){
                if(mario.getLayoutX()-enemy.getLayoutX()<0) {
                    if(!spiny.isSpinyGoingLeft())spiny.setSpinyChangeDirection(true);
                    spiny.setSpinyGoingLeft(true);
                }
                else {
                    if(spiny.isSpinyGoingLeft())spiny.setSpinyChangeDirection(false);
                    spiny.setSpinyGoingLeft(false);
                }
            }
        }
    }



    public void isEnemyCollision() {
        enemyActivator();
        spinyDirectionFinder();
        isUpCollision();
        if (!mario.isInvincible()) {
            for (Enemy enemy : enemies) {
                if (enemy.getBoundsInParent().intersects(mario.getBoundsInParent())) {
                    if (mario.getMarioState() == 0) {
                        mario.setDead(true);
                        return;
                    }
                    mario.setMarioState(mario.getMarioState() - 1);
                    mario.doInvincible();
                    return;
                }
            }
        }
    }
    public void isUpCollision() {
        ArrayList<Enemy> deadEnemies = new ArrayList<>();
        for (Enemy enemy : enemies) {
            if (!(enemy instanceof Spiny)) {
                if (mario.getLayoutY() + mario.getFitHeight() > enemy.getLayoutY() && mario.getLayoutY() + mario.getFitHeight() < enemy.getLayoutY() + enemy.getFitHeight()) {
                    for (int j = (int) mario.getLayoutX(); j <= mario.getLayoutX() + mario.getFitWidth(); j++) {
                        if (j > enemy.getLayoutX() && j < enemy.getLayoutX() + enemy.getFitWidth()) {
                            if (enemy.isJumpDie()) {
                                if (enemyDamaged(enemy,1)) deadEnemies.add(enemy);
                            }
                        }
                    }
                }
            }
        }
        enemies.removeAll(deadEnemies);
    }

    public boolean enemyDamaged(Enemy enemy,int damage) {
        mario.doInvincible();
        if (!enemy.isInvincible()) {
            enemy.setEnemyHp(enemy.getEnemyHp() - damage);
            enemy.setInvincible(true);
            if (enemy instanceof Koopa) {
                ((Koopa) enemy).doAngry();
            }
            enemy.getInvincibleEnemy().play();
            if (enemy.getEnemyHp() <= 0) {
                enemy.setVisible(false);
                gameData.setCoin(gameData.getCoin() + 3);
                gameLabelController.setCoinChange(gameData.getCoin());
                gameData.setPoint(gameData.getPoint() + enemy.getEnemyScore());
                gameLabelController.setPointChange(gameData.getPoint());
                return true;
            }

        }
        return false;
    }
}

