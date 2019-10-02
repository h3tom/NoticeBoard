package com.noticeBoard.app.web.controllers.page;

import com.noticeBoard.app.dto.UserDTO;
import com.noticeBoard.app.services.MessageService;
import com.noticeBoard.app.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.time.LocalDate;

@Controller
@RequestMapping("/user-page")
public class UserPageController {

    private UserService userService;
    private MessageService messageService;

    @Autowired
    public UserPageController(UserService userService, MessageService messageService) {
        this.userService = userService;
        this.messageService = messageService;
    }

    @GetMapping("/{username}")
    public String prepareUserPage(@PathVariable String username,
                                  Model model,
                                  Principal principal) {
        UserDTO userDTO = userService.getByUsername(username);
        model.addAttribute("user", userDTO);
        model.addAttribute("localDate", LocalDate.now());
        model.addAttribute("username", principal.getName());
        model.addAttribute("messagesSent", messageService.getMessagesSent(principal.getName()));
        model.addAttribute("messagesReceived", messageService.getMessagesReceived(principal.getName()));
        return "userPage";
    }

    @GetMapping("/profile")
    public String prepareProfilePage(Principal principal) {
        return "redirect:/user-page/" + principal.getName();
    }
}
