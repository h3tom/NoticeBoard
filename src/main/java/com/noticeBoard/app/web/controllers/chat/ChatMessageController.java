package com.noticeBoard.app.web.controllers.chat;

import com.noticeBoard.app.dto.ChatMessageDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.security.Principal;

@Controller
public class ChatMessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public ChatMessageDTO get(ChatMessageDTO chatMessage, Principal principal) {
        chatMessage.setUser(principal.getName());
        return chatMessage;
    }

}
