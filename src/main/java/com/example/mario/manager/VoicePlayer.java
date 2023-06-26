package com.example.mario.manager;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;

public class VoicePlayer {
    private MediaPlayer mediaPlayer;
    public VoicePlayer(String path){
        Media sound=new Media(new File(path).toURI().toString());
        mediaPlayer =new MediaPlayer(sound);
        new MediaView();
        mediaPlayer.setOnEndOfMedia(() ->{
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
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
}
