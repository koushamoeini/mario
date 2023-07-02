package com.example.mario.levels;

public class SetLevel {
    private int level;

    public SetLevel(int level) {
        this.level = level;
    }

    public boolean levelSet(int state) {
        try {
            if (level == 1) new Level1_2(state);
            if (level == 2) new Level1_3(state);
            if (level == 3) new Level1_4(state);
            if (level == 4) new Level1_5(state);
            if (level == 5) new Level2_1(state);
            if (level == 6) new Level2_2(state);
            if (level == 7) new Level2_3(state);
        } catch (Exception ignored) {
        }
        if (level < 8) return true;
        return false;
    }

    public void jsonLevelSet() {
        try {
            if (level == 1) new Level1_1();
            if (level == 2) new Level1_2(0);
            if (level == 3) new Level1_3(0);
            if (level == 4) new Level1_4(0);
            if (level == 5) new Level1_5(0);
            if (level == 6) new Level2_1(0);
            if (level == 7) new Level2_2(0);
            if (level == 8) new Level2_3(0);
        } catch (Exception ignored) {
        }
    }
}
