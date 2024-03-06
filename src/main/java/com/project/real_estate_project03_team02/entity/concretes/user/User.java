package com.project.real_estate_project03_team02.entity.concretes.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Entity class representing a user in the real estate project.
 */
@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    /**
     * Unique identifier for the user.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The first name of the user.
     */
    @NotNull
    @Column(name = "first_name", length = 30)
    private String firstName;

    /**
     * The last name of the user.
     */
    @NotNull
    @Column(name = "last_name", length = 30)
    private String lastName;

    /**
     * The email address of the user.
     */
    @NotNull
    @Column(length = 80)
    private String email;

    /**
     * The phone number of the user.
     */
    @NotNull
    private String phone;

    /**
     * The hashed password of the user.
     */
    @NotNull
    @Column(name = "password_hash")
    private String passwordHash;

    /**
     * The code used for resetting the user's password.
     */
    @Column(name = "reset_password_code")
    private String resetPasswordCode;

    /**
     * Indicates whether the user is built-in or not.
     */
    @NotNull
    @Column(name = "built_in", columnDefinition = "boolean default false")
    private boolean builtIn;

    /**
     * The date and time when the user was created.
     */
    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(name = "create_at")
    private LocalDateTime createAt;

    /**
     * The date and time when the user was last updated.
     */
    @Column(name = "update_at")
    private LocalDateTime updateAt;

    /**
     * Set of roles assigned to the user.
     */
    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> userRoles;
}
