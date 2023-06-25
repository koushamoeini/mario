package com.example.mario.controllers;


import com.example.mario.user.GameData;
import com.example.mario.SuperMario;
import com.example.mario.manager.VoicePlayer;
import com.example.mario.levels.Level1_1;
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
    Timeline timerShop=new Timeline();
    static VoicePlayer shopVoice=new VoicePlayer("./src/main/resources/Media/shopWelcome.wav");

    KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> shopVoice.stop());
    public void level1_1() throws IOException {
        GameData.resetInstance();
        new Level1_1();
    }
    public void Shop() throws IOException {
        timerShop.getKeyFrames().addAll(keyFrame);
        timerShop.play();
        shopVoice.play();
        Stage stage= SuperMario.getLevelStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/Shop.fxml").toURI().toURL());
        Parent content = loader.load();
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.setHeight(SuperMario.getHeight());
        stage.setWidth(SuperMario.getWidth());
        stage.getIcons().add(SuperMario.getIcon());
        stage.setResizable(false);
        stage.setTitle(SuperMario.getStageTitle());
        stage.setX(SuperMario.getStageX());
        stage.setY(SuperMario.getStageY());
        stage.show();
    }
    public void back() throws IOException {
        Stage stage=SuperMario.getLevelStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/account.fxml").toURI().toURL());
        Parent content = loader.load();
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.setHeight(SuperMario.getHeight());
        stage.setWidth(SuperMario.getWidth());
        stage.getIcons().add(SuperMario.getIcon());
        stage.setResizable(false);
        stage.setTitle(SuperMario.getStageTitle());
        stage.setX(SuperMario.getStageX());
        stage.setY(SuperMario.getStageY());
        stage.show();
    }
    public void profile() throws IOException {
        Stage stage=SuperMario.getLevelStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/Profile.fxml").toURI().toURL());
        Parent content = loader.load();
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.setHeight(447);
        stage.setWidth(621);
        stage.getIcons().add(SuperMario.getIcon());
        stage.setResizable(false);
        stage.setTitle(SuperMario.getStageTitle());
        stage.setX(SuperMario.getStageX());
        stage.setY(SuperMario.getStageY());
        stage.show();
    }
    public void continueGame() throws IOException {
        Stage stage=SuperMario.getLevelStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/chooseSave.fxml").toURI().toURL());
        Parent content = loader.load();
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.setHeight(SuperMario.getHeight());
        stage.setWidth(SuperMario.getWidth());
        stage.getIcons().add(SuperMario.getIcon());
        stage.setResizable(false);
        stage.setTitle(SuperMario.getStageTitle());
        stage.setX(SuperMario.getStageX());
        stage.setY(SuperMario.getStageY());
        stage.show();
    }
}
