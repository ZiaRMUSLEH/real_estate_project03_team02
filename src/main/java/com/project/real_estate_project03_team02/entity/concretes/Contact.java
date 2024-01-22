package com.project.real_estate_project03_team02.entity.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity
@Table(name = "contacts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Size(max = 30)
    @Column(name = "first_name",nullable = false)
    private String firstName;

    //public String firstName;


    @NotNull
    @Size(max = 30)
    @Column(name = "last_name",nullable = false)
    private String lastName;

    //public String lastName;


    @NotNull
    @Size(max = 60)
    @Column(nullable = false)
    private String email;

    @NotNull
    @Size(max = 300)
    private String message;

  //  private LocalDateTime create_at;
    @Column(name = "create_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Setter(AccessLevel.NONE)
    private LocalDateTime createAt;




}
