package com.project.real_estate_project03_team02.service.helper;

import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.exception.ConflictException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserServiceHelper {
    private final UserRepository userRepository;

    public void checkDuplicate(String value) {
        if (userRepository.existsByEmail(value)) {
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTER_MESSAGE_EMAIL, value));
        }
    }
    //TODO tests..

    public  String generateResetCode(int length) {
        StringBuilder resetCode = new StringBuilder();

        for (int i = 0; i < length; i++) {
            char randomChar = generateRandomChar();
            resetCode.append(randomChar);
        }

        return resetCode.toString();
    }

    private char generateRandomChar() {
        String characters = "ABCDEFGHIJKLMNPQRSTUVWXYZabcdefghijklmnpqrstuvwxyz123456789!@#$%^&*()";
        int randomIndex = (int) (Math.random() * characters.length());
        return characters.charAt(randomIndex);
    }

    public User getUserResetCode(String resetCode) {
        User user = userRepository.findByResetPasswordCode(resetCode);
        return user != null ? user : null;
    }



}
