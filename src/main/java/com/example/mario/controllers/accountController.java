package com.example.mario.controllers;

import com.example.mario.SuperMario;
import javafx.stage.Stage;


public class accountController {
    private final FxmlLoader fxmlLoader=new FxmlLoader();
    public void login() throws Exception {
        Stage stage= SuperMario.getLevelStage();
        stage.setScene(fxmlLoader.loadFxml("login"));
    }
    public void createAccount() throws Exception {
        Stage stage=SuperMario.getLevelStage();
        stage.setScene(fxmlLoader.loadFxml("createAccount"));

    }
    public void exit(){
        System.exit(0);
    }
}

