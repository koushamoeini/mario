package com.example.mario.controllers;


import com.example.mario.GameHandle.GameSound;
import com.example.mario.user.GameData;
import com.example.mario.SuperMario;
import com.example.mario.manager.VoicePlayer;
import com.example.mario.levels.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class MainMenuController {
    private final VoicePlayer shopVoice=new VoicePlayer("./src/main/resources/Media/shopWelcome.wav");
    private FxmlLoader fxmlLoader=new FxmlLoader();
    public void level1_1() throws Exception {
        GameData.resetInstance();
        new Level1_1();
    }
    public void Shop() throws Exception {
        shopVoice.play();
        Stage stage= SuperMario.getLevelStage();
        stage.setScene(fxmlLoader.loadFxml("Shop"));
    }
    public void back() throws Exception {
        Stage stage=SuperMario.getLevelStage();
        stage.setScene(fxmlLoader.loadFxml("account"));
    }
    public void profile() throws Exception {
        Stage stage=SuperMario.getLevelStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/Profile.fxml").toURI().toURL());
        Parent content = loader.load();
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.setHeight(447);
        stage.setWidth(621);
        stage.setResizable(false);
        stage.show();
    }
    public void continueGame() throws Exception {
        Stage stage=SuperMario.getLevelStage();
        stage.setScene(fxmlLoader.loadFxml("chooseSave"));
    }
}
