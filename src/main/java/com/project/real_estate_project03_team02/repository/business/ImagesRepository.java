package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public interface ImagesRepository extends JpaRepository<Images,Long> {
    List<Images> findAllByAdvertId(Advert advert);

    @Modifying
    @Transactional
    @Query("DELETE FROM Images")
    void removeAll();
}
