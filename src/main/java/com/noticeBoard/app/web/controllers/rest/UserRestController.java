package com.noticeBoard.app.web.controllers.rest;

import com.noticeBoard.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserRestController {

    private UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/usernames")
    public List<String> getUsernames() {
        return userService.getUsernames();
    }
}
