package com.noticeBoard.app.dto;

import java.time.LocalDateTime;

public class MessageDTO {

    private Long id;
    private String title;
    private String content;
    private String sender;
    private String receiver;
    private int readMessage;
    private LocalDateTime created;

    public MessageDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public int getReadMessage() {
        return readMessage;
    }

    public void setReadMessage(int readMessage) {
        this.readMessage = readMessage;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }
}
