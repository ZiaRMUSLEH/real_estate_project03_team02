package com.project.real_estate_project03_team02;

import com.project.real_estate_project03_team02.entity.enums.RoleType;
import com.project.real_estate_project03_team02.service.user.UserRoleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RealEstateProject03Team02Application implements CommandLineRunner {



	private final UserRoleService userRoleService;

	public RealEstateProject03Team02Application(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

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

	}
}
