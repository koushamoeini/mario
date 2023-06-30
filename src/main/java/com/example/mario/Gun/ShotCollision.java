package com.example.mario.Gun;

import com.example.mario.blocks.Block;
import com.example.mario.controllers.GameLabelController;
import com.example.mario.enemies.Enemy;
import com.example.mario.enemies.EnemyCollision;
import com.example.mario.user.GameData;

import java.util.ArrayList;
import java.util.List;

public class ShotCollision {
    private final List<Block> blocks;
    private final List<Enemy> enemies;
    private final List<Gun> shots;
    private final EnemyCollision enemyCollision;
    private final GameData gameData = GameData.getInstance();
    private final GameLabelController gameLabelController = GameLabelController.getInstance();

    public ShotCollision(List<Block> blocks, List<Enemy> enemies, List<Gun> shots, EnemyCollision enemyCollision) {
        this.blocks = blocks;
        this.enemies = enemies;
        this.shots = shots;
        this.enemyCollision = enemyCollision;
    }

    public void checkCollision() {
        try {
            blockCollision();
            enemyCollision();
        } catch (Exception ignored) {
        }
    }

    public void blockCollision() {
        List<Gun> removeShot = new ArrayList<>();
        for (Block block : blocks) {
            for (Gun shot : shots) {
                if (block.getBoundsInParent().intersects(shot.getBoundsInParent())) {
                    shot.setVisible(false);
                    removeShot.add(shot);
                }
            }
        }
        shots.removeAll(removeShot);
    }

    public void enemyCollision() {
        List<Enemy> deadEnemies = new ArrayList<>();
        List<Gun> removeShot = new ArrayList<>();
        for (Enemy enemy : enemies) {
            for (Gun shot : shots) {
                if (enemy.getBoundsInParent().intersects(shot.getBoundsInParent())) {
                    shot.setVisible(false);
                    removeShot.add(shot);
                    if (shot instanceof Sword) {
                        if (enemyCollision.enemyDamaged(enemy, 3)) deadEnemies.add(enemy);
                    } else if (enemyCollision.enemyDamaged(enemy, 1)) deadEnemies.add(enemy);
                }
            }
        }
        shots.removeAll(removeShot);
        enemies.removeAll(deadEnemies);
    }

    public boolean usageCost() {
        if (gameData.getCoin() >= 3) {
            gameData.setCoin(gameData.getCoin() - 3);
            gameLabelController.setCoinChange(gameData.getCoin());
            return true;
        }
        return false;
    }
}
