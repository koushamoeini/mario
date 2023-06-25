package com.example.mario.controllers;

import com.example.mario.manager.JsonManager;
import com.example.mario.SuperMario;
import com.example.mario.user.UserData;
import com.example.mario.levels.Level1_1;
import com.example.mario.levels.Level1_2;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class ChooseSaveController {
    UserData userData=UserData.getInstance();
    private static boolean firstSave=false;
    private static boolean secondSave=false;
    private static boolean thirdSave=false;
    private static int section;
    public void firstSave() throws IOException {
        JsonManager jsonManager=new JsonManager("./src/main/resources/GamaData/"+userData.getCurrentUser().getUserName()+"/game1.json");
        section=jsonManager.readArray(JsonManager.integerReference).get(8);
        firstSave=true;
        if(section==2)new Level1_2();
        else new Level1_1();
    }
    public void secondSave() throws IOException {
        JsonManager jsonManager=new JsonManager("./src/main/resources/GamaData/"+userData.getCurrentUser().getUserName()+"/game2.json");
        section=jsonManager.readArray(JsonManager.integerReference).get(8);
        secondSave=true;
        if(section==2)new Level1_2();
        else new Level1_1();
    }
    public void thirdSave() throws IOException {
        JsonManager jsonManager=new JsonManager("./src/main/resources/GamaData/"+userData.getCurrentUser().getUserName()+"/game3.json");
        section=jsonManager.readArray(JsonManager.integerReference).get(8);
        thirdSave=true;
        if(section==2)new Level1_2();
        else new Level1_1();
    }
    public void back() throws IOException {
        Stage stage= SuperMario.getLevelStage();
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
    }

    public static boolean isFirstSave() {
        return firstSave;
    }

    public static boolean isSecondSave() {
        return secondSave;
    }

    public static boolean isThirdSave() {
        return thirdSave;
    }

    public static void setFirstSave(boolean firstSave) {
        ChooseSaveController.firstSave = firstSave;
    }

    public static void setSecondSave(boolean secondSave) {
        ChooseSaveController.secondSave = secondSave;
    }

    public static void setThirdSave(boolean thirdSave) {
        ChooseSaveController.thirdSave = thirdSave;
    }

    public static int getSection() {
        return section;
    }
}
