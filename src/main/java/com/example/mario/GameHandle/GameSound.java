package com.example.mario.GameHandle;


import com.example.mario.manager.VoicePlayer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class GameSound {
    @SuppressWarnings("unused")
    private MotionHandler motionHandler;
    private final VoicePlayer andBegin = new VoicePlayer("./src/main/resources/Media/and begin.mp3");
    private final VoicePlayer beppi = new VoicePlayer("./src/main/resources/Media/beppi.mp3");
    private final VoicePlayer marioDamage = new VoicePlayer("./src/main/resources/Media/takingDamage.mp3");
    private final VoicePlayer enemyDamage = new VoicePlayer("./src/main/resources/Media/enemyDamage.mp3");
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

    public static void setInstance(GameSound instance) {
        GameSound.instance = instance;
    }

    private final BooleanProperty muteListener = new SimpleBooleanProperty(true);

    public GameSound(MotionHandler motionHandler) {
        this.motionHandler = motionHandler;
        muteListener.addListener((observable, oldValue, newValue) -> {
            if(newValue) beppi.play();
            else {
                andBegin.stop();
                beppi.pause();
            }
        });
        if (muteListener.getValue()) {
            andBegin.play();
            andBegin.setEndOfMedia(beppi);
        }
    }

    public void setMuteListener(boolean muteListener) {
        this.muteListener.set(muteListener);
    }

    public boolean isMuteListener() {
        return muteListener.get();
    }

    public void marioDamageSound() {
        if(this.muteListener.getValue()) {
            marioDamage.play();
            marioDamage.setEndOfMediaAndPause(marioDamage);
        }
    }

    public void marioGetItemSound() {
        if(this.muteListener.getValue()) {
            getItem.play();
            getItem.setEndOfMediaAndPause(getItem);
        }
    }

    public void enemyDamageSound() {
        if(this.muteListener.getValue()) {
            enemyDamage.play();
            enemyDamage.setEndOfMediaAndPause(enemyDamage);
        }
    }
}