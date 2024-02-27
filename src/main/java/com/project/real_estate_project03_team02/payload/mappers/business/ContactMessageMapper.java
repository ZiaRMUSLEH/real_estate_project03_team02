package com.project.real_estate_project03_team02.payload.mappers.business;


import com.project.real_estate_project03_team02.entity.concretes.business.ContactMessage;
import com.project.real_estate_project03_team02.payload.request.business.ContactMessageRequest;
import com.project.real_estate_project03_team02.payload.response.business.ContactMessageResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ContactMessageMapper {

    public ContactMessage mapContactMessageRequestToContactMessage (ContactMessageRequest contactRequest){
        return ContactMessage.builder()
                .id(contactRequest.getId())
                .email(contactRequest.getEmail())
                .firstName(contactRequest.getFirstName())
                .lastName(contactRequest.getLastName())
                .build();
    }

    public ContactMessageResponse mapContactMessageToContactMessageResponse(ContactMessage contact){
        return ContactMessageResponse.builder()
                .id(contact.getId())
                .email(contact.getEmail())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .build();
    }
}
