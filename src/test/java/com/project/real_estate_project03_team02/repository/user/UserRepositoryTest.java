package com.project.real_estate_project03_team02.repository.user;

import com.project.real_estate_project03_team02.entity.concretes.user.Role;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import com.project.real_estate_project03_team02.service.helper.TestConfig;
import com.project.real_estate_project03_team02.service.user.UserRoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import({UserRoleService.class, TestConfig.class})
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    String email = "test@example.com";
    RoleType roleType = RoleType.CUSTOMER;
    Set<Role> userRole= new HashSet<>(roleType.ordinal());
    User user = new User(1L,"Gica","Gina",email,"2435649058","password",null,false, LocalDateTime.now(),null,userRole);
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Initialize annotated mocks
    }

    @Test
    void findByEmailEquals() {
        userRepository.save(user);
        assertNotNull(userRepository.findByEmailEquals(email));
        assertEquals(email, Objects.requireNonNull(userRepository.findByEmailEquals(email).orElse(null)).getEmail());
    }

    @Test
    void existsByEmail() {
        userRepository.save(user);
        assertEquals(true,userRepository.existsByEmail(email));
    }
    @Test
    void count() {
        long expectedCount = 1L;
        UserRepository mockUserRepository = mock(UserRepository.class);
        when(mockUserRepository.count()).thenReturn(expectedCount);
        mockUserRepository.save(user);
        assertEquals(expectedCount, mockUserRepository.count());

        verify(mockUserRepository, times(1)).count();
    }



}
