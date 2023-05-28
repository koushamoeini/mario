package com.example.mario.controllers;

import com.example.mario.SuperMario;
import com.example.mario.UserData;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {
    UserData userData=UserData.getInstance();
    @FXML
    private ImageView chosenCharacter;
    @FXML
    private Label point3;
    @FXML
    private Label point2;
    @FXML
    private Label point1;
    @FXML
    private Label username;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        username.setText(userData.getCurrentUser().getUserName());
        point1.setText("score: "+userData.getCurrentUser().getPoint1());
        point2.setText("score: "+userData.getCurrentUser().getPoint2());
        point3.setText("score: "+userData.getCurrentUser().getPoint3());
        String str="";
        if(userData.getCurrentUser().isCurrentSkin1()) str="/cuphead";
        else if(userData.getCurrentUser().isCurrentSkin2()) str="/mugman";
        else if(userData.getCurrentUser().isCurrentSkin3()) str="/chalice";
        else if (userData.getCurrentUser().isCurrentSkin4()) str="/plane";
        chosenCharacter.setImage(new Image("Images"+str+"/runner.png"));
    }
    public void back() throws IOException {
        Stage stage= SuperMario.getLevelStage();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainMenu.fxml"));
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
