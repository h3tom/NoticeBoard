package com.noticeBoard.app.web.controllers.action;

import com.noticeBoard.app.dto.AddMessageDTO;
import com.noticeBoard.app.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@RequestMapping("/add-message")
public class AddMessageController {

    private MessageService messageService;

    @Autowired
    public AddMessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @RequestMapping("/{receiver}")
    public String prepareAddMessage(@PathVariable String receiver, Model model, Principal principal) {
        AddMessageDTO messageDTO = new AddMessageDTO();
        messageDTO.setSender(principal.getName());
        messageDTO.setReceiver(receiver);
        model.addAttribute("messageData", messageDTO);
        return "addMessage";
    }

    @PostMapping
    public String processAddMessage(@ModelAttribute("messageData") @Valid AddMessageDTO messageDTO,
                                    BindingResult result) {
        if (messageDTO.getReceiver().equals(messageDTO.getSender())) {
            result.rejectValue("title",
                    null,
                    "You cannot send message to yourself");
            return "addMessage";
        }
        if (result.hasErrors()) {
            return "addMessage";
        }
        messageService.saveMessage(messageDTO);
        return "redirect:/user-page/profile";
    }
}
