package com.example.mario.controllers;

import com.example.mario.manager.JsonManager;
import com.example.mario.SuperMario;
import com.example.mario.user.UserData;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ShopController implements Initializable {
    private final UserData userData = UserData.getInstance();
    private int counter = 0;
    @FXML
    private ImageView characterImage;
    @FXML
    private Label price;
    @FXML
    private Label userCoin;
    @FXML
    private Button select;
    @FXML
    ImageView coin;
    @FXML
    private Button buy;
    JsonManager jsonManager=new JsonManager("./src/main/resources/GamaData/users.json");
    private final FxmlLoader fxmlLoader=new FxmlLoader();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        coin.setVisible(false);
        buy.setVisible(false);
        price.setVisible(false);
        userCoin.setText(Integer.toString(userData.getCurrentUser().getCoins()));
    }

    public void next() {
        counter++;
        if (counter % 5 == 0) {
            characterImage.setImage(new Image("Images/runner.png"));
            if (userData.getCurrentUser().isSkin0()) {
                select.setVisible(true);
                coin.setVisible(false);
                price.setVisible(false);
                buy.setVisible(false);
            } else {
                select.setVisible(false);
                buy.setVisible(true);
                coin.setVisible(true);
                price.setVisible(true);
            }
        }
        if (counter % 5 == 1) {
            characterImage.setImage(new Image("Images/cuphead/runner.png"));
            if (userData.getCurrentUser().isSkin1()) {
                select.setVisible(true);
                coin.setVisible(false);
                buy.setVisible(false);
                price.setVisible(false);
            } else {
                select.setVisible(false);
                buy.setVisible(true);
                coin.setVisible(true);
                price.setVisible(true);
            }
        }
        if (counter % 5 == 2) {
            characterImage.setImage(new Image("Images/mugman/runner.png"));
            if (userData.getCurrentUser().isSkin2()) {
                select.setVisible(true);
                coin.setVisible(false);
                buy.setVisible(false);
                price.setVisible(false);
            } else {
                select.setVisible(false);
                buy.setVisible(true);
                coin.setVisible(true);
                price.setVisible(true);
            }
        }
        if (counter % 5 == 3) {
            characterImage.setImage(new Image("Images/chalice/runner.png"));
            if (userData.getCurrentUser().isSkin3()) {
                select.setVisible(true);
                buy.setVisible(false);
                coin.setVisible(false);
                price.setVisible(false);
            } else {
                select.setVisible(false);
                buy.setVisible(true);
                coin.setVisible(true);
                price.setVisible(true);
            }
        }
        if (counter % 5 == 4) {
            characterImage.setImage(new Image("Images/plane/runner.png"));
            if (userData.getCurrentUser().isSkin4()) {
                select.setVisible(true);
                buy.setVisible(false);
                coin.setVisible(false);
                price.setVisible(false);
            } else {
                select.setVisible(false);
                buy.setVisible(true);
                coin.setVisible(true);
                price.setVisible(true);
            }
        }

    }
    public String urlEditor(String str){
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='I'&&str.charAt(i+1)=='m'){
                str=str.substring(i);
                return str;
            }
        }
        return "" ;
    }
    public void select() throws IOException {
        if (urlEditor(characterImage.getImage().getUrl()).equals("Images/runner.png")) {
            userData.getCurrentUser().setCurrentSkin0(true);
            userData.getCurrentUser().setCurrentSkin1(false);
            userData.getCurrentUser().setCurrentSkin2(false);
            userData.getCurrentUser().setCurrentSkin3(false);
            userData.getCurrentUser().setCurrentSkin4(false);
            jsonManager.writeArray(userData.getUsers());
        }
        if (urlEditor(characterImage.getImage().getUrl()).equals("Images/cuphead/runner.png")) {
            userData.getCurrentUser().setCurrentSkin0(false);
            userData.getCurrentUser().setCurrentSkin1(true);
            userData.getCurrentUser().setCurrentSkin2(false);
            userData.getCurrentUser().setCurrentSkin3(false);
            userData.getCurrentUser().setCurrentSkin4(false);
            jsonManager.writeArray(userData.getUsers());
        }
        if (urlEditor(characterImage.getImage().getUrl()).equals("Images/mugman/runner.png")) {
            userData.getCurrentUser().setCurrentSkin0(false);
            userData.getCurrentUser().setCurrentSkin1(false);
            userData.getCurrentUser().setCurrentSkin2(true);
            userData.getCurrentUser().setCurrentSkin3(false);
            userData.getCurrentUser().setCurrentSkin4(false);
            jsonManager.writeArray(userData.getUsers());
        }
        if (urlEditor(characterImage.getImage().getUrl()).equals("Images/chalice/runner.png")) {
            userData.getCurrentUser().setCurrentSkin0(false);
            userData.getCurrentUser().setCurrentSkin1(false);
            userData.getCurrentUser().setCurrentSkin2(false);
            userData.getCurrentUser().setCurrentSkin3(true);
            userData.getCurrentUser().setCurrentSkin4(false);
            jsonManager.writeArray(userData.getUsers());
        }
        if (urlEditor(characterImage.getImage().getUrl()).equals("Images/plane/runner.png")) {
            userData.getCurrentUser().setCurrentSkin0(false);
            userData.getCurrentUser().setCurrentSkin1(false);
            userData.getCurrentUser().setCurrentSkin2(false);
            userData.getCurrentUser().setCurrentSkin3(false);
            userData.getCurrentUser().setCurrentSkin4(true);
            jsonManager.writeArray(userData.getUsers());
        }
    }

    public void buy() throws IOException {
        if (userData.getCurrentUser().getCoins() >= Integer.parseInt(price.getText())) {
            userData.getCurrentUser().setCoins(userData.getCurrentUser().getCoins() - Integer.parseInt(price.getText()));
            userCoin.setText(Integer.toString(userData.getCurrentUser().getCoins()));
            price.setVisible(false);
            coin.setVisible(false);
            buy.setVisible(false);
            select.setVisible(true);
            if (counter % 5 == 1) {
                userData.getCurrentUser().setSkin1(true);
                jsonManager.writeArray(userData.getUsers());
            }
            if (counter % 5 == 2) {
                userData.getCurrentUser().setSkin2(true);
                jsonManager.writeArray(userData.getUsers());
            }
            if (counter % 5 == 3) {
                userData.getCurrentUser().setSkin3(true);
                jsonManager.writeArray(userData.getUsers());
            }
            if (counter % 5 == 4) {
                userData.getCurrentUser().setSkin4(true);
                jsonManager.writeArray(userData.getUsers());
            }
        }
    }

    public void back() throws Exception {
        Stage stage = SuperMario.getLevelStage();
        stage.setScene(fxmlLoader.loadFxml("MainMenu"));
    }
}
