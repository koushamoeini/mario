package com.example.mario.controllers;

import com.example.mario.manager.JsonManager;
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

public class CreateAccountController {
    private final UserData userData=UserData.getInstance();
    @FXML
    private Label createError;
    @FXML
    private TextField createUser;
    @FXML
    private PasswordField createPass;
    public void CheckUserPass() throws IOException {
        JsonManager jsonManager=new JsonManager("./src/main/resources/GamaData/users.json");
        Stage stage= SuperMario.getLevelStage();
        for(User user :userData.getUsers()){
            if(user.getUserName().equals(createUser.getText())){
                createError.setText("Username is already taken");
                return;
            }
        }
        User user=new User(createUser.getText(),createPass.getText());
        userData.getUsers().add(user);
        File folder=new File("./src/main/resources/GamaData/"+createUser.getText());
        folder.mkdir();
        File folder1=new File("./src/main/resources/GamaData/"+createUser.getText(),"game1.json");
        folder1.createNewFile();
        File folder2=new File("./src/main/resources/GamaData/"+createUser.getText(),"game2.json");
        folder2.createNewFile();
        File folder3=new File("./src/main/resources/GamaData/"+createUser.getText(),"game3.json");
        folder3.createNewFile();
        jsonManager.writeArray(userData.getUsers());
        JsonManager jsonManager1=new JsonManager("./src/main/resources/GamaData/"+createUser.getText()+"/game1.json");
        JsonManager jsonManager2=new JsonManager("./src/main/resources/GamaData/"+createUser.getText()+"/game2.json");
        JsonManager jsonManager3=new JsonManager("./src/main/resources/GamaData/"+createUser.getText()+"/game3.json");
        jsonManager3.nothing();
        jsonManager2.nothing();
        jsonManager1.nothing();
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
    public void back () throws IOException {
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
}
