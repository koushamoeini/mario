package com.example.mario.GameHandle;

import com.example.mario.controllers.GameLabelController;
import com.example.mario.manager.VoicePlayer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.util.Duration;

public class GameSound {
    private MotionHandler motionHandler;
    Timeline andBeginTime;
    private final VoicePlayer andBegin = new VoicePlayer("./src/main/resources/Media/and begin.mp3");
    private final VoicePlayer beppi = new VoicePlayer("./src/main/resources/Media/beppi.mp3");
    private final VoicePlayer marioDamage = new VoicePlayer("./src/main/resources/Media/takingDamage.mp3");
    private final VoicePlayer getItem = new VoicePlayer("./src/main/resources/Media/getItem.wav");
    private static GameSound instance;

    public GameSound() {
        instance = this;
    }

    public static GameSound getInstance(MotionHandler motionHandler) {
        if (instance == null)
            instance = new GameSound(motionHandler);
        return instance;
    }

    private final BooleanProperty muteListener = new SimpleBooleanProperty(true);

    public GameSound(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;

        muteListener.addListener((observable, oldValue, newValue) -> {

        });
        if (muteListener.getValue()) {
            andBegin.play();
            andBegin.setEndOfMedia(beppi);
        }
    }

    public void setMuteListener(boolean muteListener) {
        this.muteListener.set(muteListener);
    }

    public void marioDamageSound() {
        marioDamage.play();
    }

    public void marioGetItemSound() {
        getItem.play();
    }

    public void enemyDamageSound() {

    }
}