package com.project.real_estate_project03_team02.service;

import com.project.real_estate_project03_team02.entity.concretes.Role;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import com.project.real_estate_project03_team02.exceptions.ConflictException;
import com.project.real_estate_project03_team02.repository.RoleRepository;
import com.project.real_estate_project03_team02.utilis.Messages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository rolesRepository;

    public Role getUserRole(RoleType roleName){
        return (Role) rolesRepository.findByEnumRolesEquals(roleName).orElseThrow(
                ()-> new ConflictException(Messages.ROLE_NOT_FOUND));
    }

}
