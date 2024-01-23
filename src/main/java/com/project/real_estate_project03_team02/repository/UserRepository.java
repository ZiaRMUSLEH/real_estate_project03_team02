package com.project.real_estate_project03_team02.repository;

import com.project.real_estate_project03_team02.entity.concretes.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByEmailEquals(String email);

    boolean existsByEmail(String value);
}
