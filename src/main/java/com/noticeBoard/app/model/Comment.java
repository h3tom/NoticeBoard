package com.noticeBoard.app.model;

import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
public class Comment extends AbstractEntity {

    @NotBlank
    @Size(min = 1, max = 60)
    private String content;
    private LocalDateTime created;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "notice_id", nullable = false)
    private Notice notice;
    @Column(name = "unregistered_username")
    private String unregisteredUsername;

    public Comment() {
    }

    @PrePersist
    public void prePersist() {
        created = LocalDateTime.now();
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

    public String getUnregisteredUsername() {
        return unregisteredUsername;
    }

    public void setUnregisteredUsername(String unregisteredUsername) {
        this.unregisteredUsername = unregisteredUsername;
    }
}
