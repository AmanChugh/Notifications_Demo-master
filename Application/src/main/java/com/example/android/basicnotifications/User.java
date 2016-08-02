package com.example.android.basicnotifications;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amandeepsingh on 2/8/16.
 */
public class User {

    private int user_id = 0;
    private List<String> messages = new ArrayList<>();

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(String message) {
        messages.add(message);
    }

}
