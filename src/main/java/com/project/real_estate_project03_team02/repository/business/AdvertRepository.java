package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.stream.DoubleStream;

@Repository
public interface AdvertRepository extends JpaRepository<Advert,Long> {


    DoubleStream findAllByOwnerUserId(Long id, Pageable pageable);
}
