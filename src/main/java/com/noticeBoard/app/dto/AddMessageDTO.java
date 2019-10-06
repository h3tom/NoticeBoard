package com.noticeBoard.app.dto;

import javax.validation.constraints.Size;

public class AddMessageDTO {

    @Size(min = 5, max = 40, message = "must be between 5 and 40 characters")
    private String title;
    @Size(min = 5, max = 250, message = "must be between 5 and 250 characters")
    private String content;
    private String sender;
    private String receiver;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}
