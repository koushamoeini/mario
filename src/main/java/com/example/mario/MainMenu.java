package com.example.mario;


import com.example.mario.levels.Level1_1;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class MainMenu  {
    Timeline timerShop=new Timeline();
    static VoicePlayer shopVoice=new VoicePlayer("C:\\Users\\koush\\IdeaProjects\\Mario\\src\\main\\resources\\Media\\shopWelcome.wav");

    KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> shopVoice.stop());
    public void level1_1() throws IOException {
        GameData.resetInstance();
        new Level1_1();
    }
    public void Shop() throws IOException {
        timerShop.getKeyFrames().addAll(keyFrame);
        timerShop.play();
        shopVoice.play();
        Stage stage=SuperMario.getLevelStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Shop.fxml"));
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
        loader.setLocation(getClass().getResource("account.fxml"));
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
        loader.setLocation(getClass().getResource("Profile.fxml"));
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
        loader.setLocation(getClass().getResource("chooseSave.fxml"));
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
