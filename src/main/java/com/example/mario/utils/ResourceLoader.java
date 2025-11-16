package com.example.mario.utils;

import javafx.scene.image.Image;

public class ResourceLoader {
    public static Image loadImage(String path) {
        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return new Image(ResourceLoader.class.getResource(path).toExternalForm());
    }
}
