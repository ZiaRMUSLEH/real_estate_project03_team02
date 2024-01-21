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
<<<<<<< HEAD
    private String first_name;
=======
    public String firstName;
>>>>>>> ozkan

    @NotNull
    @Size(max = 30)
<<<<<<< HEAD
    private String last_name;
=======
    public String lastName;
>>>>>>> ozkan

    @NotNull
    @Size(max = 60)
    private String email;

    @NotNull
    @Size(max = 300)
    private String message;

<<<<<<< HEAD
    private LocalDateTime create_at;
=======
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createAt;
>>>>>>> ozkan



}
