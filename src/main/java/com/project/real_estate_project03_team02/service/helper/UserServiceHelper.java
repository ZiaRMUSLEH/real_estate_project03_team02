package com.project.real_estate_project03_team02.service.helper;

import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.exception.ConflictException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
@RequiredArgsConstructor
public class UserServiceHelper {
    private final UserRepository userRepository;

    public void checkDuplicate(String value) {
        if (userRepository.existsByEmail(value)) {
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTER_MESSAGE_EMAIL, value));
        }
    }

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
        return userRepository.findByResetPasswordCode(resetCode).orElse(null);
    }
    public User getUserFromUsernameAttribute (HttpServletRequest httpServletRequest){
        String userName = (String) httpServletRequest.getAttribute("username");
        User user=userRepository.findByEmailEquals(userName).orElse(null);
        if(user==null) {
            throw new BadRequestException(ErrorMessages.USER_NOT_FOUND);
        }
        return user;
    }



}
