package com.project.real_estate_project03_team02.repository.user;

import com.project.real_estate_project03_team02.entity.concretes.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

	boolean existsByFirstName(String username);
	boolean existsByEmail(String email);

	boolean existsByPhone(String phoneNumber);

    List<User> findByFirstName(String username);

    List<User> findByFirstNameOrLastName(String name, String surname);

	User findByFirstNameEquals(String username);
}
