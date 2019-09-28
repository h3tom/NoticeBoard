package com.noticeBoard.app.dto;

import java.time.LocalDateTime;

public class CommentDTO {

    private String content;
    private LocalDateTime created;
    private String registeredUsername;
    private String unregisteredUsername;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public String getRegisteredUsername() {
        return registeredUsername;
    }

    public void setRegisteredUsername(String registeredUsername) {
        this.registeredUsername = registeredUsername;
    }

    public String getUnregisteredUsername() {
        return unregisteredUsername;
    }

    public void setUnregisteredUsername(String unregisteredUsername) {
        this.unregisteredUsername = unregisteredUsername;
    }
}
