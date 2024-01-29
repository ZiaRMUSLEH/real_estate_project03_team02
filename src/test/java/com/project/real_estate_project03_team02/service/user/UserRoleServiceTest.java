package com.project.real_estate_project03_team02.service.user;

import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.repository.user.UserRoleRepository;
import com.project.real_estate_project03_team02.exception.ConflictException;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserRoleServiceTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleService userRoleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getUserRoleWhenRoleExistsShouldReturnRole() {
        // Arrange
        RoleType roleType = RoleType.CUSTOMER;

        Role mockRole = Role.builder().roleName(roleType).build();

        when(userRoleRepository.findByEnumRoleEquals(roleType)).thenReturn(Optional.of(mockRole));

        // Act
        Role resultRole = userRoleService.getUserRole(roleType);

        // Assert
        assertNotNull(resultRole);
        assertEquals(roleType, resultRole.getRoleName());
    }

    @Test
    void getUserRoleWhenRoleDoesNotExistShouldThrowConflictException() {
        // Arrange
        RoleType roleType = RoleType.CUSTOMER;

        when(userRoleRepository.findByEnumRoleEquals(roleType)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ConflictException.class, () -> userRoleService.getUserRole(roleType));
    }

    @Test
    void saveUserRoleWhenRoleDoesNotExistShouldSaveRole() {
        // Arrange
        RoleType roleType = RoleType.CUSTOMER;

        when(userRoleRepository.existsByEnumRoleEquals(roleType)).thenReturn(false);

        // Act
        userRoleService.saveUserRole(roleType);

        // Assert
        verify(userRoleRepository, times(1)).save(any(Role.class));
    }

    @Test
    void saveUserRoleWhenRoleExistsShouldThrowConflictException() {
        // Arrange
        RoleType roleType = RoleType.CUSTOMER;

        when(userRoleRepository.existsByEnumRoleEquals(roleType)).thenReturn(true);

        // Act and Assert
        assertThrows(ConflictException.class, () -> userRoleService.saveUserRole(roleType));

        // Ensure that userRepository.save is never called
        verify(userRoleRepository, never()).save(any(Role.class));
    }
}
