package com.noticeBoard.app.services;

import com.noticeBoard.app.dto.AddMessageDTO;
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
        return messages.stream().map(this::getMessageDTO).collect(Collectors.toList());
    }

    private MessageDTO getMessageDTO(Message message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setId(message.getId());
        messageDTO.setTitle(message.getTitle());
        messageDTO.setContent(message.getContent());
        messageDTO.setSender(message.getSender().getUsername());
        messageDTO.setReceiver(message.getReceiver().getUsername());
        messageDTO.setReadMessage(message.getReadMessage());
        messageDTO.setCreated(message.getCreated());
        return messageDTO;
    }

    public MessageDTO getMessage(Long id) {
        Message message = messageRepository.getOne(id);
        return getMessageDTO(message);
    }

    public boolean checkIfUserIsNotSenderOrReceiver(String username, Long messageId) {
        MessageDTO message = getMessage(messageId);
        return !message.getSender().equals(username) && !message.getReceiver().equals(username);
    }

    public void markAsRead(String username, Long id) {
        Message message = messageRepository.getOne(id);
        if (message.getReceiver().getUsername().equals(username)) {
            message.setReadMessage(1);
            messageRepository.save(message);
        }
    }

    public void saveMessage(AddMessageDTO messageDTO) {
        Message message = new Message();
        message.setTitle(messageDTO.getTitle());
        message.setContent(messageDTO.getContent());
        User sender = userRepository.getByUsername(messageDTO.getSender());
        message.setSender(sender);
        User receiver = userRepository.getByUsername(messageDTO.getReceiver());
        message.setReceiver(receiver);
        messageRepository.save(message);
    }
}
