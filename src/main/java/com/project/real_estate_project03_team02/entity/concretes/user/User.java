package com.project.real_estate_project03_team02.entity.concretes.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;


@Entity
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "first_name",length = 30)
    private String firstName;
    @NotNull
    @Column(name = "last_name",length = 30)
    private String lastName;
    @NotNull
    @Column(length = 80)
    private String email;

    @NotNull
    private String phone;
    @NotNull
    @Column(name = "password_hash")
    private String passwordHash;
    @Column(name = "reset_password_code")
    private String resetPasswordCode;
    @NotNull
    //@Getter(AccessLevel.NONE)
    //@Setter(AccessLevel.NONE)
    @Column(name = "built_in",columnDefinition = "boolean default false")
    private boolean builtIn;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(name = "create_at")
    private LocalDateTime createAt;

    @Column(name = "update_at")
    private LocalDateTime updateAt;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> userRoles;
}
