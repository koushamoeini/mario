package com.example.mario.controllers;

import com.example.mario.SuperMario;
import com.example.mario.user.User;
import com.example.mario.user.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class LoginController {
    private final UserData userData=UserData.getInstance();
    @FXML
    private Label loginError;
    @FXML
    private TextField logUser;
    @FXML
    private PasswordField passUser;

    public void isValidUserPass() throws IOException {
        Stage stage= SuperMario.getLevelStage();
        for(User user :userData.getUsers()) {
            if(user.getUserName().equals(logUser.getText())&&user.getPassword().equals(passUser.getText())){
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(new File("./src/main/resources/com/example/mario/MainMenu.fxml").toURI().toURL());
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
                userData.setCurrentUser(user);
                SuperMario.menuSong.stop();
            }
        }
        loginError.setText("username or password is incorrect");
    }
    public void back () throws IOException {
        Stage stage=SuperMario.getLevelStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/account.fxml").toURI().toURL());
        Parent content = loader.load();
        Scene scene = new Scene(content);
        stage.setScene(scene);
        stage.setHeight(SuperMario.getHeight());
        stage.setWidth(1020);
        stage.getIcons().add(SuperMario.getIcon());
        stage.setResizable(false);
        stage.setTitle(SuperMario.getStageTitle());
        stage.setX(SuperMario.getStageX());
        stage.setY(SuperMario.getStageY());
        stage.show();
    }
}
