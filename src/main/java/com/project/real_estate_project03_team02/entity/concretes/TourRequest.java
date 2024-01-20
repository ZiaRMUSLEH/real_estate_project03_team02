package com.project.real_estate_project03_team02.entity.concretes;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    private LocalDateTime tour_date;
    @NotNull
    private LocalDateTime tour_time;
    @NotNull
    @Column(columnDefinition = "integer default 0")
    private Integer status;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advert_id")
    private Advert advert_id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id")
    private User owner_user_id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_user_id")
    private User guest_user_id;
    @NotNull
    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

}
