package com.noticeBoard.app.web.controllers.action;

import com.noticeBoard.app.dto.RegistrationFormDTO;
import com.noticeBoard.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/register")
public class RegistrationController {

    private UserService userService;

    @Autowired
    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String prepareRegistration(Model model) {
        model.addAttribute("registrationData", new RegistrationFormDTO());
        return "registration";
    }

    @PostMapping
    public String processRegistration(@ModelAttribute("registrationData") @Valid RegistrationFormDTO registrationData,
                                          BindingResult result) {
        if (result.hasErrors()) {
            return "registration";
        }
        if (!registrationData.getPassword().equals(registrationData.getRePassword())) {
            result.rejectValue("rePassword",
                    null,
                    "must be the same as Password");
            return "registration";
        }
        if (userService.isEmailAvailable(registrationData.getEmail())) {
            result.rejectValue("email",
                    null,
                    "already taken");
            return "registration";
        }
        if (userService.isUsernameAvailable(registrationData.getUsername())) {
            result.rejectValue("username",
                    null,
                    "already taken");
            return "registration";
        }
        userService.saveUser(registrationData);
        return "redirect:/login";
    }
}
