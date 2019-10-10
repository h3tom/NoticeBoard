package com.noticeBoard.app.dto;

public class ChatMessageDTO {

    private String message;
    private UserDTO userDTO;

    public ChatMessageDTO() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
}
