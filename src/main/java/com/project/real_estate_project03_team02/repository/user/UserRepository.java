package com.project.real_estate_project03_team02.repository.user;

import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByEmailEquals(String email);

    boolean existsByEmail(String value);

    long count();

    User findByResetPasswordCode(String resetCode);

//    @Query("SELECT COUNT(u) FROM User u WHERE :customerRole MEMBER OF u.userRoles")
//    long countUsersWithCustomerRole(@Param("customerRole") Role customerRole);

    @Query("SELECT count(u) FROM User u JOIN u.userRoles r WHERE r.roleName = 'CUSTOMER'")
    long countUsersWithCustomerRole();

}
