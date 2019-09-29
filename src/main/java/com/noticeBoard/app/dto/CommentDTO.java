package com.noticeBoard.app.dto;

import java.time.LocalDateTime;

public class CommentDTO implements Comparable<CommentDTO> {

    private Long noticeId;
    private String content;
    private LocalDateTime created;
    private String registeredUsername;
    private String unregisteredUsername;

    @Override
    public int compareTo(CommentDTO o) {
        return this.getCreated().compareTo(o.getCreated());
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

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
