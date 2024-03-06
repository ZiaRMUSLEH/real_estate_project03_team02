package com.project.real_estate_project03_team02.entity.concretes.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Represents a role entity in the application.
 * Each role defines a set of permissions and privileges that users can have.
 */
@Entity
@Table(name = "roles")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role {

    /**
     * Unique identifier for the role.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the role, represented by an enumeration of RoleType.
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "role_name")
    private RoleType roleName;

    /**
     * Set of users associated with this role.
     * This relationship is mapped through the 'userRoles' field in the User class.
     */
    @JsonIgnore
    @ManyToMany(mappedBy = "userRoles", cascade = CascadeType.ALL)
    private Set<User> users;
}

