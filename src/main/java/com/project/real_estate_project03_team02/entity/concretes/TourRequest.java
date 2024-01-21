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
    private LocalDate tour_date;

    @NotNull
    private LocalTime tour_time;
    @NotNull
    private Integer status;

//    @ManyToOne(cascade = CascadeType.ALL)
//    private Advert advert_id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User owner_user_id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User guest_user_id;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime create_at;

    private LocalDateTime update_at;

}
