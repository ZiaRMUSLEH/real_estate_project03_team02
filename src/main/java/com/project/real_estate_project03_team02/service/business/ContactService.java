package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.payload.response.business.ContactResponse;
import com.project.real_estate_project03_team02.repository.business.ContactRepository;
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class ContactService {

    private final ContactRepository contactRepository;
    private final PageableHelper pageableHelper;
        public Page<ContactResponse> getAllContactMessages(HttpServletRequest httpServletRequest, int page, int size,String sort,String asc ){
            Long authenticatedUserId = (Long) httpServletRequest.getAttribute("id");
            Pageable pageable = pageableHelper.getPageableWithProperties(page, size, sort, asc);
            return contactRepository.findAllById(authenticatedUserId,pageable).map()


        }

}
