package com.noticeBoard.app.web.controllers.action;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AddMessageController {

    @GetMapping
    public String prepareAddMessage(Model model) {

        return null;
    }
}
