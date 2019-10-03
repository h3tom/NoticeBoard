package com.noticeBoard.app.services;

import com.noticeBoard.app.dto.MessageDTO;
import com.noticeBoard.app.model.Message;
import com.noticeBoard.app.model.User;
import com.noticeBoard.app.repositories.MessageRepository;
import com.noticeBoard.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MessageService {

    private UserRepository userRepository;
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(UserRepository userRepository, MessageRepository messageRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
    }

    public List<MessageDTO> getMessagesSent(String username) {
        User user = userRepository.getByUsername(username);
        List<Message> messages = messageRepository.getAllBySenderOrderByCreatedDesc(user);
        return getMessageDTOS(messages);
    }

    public List<MessageDTO> getMessagesReceived(String username) {
        User user = userRepository.getByUsername(username);
        List<Message> messages = messageRepository.getAllByReceiverOrderByCreatedDesc(user);
        return getMessageDTOS(messages);
    }

    private List<MessageDTO> getMessageDTOS(List<Message> messages) {
        return messages.stream().map(message -> {
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setId(message.getId());
            messageDTO.setTitle(message.getTitle());
            messageDTO.setContent(message.getContent());
            messageDTO.setSender(message.getSender().getUsername());
            messageDTO.setReceiver(message.getReceiver().getUsername());
            messageDTO.setReadMessage(message.getReadMessage());
            messageDTO.setCreated(message.getCreated());
            return messageDTO;
        }).collect(Collectors.toList());
    }
}
