package com.project.real_estate_project03_team02.entity.concretes;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;



@Entity
@Table(name = "tour_requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "tour_date")
    private LocalDate tourDate;

    @NotNull
    @Column(name = "tour_tate")
    private LocalTime tourTime;
    @NotNull
    private Integer status;

    @ManyToOne(cascade = CascadeType.ALL)
    @Column(name = "advert_id")
    private Advert advertId;

    @ManyToOne(cascade = CascadeType.ALL)
    @Column(name = "owner_user_id")
    private User ownerUserId;

    @ManyToOne(cascade = CascadeType.ALL)
    @Column(name = "guest_user_id")
    private User guestUserId;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(name = "create_at")
    private LocalDateTime createAt;
    @Column(name = "update_at")
    private LocalDateTime updateAt;

}
