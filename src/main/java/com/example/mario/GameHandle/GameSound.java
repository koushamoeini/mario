package com.example.mario.GameHandle;

import com.example.mario.manager.VoicePlayer;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class GameSound {
    private MotionHandler motionHandler;
    private boolean isMute;
    Timeline andBeginTime = new Timeline();
    private final VoicePlayer andBegin = new VoicePlayer("./src/main/resources/Media/and begin.mp3");
    private final VoicePlayer beppi = new VoicePlayer("./src/main/resources/Media/beppi.mp3");
    KeyFrame keyFrame = new KeyFrame(Duration.seconds(4), event -> {
        andBegin.stop();
        beppi.play();
    });
    public GameSound(MotionHandler motionHandler, boolean isMute) {
        this.motionHandler = motionHandler;
        this.isMute = isMute;
        andBeginTime.getKeyFrames().addAll(keyFrame);
        andBeginTime.setCycleCount(Animation.INDEFINITE);
        andBeginTime.play();
        andBegin.play();
        andBeginTime.stop();
        beppi.stop();
        andBeginTime.stop();
        andBegin.stop();
        beppi.stop();
        andBegin.stop();
        andBeginTime.stop();
        beppi.stop();
    }

}