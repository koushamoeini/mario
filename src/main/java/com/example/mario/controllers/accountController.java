package com.example.mario.controllers;

import com.example.mario.SuperMario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class accountController {


    public void login() throws IOException {
        Stage stage= SuperMario.getLevelStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/login.fxml").toURI().toURL());
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
    public void createAccount() throws IOException {
        Stage stage=SuperMario.getLevelStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/createAccount.fxml").toURI().toURL());
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
    public void exit(){
        System.exit(0);
    }
}

