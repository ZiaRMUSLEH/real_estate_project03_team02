package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.ContactMessage;
import com.project.real_estate_project03_team02.payload.mappers.business.ContactMessageMapper;
import com.project.real_estate_project03_team02.payload.request.business.ContactMessageRequest;
import com.project.real_estate_project03_team02.payload.response.business.ContactMessageResponse;
import com.project.real_estate_project03_team02.repository.business.ContactMessageRepository;
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContactMessageService {

    private final ContactMessageRepository contactMessageRepository;
    private final PageableHelper pageableHelper;

    private final ContactMessageMapper contactMessageMapper;


        public Page<ContactMessageResponse> getAllContactMessages(int page, int size, String sort, String asc ){
            Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, asc);
            Page<ContactMessage> contactMessages =  contactMessageRepository.findAll(pageable);
           return contactMessages.map(contactMessageMapper::mapContactMessageToContactMessageResponse);


        }

    public ContactMessageResponse saveContactMessage(ContactMessageRequest contactMessageRequest) {
        // 1. Map the ContactMessageRequest to a ContactMessage entity
             ContactMessage contactMessage=contactMessageMapper.mapContactMessageRequestToContactMessage(contactMessageRequest);
        // 2. Save the mapped ContactMessage entity
             ContactMessage saveContactMessage=contactMessageRepository.save(contactMessage);
        // 3. Map the saved ContactMessage entity back to a ContactMessageResponse
             ContactMessageResponse contactMessageResponse=contactMessageMapper.mapContactMessageToContactMessageResponse(saveContactMessage);


        // 4. Return a ResponseEntity with the mapped ContactMessageResponse
        return contactMessageResponse;

    }
}
