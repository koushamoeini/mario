package com.example.mario.controllers;

import com.example.mario.user.GameData;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class GameLabelController implements Initializable {
    @FXML
    public AnchorPane Pane;
    @FXML
    public Label time;
    @FXML
    public ImageView HpBar;
    @FXML
    public Label coin;
    @FXML
    public Label point;
    public static Timeline timeline;
    private static GameLabelController instance;
    public GameLabelController(){
        instance=this;
    }
    public static GameLabelController getInstance(){
        if (instance==null)
            instance = new GameLabelController();
        return instance;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        timeline = new Timeline();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), event -> {
            setCoinChange(GameData.getInstance().getCoin());
            setPointChange(GameData.getInstance().getPoint());
            setHpChange((GameData.getInstance().getHp()));
            int value = GameData.getInstance().getTime() - 1;
            GameData.getInstance().setTime(value);
            time.setText(Integer.toString(value));
        });
        timeline.getKeyFrames().addAll(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }
    public void setCoinChange(int num){
        coin.setText(Integer.toString(num));
    }
    public void setPointChange(int num){
        point.setText(num+"P");
    }
    public void setHpChange(int num){
       if(num==1){
           HpBar.setImage(new Image("Images/backGrounds/Hp1.png"));
       }
       if(num==2){
           HpBar.setImage(new Image("Images/backGrounds/Hp2.png"));
       }
       if(num==3){
           HpBar.setImage(new Image("Images/backGrounds/Hp3.png"));
       }
    }
    public void setTimeChange(int num){
            time.setText(Integer.toString(num));
    }

    public Label getTime() {
        return time;
    }
}

