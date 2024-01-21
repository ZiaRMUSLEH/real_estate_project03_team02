package com.project.real_estate_project03_team02.entity.concretes;


import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;




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
    private LocalDateTime tourDate;
    @NotNull
    private LocalDateTime tourTime;
    @NotNull
    @Column(columnDefinition = "integer default 0")
    private Integer status;


    @ManyToOne(cascade = CascadeType.ALL)
    private Advert advert_id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User owner_user_id;

    @ManyToOne(cascade = CascadeType.ALL)
    private User guest_user_id;


    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "advert_id")
    private Advert advertId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_user_id")
    private User ownerUserId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "guest_user_id")
    private User guestUserId;
    @NotNull
    @CreationTimestamp
    private LocalDateTime created_at;
    @UpdateTimestamp
    private LocalDateTime updated_at;

}
