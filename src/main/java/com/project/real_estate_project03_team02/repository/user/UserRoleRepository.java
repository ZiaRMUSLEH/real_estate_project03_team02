package com.project.real_estate_project03_team02.repository.user;

import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<Role,Integer> {

	//select * from roles where role_type = 'ADMIN';
	@Query("SELECT r FROM UserRole r WHERE r.roleName = ?1")
	Optional<Role> findByEnumRoleEquals(RoleType userRoleType);

	@Query("SELECT (count (r)>0) FROM UserRole r WHERE r.roleName = ?1")
	boolean existsByEnumRoleEquals(RoleType userRoleType);


}
