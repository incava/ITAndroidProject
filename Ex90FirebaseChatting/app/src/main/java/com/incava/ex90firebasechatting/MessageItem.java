package com.incava.ex90firebasechatting;

public class MessageItem {

    public String name;
    public String message;
    public String profileUrl;
    public String time;

    public MessageItem(String name, String message, String profileUrl, String time) {
        this.name = name;
        this.message = message;
        this.profileUrl = profileUrl;
        this.time = time;
    }
    public MessageItem() {

    }

}
