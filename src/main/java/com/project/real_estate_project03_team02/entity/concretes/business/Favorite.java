package com.project.real_estate_project03_team02.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "favorites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favorite {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_id")
    private Advert advertId;

    @NotNull
    @JsonFormat(pattern = " yyyy-MM-dd'T'HH:mm:ss'Z'")
    @JoinColumn(name = "create_at")
    private LocalDateTime createAt;
}

