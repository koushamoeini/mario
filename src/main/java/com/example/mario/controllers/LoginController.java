package com.example.mario.controllers;

import com.example.mario.SuperMario;
import com.example.mario.user.User;
import com.example.mario.user.UserData;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {
    private final UserData userData=UserData.getInstance();
    @FXML
    private Label loginError;
    @FXML
    private TextField logUser;
    @FXML
    private PasswordField passUser;
    private FxmlLoader fxmlLoader=new FxmlLoader();

    public void isValidUserPass() throws Exception {
        Stage stage= SuperMario.getLevelStage();
        for(User user :userData.getUsers()) {
            if(user.getUserName().equals(logUser.getText())&&user.getPassword().equals(passUser.getText())){
                stage.setScene(fxmlLoader.loadFxml("MainMenu"));
                userData.setCurrentUser(user);
                SuperMario.menuSong.stop();
            }
        }
        loginError.setText("username or password is incorrect");
    }
    public void back () throws Exception {
        Stage stage=SuperMario.getLevelStage();
        stage.setScene(fxmlLoader.loadFxml("account"));
    }
}
