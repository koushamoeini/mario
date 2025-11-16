module com.example.mario {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens com.example.mario to javafx.fxml,com.fasterxml.jackson.databind;
    exports com.example.mario;
    exports com.example.mario.levels;
    opens com.example.mario.levels to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.example.mario.blocks;
    opens com.example.mario.blocks to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.example.mario.enemies;
    opens com.example.mario.enemies to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.example.mario.controllers;
    opens com.example.mario.controllers to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.example.mario.user;
    opens com.example.mario.user to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.example.mario.manager;
    opens com.example.mario.manager to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.example.mario.Items;
    opens com.example.mario.Items to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.example.mario.Mario;
    opens com.example.mario.Mario to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.example.mario.GameHandle;
    opens com.example.mario.GameHandle to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.example.mario.enemies.bossFight;
    opens com.example.mario.enemies.bossFight to com.fasterxml.jackson.databind, javafx.fxml;
    exports com.example.mario.utils;
    opens com.example.mario.utils to com.fasterxml.jackson.databind, javafx.fxml;
}