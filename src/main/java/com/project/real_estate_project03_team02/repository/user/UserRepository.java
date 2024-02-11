package com.project.real_estate_project03_team02.repository.user;

import com.project.real_estate_project03_team02.entity.concretes.user.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailEquals(String email);

    boolean existsByEmail(String value);

    long count();

    User findByResetPasswordCode(String codeFromDatabase);

}
