package com.example.mario.Gun;

import com.example.mario.blocks.Block;
import com.example.mario.enemies.Enemy;
import com.example.mario.enemies.EnemyCollision;

import java.util.ArrayList;
import java.util.List;

public class ShotCollision {
    private final List<Block> blocks;
    private final List<Enemy> enemies;
    private final List<Shot> shots;
    private final EnemyCollision enemyCollision;

    public ShotCollision(List<Block> blocks, List<Enemy> enemies, List<Shot> shots, EnemyCollision enemyCollision) {
        this.blocks = blocks;
        this.enemies = enemies;
        this.shots = shots;
        this.enemyCollision = enemyCollision;
    }

    public void checkCollision() {
        try {
            enemyCollision();
            blockCollision();
        } catch (Exception ignored) {
        }
    }

    public void blockCollision() {
        List<Shot> removeShot = new ArrayList<>();
        for (Block block : blocks) {
            for (Shot shot : shots) {
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
        List<Shot> removeShot = new ArrayList<>();
        for (Enemy enemy : enemies) {
            for (Shot shot : shots) {
                if (enemy.getBoundsInParent().intersects(shot.getBoundsInParent())) {
                    shot.setVisible(false);
                    removeShot.add(shot);
                    if(enemyCollision.enemyDamaged(enemy))
                    deadEnemies.add(enemy);
                }
            }
        }
        shots.removeAll(removeShot);
        enemies.removeAll(deadEnemies);
    }
}
