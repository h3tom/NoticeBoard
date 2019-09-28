package com.noticeBoard.app.dto;

import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    private String username;
    private String email;
    private String firstName;
    private String lastName;
    private String avatar;
    private List<NoticeDTO> notices = new ArrayList<>();
    private List<CommentDTO> comments = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<NoticeDTO> getNotices() {
        return notices;
    }

    public void setNotices(List<NoticeDTO> notices) {
        this.notices = notices;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }
}
