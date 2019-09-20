package com.noticeBoard.app.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.validation.constraints.Size;

public class RegistrationFormDTO {

    @NotBlank(message = "cannot be empty")
    @Size(min = 2, message = "must have at least 2 characters")
    private String username;

    @NotBlank(message = "cannot be empty")
    @Email(message = "incorrect email")
    private String email;

    @NotBlank(message = "cannot be empty")
    @Size(min = 2, message = "must have at least 2 characters")
    private String firstName;

    @NotBlank(message = "cannot be empty")
    @Size(min = 2, message = "must have at least 2 characters")
    private String lastName;

    @NotBlank(message = "cannot be empty")
    @Size(min = 8, max = 12, message = "must be between 8 and 12 characters")
    private String password;

    @NotBlank(message = "cannot be empty")
    @Size(min = 8, max = 12, message = "must be between 8 and 12 characters")
    private String rePassword;

    private CommonsMultipartFile avatar;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }

    public CommonsMultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(CommonsMultipartFile avatar) {
        this.avatar = avatar;
    }
}
