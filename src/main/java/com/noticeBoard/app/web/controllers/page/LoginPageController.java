package com.noticeBoard.app.web.controllers.page;

import com.noticeBoard.app.dto.LoginFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginPageController {

    @GetMapping
    public String prepareLoginPage(Model model) {
        model.addAttribute("loginData", new LoginFormDTO());
        return "login";
    }
}
