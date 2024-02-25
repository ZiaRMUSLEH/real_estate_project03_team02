package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.TourRequest;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository

public interface TourRequestRepository extends JpaRepository<TourRequest,Long> {
    Page<TourRequest> findAllByOwnerUserId(Long authenticatedUserId, Pageable pageable);

    Optional<TourRequest> findByOwnerUserId(@NotNull User ownerUserId);
    Optional<TourRequest> findByGuestUserId(@NotNull User guestUserId);
}
