package com.project.real_estate_project03_team02.service.helper;

import com.project.real_estate_project03_team02.exceptions.ConflictException;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ServiceHelpers {
    private final UserRepository userRepository;
    public void checkDuplicate(String value) {
        if (userRepository.existsByEmail(value)) {
            throw new ConflictException(String.format(ErrorMessages.ALREADY_REGISTER_MESSAGE_EMAIL, value));
        }
    }


}
