package com.project.real_estate_project03_team02.payload.mappers.business;


import com.project.real_estate_project03_team02.entity.concretes.business.Contact;
import com.project.real_estate_project03_team02.payload.request.business.ContactRequest;
import com.project.real_estate_project03_team02.payload.response.business.ContactResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class ContactMapper {

    public Contact mapContactToContactRequest (ContactRequest contactRequest){
        return Contact.builder()
                .id(contactRequest.getId())
                .email(contactRequest.getEmail())
                .firstName(contactRequest.getFirstName())
                .lastName(contactRequest.getLastName())
                .build();
    }

    public ContactResponse mapContactToContactResponse (Contact contact){
        return ContactResponse.builder()
                .id(contact.getId())
                .email(contact.getEmail())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .build();
    }
}
