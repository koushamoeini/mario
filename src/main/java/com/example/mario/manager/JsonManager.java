package com.example.mario.manager;

import com.example.mario.user.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JsonManager {
    public static TypeReference<List<User>> typeReference = new TypeReference<>() {};
    public static TypeReference<List<Integer>> integerReference=new TypeReference<List<Integer>>() {};
    private ObjectMapper objectMapper;
    private String path;
    private File file;
    private List<Integer>emptyArr=new ArrayList<>();

    public JsonManager(String path) {
        emptyArr.add(0);emptyArr.add(0);emptyArr.add(0);emptyArr.add(0);emptyArr.add(100);emptyArr.add(3);emptyArr.add(1);
        this.path = path;
        file = new File(path);
        objectMapper = new ObjectMapper();
    }

    public <T> void writeArray( List<T> arr) throws IOException {
        objectMapper.writeValue(file, arr);
    }

    public <T> List<T> readArray(TypeReference<List<T>> typeReference) throws IOException {
        return objectMapper.readValue(file, typeReference);
    }

    public void writeObject(Object obj) throws IOException {
        objectMapper.writeValue(file, obj);
    }

    public <T> T readObject(Class<T> clazz) throws IOException {
        return objectMapper.readValue(file,clazz);
    }

    public <T extends Enum<T>> T readEnum(String value, Class<T> enumClass) throws IOException {
        return objectMapper.readValue("\"" + value + "\"", enumClass);
    }

    public void writeEnum(Enum<?> value) throws IOException {
        objectMapper.writeValue(file, value);
    }
    public void nothing() throws IOException {
        objectMapper.writeValue(file,emptyArr);
    }
}