package com.project.real_estate_project03_team02.repository.user;

import com.project.real_estate_project03_team02.entity.concretes.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailEquals(String email);

    boolean existsByEmail(String value);

    long count();

    @Query("SELECT count(u) FROM User u JOIN u.userRoles r WHERE r.roleName = 'CUSTOMER'")
    long countUsersWithCustomerRole();

    Optional<User> findByResetPasswordCode(String resetCode);
    @Query("SELECT u FROM User u " +
            "WHERE LOWER(u.firstName) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(u.lastName) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(u.email) LIKE LOWER(CONCAT('%', :q, '%')) " +
            "OR LOWER(u.phone) LIKE LOWER(CONCAT('%', :q, '%'))")
    Page<User> findByFirstNameOrLastNameOrEmailOrPhone(String q, Pageable pageable);

    @Modifying
    @Query("DELETE FROM User a WHERE a.builtIn = false")
    void deleteAllByBuiltInIsFalse();

    @Query("SELECT COUNT(a) FROM User a WHERE a.builtIn = false")
    int countByBuiltInIsFalse();
}

