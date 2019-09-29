package com.noticeBoard.app.services;

import com.noticeBoard.app.dto.CommentDTO;
import com.noticeBoard.app.dto.NoticeDTO;
import com.noticeBoard.app.dto.UserDTO;
import com.noticeBoard.app.model.User;
import com.noticeBoard.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Base64;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO getByUsername(String username) {
        User user = userRepository.getByUsername(username);
        UserDTO userDTO = new UserDTO();

        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());

        if (user.getAvatar() != null) {
            userDTO.setAvatar(Base64.getEncoder().encodeToString(user.getAvatar()));
        }

        userDTO.setNotices(user.getNotices().stream().map(notice -> {
            NoticeDTO noticeDTO = new NoticeDTO();
            noticeDTO.setId(notice.getId());
            noticeDTO.setTitle(notice.getTitle());
            noticeDTO.setCreated(notice.getCreated());
            noticeDTO.setEndDate(notice.getEndDate());
            return noticeDTO;
        }).collect(Collectors.toList()));
        userDTO.getNotices().sort(Collections.reverseOrder());

        userDTO.setComments(user.getComments().stream().map(comment -> {
            CommentDTO commentDTO = new CommentDTO();
            commentDTO.setNoticeId(comment.getNotice().getId());
            commentDTO.setContent(comment.getContent());
            commentDTO.setCreated(comment.getCreated());

            return commentDTO;
        }).collect(Collectors.toList()));
        userDTO.getComments().sort(Collections.reverseOrder());

        return userDTO;
    }

    public UserDTO userWithoutExpiredNotices(UserDTO userDTO) {
        List<NoticeDTO> noticeDTOS = userDTO.getNotices().stream()
                .filter(noticeDTO -> noticeDTO.getEndDate().isAfter(LocalDate.now()) || noticeDTO.getEndDate().isEqual(LocalDate.now()))
                .collect(Collectors.toList());
        userDTO.setNotices(noticeDTOS);
        return userDTO;
    }
}
