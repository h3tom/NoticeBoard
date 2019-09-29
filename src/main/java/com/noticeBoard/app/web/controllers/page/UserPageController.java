package com.noticeBoard.app.web.controllers.page;

import com.noticeBoard.app.dto.UserDTO;
import com.noticeBoard.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user-page")
public class UserPageController {

    private UserService userService;

    @Autowired
    public UserPageController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{username}")
    public String prepareUserPage(@PathVariable String username,
                                  Model model,
                                  Principal principal) {
        UserDTO userDTO = userService.getByUsername(username);
        if (principal.getName().equals(username)) {
            model.addAttribute("user", userDTO);
        } else {
            model.addAttribute("user", userService.userWithoutExpiredNotices(userDTO));
        }
        model.addAttribute("username", principal.getName());
        return "userPage";
    }

    @GetMapping("/profile")
    public String prepareProfilePage(Principal principal) {
        return "redirect:/user-page/" + principal.getName();
    }
}
