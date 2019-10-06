package com.noticeBoard.app.web.controllers.page;

import com.noticeBoard.app.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/message")
public class MessagePageController {

    private MessageService messageService;

    @Autowired
    public MessagePageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @RequestMapping("/{id}")
    public String prepareMessagePage(@PathVariable Long id, Model model, Principal principal) {
        if (messageService.checkIfUserIsNotSenderOrReceiver(principal.getName(), id)) {
            return "redirect:/home/";
        }
        messageService.markAsRead(principal.getName(), id);
        model.addAttribute("message", messageService.getMessage(id));
        model.addAttribute("username", principal.getName());
        return "showMessageInfo";
    }
}
