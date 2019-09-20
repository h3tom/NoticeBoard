package com.noticeBoard.app.web.controllers;

import com.noticeBoard.app.dto.RegistrationFormDTO;
import com.noticeBoard.app.services.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class RegistrationController {

    private RegistrationService registrationService;

    @Autowired
    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @GetMapping
    public String prepareRegistrationPage(Model model) {
        model.addAttribute("registrationData", new RegistrationFormDTO());
        return "registration";
    }

    @PostMapping
    public String processRegistrationPage(@ModelAttribute("registrationData") @Valid RegistrationFormDTO registrationData,
                                          BindingResult result,
                                          Principal principal) {
        if (result.hasErrors()) {
            return "registration";
        }
        if (!registrationData.getPassword().equals(registrationData.getRePassword())) {
            result.rejectValue("rePassword",
                    null,
                    "must be the same as Password");
            return "registration";
        }
        if (!registrationService.isEmailAvailable(registrationData.getEmail())) {
            result.rejectValue("email",
                    null,
                    "already taken");
            return "registration";
        }
        if (!registrationService.isUsernameAvailable(registrationData.getUsername())) {
            result.rejectValue("username",
                    null,
                    "already taken");
            return "registration";
        }
        registrationService.saveUser(registrationData);
        return "redirect:/login";
    }
}
