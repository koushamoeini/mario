module com.example.supermario {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires com.fasterxml.jackson.annotation;
    requires com.fasterxml.jackson.core;
    requires com.fasterxml.jackson.databind;


    opens com.example.mario to javafx.fxml,com.fasterxml.jackson.databind;
    exports com.example.mario;
}