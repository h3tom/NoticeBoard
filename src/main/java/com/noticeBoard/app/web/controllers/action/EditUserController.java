package com.noticeBoard.app.web.controllers.action;

import com.noticeBoard.app.dto.RegistrationFormDTO;
import com.noticeBoard.app.dto.UserDTO;
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
import java.security.Principal;

@Controller
@RequestMapping("/edit-user")
public class EditUserController {

    private UserService userService;

    @Autowired
    public EditUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String prepareEditUser(Model model, Principal principal) {
        UserDTO userDTO = userService.getEditInfoByUsername(principal.getName());
        model.addAttribute("user", userDTO);
        model.addAttribute("userData", new RegistrationFormDTO());
        return "editUser";
    }

    @PostMapping
    public String processEditUser(@ModelAttribute("userData") @Valid RegistrationFormDTO editData,
                                  BindingResult result,
                                  Principal principal) {
        if (result.hasErrors()) {
            return "editUser";
        }
        if (!editData.getPassword().equals(editData.getRePassword())) {
            result.rejectValue("rePassword",
                    null,
                    "must be the same as Password");
            return "editUser";
        }
        if (!editData.getEmail().equals(userService.getUserEmail(principal.getName()))) {
            if (userService.isEmailAvailable(editData.getEmail())) {
                result.rejectValue("email",
                        null,
                        "already taken");
                return "editUser";
            }
        }
        if (!editData.getUsername().equals(principal.getName())) {
            if (userService.isUsernameAvailable(editData.getUsername())) {
                result.rejectValue("username",
                        null,
                        "already taken");
                return "editUser";
            }
        }
        userService.editUser(editData, principal);
        if (!editData.getUsername().equals(principal.getName())) {
            return "redirect:/perform_logout";
        } else {
            return "redirect:/user-page/profile";
        }
    }
}
