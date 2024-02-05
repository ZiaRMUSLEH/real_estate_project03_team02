package com.project.real_estate_project03_team02.repository.user;

import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import com.project.real_estate_project03_team02.service.helper.TestConfig;
import com.project.real_estate_project03_team02.service.user.UserRoleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({UserRoleService.class, TestConfig.class})
class UserRoleRepositoryTest {

    @Mock
    private UserRoleRepository userRoleRepository;


    RoleType roleType = RoleType.CUSTOMER;
    Role expectedRole = new Role(1L,RoleType.CUSTOMER,null);

    @Test
    void findByEnumRoleEquals_WhenRoleExists_ShouldReturnRole() {
        when(userRoleRepository.findByEnumRoleEquals(roleType)).thenReturn(Optional.of(expectedRole));
        Optional<Role> result = userRoleRepository.findByEnumRoleEquals(roleType);
        assertEquals(Optional.of(expectedRole), result);
        verify(userRoleRepository, times(1)).findByEnumRoleEquals(roleType);
    }




    @Test
    void findByEnumRoleEquals_WhenRoleDoesNotExist_ShouldReturnEmptyOptional() {
        when(userRoleRepository.findByEnumRoleEquals(roleType)).thenReturn(Optional.empty());
        Optional<Role> result = userRoleRepository.findByEnumRoleEquals(roleType);
        assertTrue(result.isEmpty());
        verify(userRoleRepository, times(1)).findByEnumRoleEquals(roleType);
    }

    @Test
    void existsByEnumRoleEquals_WhenRoleExists_ShouldReturnTrue() {
        when(userRoleRepository.existsByEnumRoleEquals(roleType)).thenReturn(true);
        boolean result = userRoleRepository.existsByEnumRoleEquals(roleType);
        assertTrue(result);
        verify(userRoleRepository, times(1)).existsByEnumRoleEquals(roleType);
    }

    @Test
    void existsByEnumRoleEquals_WhenRoleDoesNotExist_ShouldReturnFalse() {
        when(userRoleRepository.existsByEnumRoleEquals(roleType)).thenReturn(false);

        // Act
        boolean result = userRoleRepository.existsByEnumRoleEquals(roleType);

        // Assert
        assertFalse(result);

        // Verify that the repository method was called with the correct parameter
        verify(userRoleRepository, times(1)).existsByEnumRoleEquals(roleType);
    }
}
