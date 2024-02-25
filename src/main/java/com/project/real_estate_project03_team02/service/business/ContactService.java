package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Contact;
import com.project.real_estate_project03_team02.entity.concretes.business.ContactMessage;
import com.project.real_estate_project03_team02.payload.mappers.business.ContactMessageMapper;
import com.project.real_estate_project03_team02.payload.mappers.business.ContactMessageMapper;
import com.project.real_estate_project03_team02.payload.response.business.ContactMessageResponse;
import com.project.real_estate_project03_team02.payload.response.business.ContactMessageResponse;
import com.project.real_estate_project03_team02.repository.business.ContactRepository;
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final PageableHelper pageableHelper;

    private final ContactMessageMapper contactMessageMapper;


        public Page<ContactMessageResponse> getAllContactMessages(int page, int size, String sort, String asc ){
            Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, asc);
            Page<ContactMessage> contactMessages =  contactRepository.findAll(pageable);
           return contactMessages.map(contactMessageMapper::mapContactMessageToContactMessageResponse);
        }

}
