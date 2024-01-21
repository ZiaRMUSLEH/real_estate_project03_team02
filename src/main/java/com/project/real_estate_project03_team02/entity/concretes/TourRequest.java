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
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_id")
    private Advert advertId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_user_id")
    private User ownerUserId;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_user_id")
    private User guestUserId;
    @NotNull
    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;

}
