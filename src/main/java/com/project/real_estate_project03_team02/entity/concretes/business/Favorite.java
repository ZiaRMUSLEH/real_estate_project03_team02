package com.project.real_estate_project03_team02.entity.concretes.business;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

/**
 * The Favorite class represents a favorite entity in the real estate project.
 * It contains information about users' favorites on specific advertisements.
 */
@Entity
@Table(name = "favorites")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Favorite {

    /**
     * The unique identifier for the favorite.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * The user who added this favorite.
     */
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userId;

    /**
     * The advertisement that is marked as favorite.
     */
    @NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_id")
    private Advert advertId;

    /**
     * The timestamp when this favorite was created.
     */
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'")
    @JoinColumn(name = "create_at")
    private LocalDateTime createAt;

    // Constructors, Getters, and Setters are provided by Lombok annotations.
}


