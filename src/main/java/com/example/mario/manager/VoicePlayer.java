package com.example.mario.manager;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

public class VoicePlayer {
    private final MediaPlayer mediaPlayer;
    private Timeline timeline;
    public VoicePlayer(String path){
        Media sound=new Media(new File(path).toURI().toString());
        mediaPlayer =new MediaPlayer(sound);
        new MediaView();
    }
    public void play(){
        mediaPlayer.play();
    }
    public void stop(){
        mediaPlayer.stop();
    }
    public void pause(){
        mediaPlayer.pause();
    }
    public void setEndOfMedia(VoicePlayer voicePlayer){
        mediaPlayer.setOnEndOfMedia(() -> voicePlayer.play());
    }

}
