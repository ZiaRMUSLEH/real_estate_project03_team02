package com.project.real_estate_project03_team02.security.service;


import com.project.real_estate_project03_team02.entity.concretes.User;
import com.project.real_estate_project03_team02.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user= userRepository.findByEmailEquals(email);

        if (user != null) {
            return new UserDetailsImpl(
                    user.getId(),
                    user.getEmail(),
                    user.getLastName(),
                    user.getPasswordHash(),
                    String.valueOf(user.getRoles()));

        }
        throw new UsernameNotFoundException("User with email '" + email + "' not found!");
    }


}
