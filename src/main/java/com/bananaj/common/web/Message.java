package com.bananaj.common.web;

import java.util.ResourceBundle;

public class Message {

    private ResourceBundle resourceBundle;

    public Message() {
    }

    public void init() {
        resourceBundle = ResourceBundle.getBundle("messages");
    }

    public String getString(String key) {
        return resourceBundle.getString(key);
    }

    public String s(String key) {
        return getString(key);
    }




}
