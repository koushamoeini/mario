package com.example.mario.controllers;

import com.example.mario.SuperMario;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.File;

public class FxmlLoader {
    public FxmlLoader() {
    }
    public Scene loadFxml(String path) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(new File("./src/main/resources/com/example/mario/"+path+".fxml").toURI().toURL());
        Parent content = loader.load();
        Scene scene = new Scene(content);
        return scene;
    }
}
