package com.noticeBoard.app.dto;

public class ChatMessageDTO {

    private String message;
    private String user;

    public ChatMessageDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
