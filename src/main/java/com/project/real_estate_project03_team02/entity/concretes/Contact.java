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
    public Long id;

    @NotNull
    @Size(max = 30)

    @Column(name = "first_name")
    private String first_name;

    //public String firstName;


    @NotNull
    @Size(max = 30)
    @Column(name = "last_name")
    private String lastName;

    //public String lastName;


    @NotNull
    @Size(max = 60)
    private String email;

    @NotNull
    @Size(max = 300)
    private String message;

  //  private LocalDateTime create_at;
    @Column(name = "create_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createAt;




}
