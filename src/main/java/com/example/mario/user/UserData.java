package com.example.mario.user;

import java.util.ArrayList;
import java.util.List;

public class UserData {
    private static UserData object;
    private List<User> users;
    private User currentUser;

    public UserData() {
        users=new ArrayList<>();
    }
    public static UserData getInstance(){
        if (object == null){
            object = new UserData();
        }
        return object;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
}
