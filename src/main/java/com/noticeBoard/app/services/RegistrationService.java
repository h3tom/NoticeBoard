package com.noticeBoard.app.services;

import com.noticeBoard.app.dto.RegistrationFormDTO;
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
import java.util.Base64;
import java.util.Objects;
import java.util.Scanner;

@Service
@Transactional
public class RegistrationService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean isEmailAvailable(String email) {
        Long count = userRepository.countByEmail(email);
        return count <= 0;
    }

    public boolean isUsernameAvailable(String username) {
        Long count = userRepository.countByUsername(username);
        return count <= 0;
    }

    public void saveUser(RegistrationFormDTO registrationData) {
        User user = new User();
        user.setFirstName(registrationData.getFirstName());
        user.setLastName(registrationData.getLastName());
        user.setUsername(registrationData.getUsername());
        user.setEmail(registrationData.getEmail());
        String encodedPassword = passwordEncoder.encode(registrationData.getPassword());
        user.setPassword(encodedPassword);
        if (!registrationData.getAvatar().isEmpty()) {
            try {
                user.setAvatar(IOUtils.toByteArray(registrationData.getAvatar().getInputStream()));
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
}
