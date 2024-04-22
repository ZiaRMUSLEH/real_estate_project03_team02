package com.project.real_estate_project03_team02.repository.user;

import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserRoleRepository extends JpaRepository<Role,Integer> {

	@Query("SELECT r FROM Role r WHERE r.roleName = ?1")
	Optional<Role> findByEnumRoleEquals(RoleType roleName);


	@Query("SELECT (count (r)>0) FROM Role r WHERE r.roleName = ?1")
	boolean existsByEnumRoleEquals(RoleType roleName);

	@Modifying
	@Transactional
	@Query("DELETE FROM Role")
	void removeAll();


}
