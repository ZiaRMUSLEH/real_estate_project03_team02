package com.project.real_estate_project03_team02.entity.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name="logs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String log;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private  User userId;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_id")
    private Advert advertId;


    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern =  "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @Column(name = "create_at")
    private LocalDateTime createAt;

}
