package com.project.real_estate_project03_team02.repository;

import com.project.real_estate_project03_team02.entity.concretes.Role;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("SELECT r FROM Role r WHERE r.roleName = ?1")
    Optional<Object> findByEnumRolesEquals(RoleType roleName);
}
