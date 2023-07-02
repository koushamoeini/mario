package com.example.mario.levels;

public class SetLevel {
    private int level;

    public SetLevel(int level) {
        this.level = level;
    }

    public boolean levelSet() {
        try {
            if (level == 1) new Level1_2();
            if (level == 2)new Level1_3();
                if (level == 3)
                    if (level == 4)
                        if (level == 5)
                            if (level == 6) ;
        } catch (Exception ignored) {
        }
        if (level < 3) return true;
        return false;
    }

    public void jsonLevelSet() {
        try {
            if (level == 1) new Level1_1();
            if (level == 2) new Level1_2();
            if (level == 3) new Level1_3();
            if (level == 4)
                if (level == 5)
                    if (level == 6) ;
        } catch (Exception ignored) {
        }
    }
}
