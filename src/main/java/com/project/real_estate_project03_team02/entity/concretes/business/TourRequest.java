package com.project.real_estate_project03_team02.entity.concretes.business;


import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.TourRequestStatus;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * The TourRequest class represents a tour request made by a guest user for visiting a real estate property.
 * It includes details such as the tour date, tour time, status of the request, associated advertisement,
 * owner user, guest user, and timestamps for creation and last update.
 */
@Entity
@Table(name = "tour_requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TourRequest {

    /** The unique identifier for the tour request. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** The date for the scheduled tour. */
    @NotNull
    @Column(name = "tour_date")
    private LocalDate tourDate;

    /** The time for the scheduled tour. */
    @NotNull
    @Column(name = "tour_time")
    private LocalTime tourTime;

    /** The status of the tour request. */
    @NotNull
    @Column(columnDefinition = "integer default 0")
    @Enumerated(EnumType.ORDINAL)
    private TourRequestStatus status;

    /** The advertisement associated with the tour request. */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "advert_id")
    private Advert advertId;

    /** The owner user who manages the property associated with the tour request. */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_user_id")
    private User ownerUserId;

    /** The guest user who requested the tour. */
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "guest_user_id")
    private User guestUserId;

    /** The date and time when the tour request was created. */
    @NotNull
    @CreationTimestamp
    private LocalDateTime createdAt;

    /** The date and time when the tour request was last updated. */
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
