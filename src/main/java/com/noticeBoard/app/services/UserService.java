package com.noticeBoard.app.services;

import com.noticeBoard.app.dto.CommentDTO;
import com.noticeBoard.app.dto.NoticeDTO;
import com.noticeBoard.app.dto.RegistrationFormDTO;
import com.noticeBoard.app.dto.UserDTO;
import com.noticeBoard.app.model.User;
import com.noticeBoard.app.repositories.UserRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.Principal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isEmailAvailable(String email) {
        Long count = userRepository.countByEmail(email);
        return count > 0;
    }

    public String getUserEmail(String username) {
        return userRepository.getByUsername(username).getEmail();
    }

    public boolean isUsernameAvailable(String username) {
        Long count = userRepository.countByUsername(username);
        return count > 0;
    }

    public void saveUser(RegistrationFormDTO registrationData) {
        User user = new User();
        saveOrUpdateUser(registrationData, user);
    }

    public void editUser(RegistrationFormDTO editData, Principal principal) {
        User user = userRepository.getByUsername(principal.getName());
        saveOrUpdateUser(editData, user);
    }

    private void saveOrUpdateUser(RegistrationFormDTO data, User user) {
        user.setFirstName(data.getFirstName());
        user.setLastName(data.getLastName());
        user.setUsername(data.getUsername());
        user.setEmail(data.getEmail());
        String encodedPassword = passwordEncoder.encode(data.getPassword());
        user.setPassword(encodedPassword);
        if (!data.getAvatar().isEmpty()) {
            try {
                user.setAvatar(IOUtils.toByteArray(data.getAvatar().getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                File file = new File(Objects.requireNonNull(
                        getClass().getClassLoader().getResource("avatar/defaultAvatar.txt")).getFile());
                Scanner scanner = new Scanner(file);
                user.setAvatar(Base64.getDecoder().decode(scanner.nextLine()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        userRepository.save(user);
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

    public UserDTO getEditInfoByUsername(String username) {
        User user = userRepository.getByUsername(username);
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
    }

    public List<String> getUsernames() {
        List<User> users = userRepository.findAll();
        return users.stream().map(User::getUsername).collect(Collectors.toList());
    }
}
