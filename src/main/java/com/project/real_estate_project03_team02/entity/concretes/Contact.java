package com.project.real_estate_project03_team02.entity.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull(message = "Cannot be left blank")
    @Size(max = 30)
    public String first_name;

    @NotNull(message = "Cannot be left blank")
    @Size(max = 30)
    public String last_name;

    @NotNull
    @Size(max = 60)
    @Email(message = "Please enter your email in proper format")
    public String email;

    @NotNull
    @Size(max = 300)
    public String message;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime create_at;



}
