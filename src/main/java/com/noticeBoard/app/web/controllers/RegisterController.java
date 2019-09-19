package com.noticeBoard.app.web.controllers;

import com.noticeBoard.app.dto.RegistrationFormDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/register")
public class RegisterController {

    @GetMapping
    public String prepareRegistrationPage(Model model) {
        model.addAttribute("registerData", new RegistrationFormDTO());
        return "registration";
    }

    @PostMapping
    public String processRegistrationPage(@ModelAttribute("registerData") @Valid RegistrationFormDTO registerData,
                                          BindingResult result,
                                          Principal principal) {
        if (result.hasErrors()) {
            return "registration";
        }
        if (!registerData.getPassword().equals(registerData.getRePassword())) {
            result.rejectValue("rePassword",
                    null,
                    "must be the same as Password");
            return "registration";
        }
        return "redirect:/login";
    }
}
