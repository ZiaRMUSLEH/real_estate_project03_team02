package com.project.real_estate_project03_team02;

import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import com.project.real_estate_project03_team02.repository.user.UserRepository;
import com.project.real_estate_project03_team02.service.user.UserRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;

import java.util.Set;


@SpringBootApplication
@RequiredArgsConstructor
public class RealEstateProject03Team02Application implements CommandLineRunner {


	private final UserRoleService userRoleService;
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;



	public static void main(String[] args) {
		SpringApplication.run(RealEstateProject03Team02Application.class, args);
	}
	@Override
	public void run(String... args) {
		if(userRoleService.getAllUserRole().isEmpty()){
			userRoleService.saveUserRole(RoleType.CUSTOMER);
			userRoleService.saveUserRole(RoleType.MANAGER);
			userRoleService.saveUserRole(RoleType.ADMIN);
		}
		Set<Role> adminRoles = Set.of(userRoleService.getUserRole(RoleType.ADMIN));
		if (userRepository.count()==0L) {
			User builtInAdmin = new User();
			builtInAdmin.setFirstName("Admin");
			builtInAdmin.setLastName("Admin");
			builtInAdmin.setPasswordHash(passwordEncoder.encode("Admin!@#123"));
			builtInAdmin.setEmail("ritzyhomes.office@gmail.com");
			builtInAdmin.setPhone("0000000000");
			builtInAdmin.setUserRoles(adminRoles);
			builtInAdmin.setBuiltIn(true);
			builtInAdmin.setCreateAt(LocalDateTime.now());
			userRepository.save(builtInAdmin);
		}

	}
}
