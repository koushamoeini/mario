package com.example.mario;

import com.example.mario.manager.JsonManager;
import com.example.mario.manager.VoicePlayer;
import com.example.mario.user.User;
import com.example.mario.user.UserData;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class SuperMario extends Application {
    private static  Stage levelStage;
    private static final int width = 1020;
    private static final int height = 637;
    private static final int stageX = 180;
    private static final int stageY = 0;
    private UserData userData=UserData.getInstance();
    private static final String stageTitle = "super mario";
    private static final Image icon=new Image("Images/backGrounds/icon.png");
     Timeline timerMenu=new Timeline();
    public static VoicePlayer menuSong=new VoicePlayer("./src/main/resources/Media/menuSong.mp3");

    KeyFrame keyFrame = new KeyFrame(Duration.seconds(70), event -> menuSong.stop());
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
        userData.setCurrentUser(new User("",""));
        JsonManager jsonManager=new JsonManager("./src/main/resources/GamaData/users.json");
        userData.setUsers(jsonManager.readArray(JsonManager.typeReference));
        timerMenu.getKeyFrames().addAll(keyFrame);
        timerMenu.setCycleCount(Animation.INDEFINITE);
        timerMenu.play();
        menuSong.play();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/account.fxml").toURI().toURL());
        Parent content= loader.load();
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.setHeight(SuperMario.getHeight());
        stage.setWidth(SuperMario.getWidth());
        stage.getIcons().add(SuperMario.getIcon());
        stage.setResizable(false);
        stage.setTitle(SuperMario.getStageTitle());
        stage.setX(SuperMario.getStageX());
        stage.setY(SuperMario.getStageY());
        levelStage=stage;
        stage.show();
    }

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    public static int getStageX() {
        return stageX;
    }

    public static int getStageY() {
        return stageY;
    }
    public static String getStageTitle() {
        return stageTitle;
    }
    public static Image getIcon() {
        return icon;
    }

    public static Stage getLevelStage() {
        return levelStage;
    }
}