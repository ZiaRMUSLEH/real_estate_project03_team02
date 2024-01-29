package com.project.real_estate_project03_team02.service.user;

import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.repository.user.UserRoleRepository;
import com.project.real_estate_project03_team02.exception.ConflictException;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRoleService {

	private final UserRoleRepository userRoleRepository;

	public Role getUserRole(RoleType userRoleType){
		return userRoleRepository.findByEnumRoleEquals(userRoleType)
				.orElseThrow(()-> new ConflictException(ErrorMessages.ROLE_NOT_FOUND));
	}

	public List<Role>getAllUserRole(){
		return userRoleRepository.findAll();
	}

	public void saveUserRole(RoleType roleType){
		if(userRoleRepository.existsByEnumRoleEquals(roleType)){
			throw new ConflictException(ErrorMessages.ROLE_ALREADY_EXIST);
		}
		Role userRole = Role.builder().roleName(roleType).build();
		userRoleRepository.save(userRole);
	}



}
