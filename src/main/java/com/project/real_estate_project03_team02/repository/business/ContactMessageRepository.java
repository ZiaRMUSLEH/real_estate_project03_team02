package com.project.real_estate_project03_team02.repository.business;

import com.project.real_estate_project03_team02.entity.concretes.business.ContactMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ContactMessageRepository extends JpaRepository<ContactMessage,Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM ContactMessage")
    void removeAll();



}
