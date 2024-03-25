package com.project.real_estate_project03_team02.payload.mappers.business;


import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
@RequiredArgsConstructor
public class AdvertListToAdvertResponseListMapper {

    private final AdvertToAdvertResponseMapper advertToAdvertResponseMapper;

    public List<AdvertResponse> mapAdvertListToAdvertResponseList(List<Advert> adverts) {
        return adverts.stream()
                .map(advertToAdvertResponseMapper::mapAdvertToAdvertResponse)
                .collect(Collectors.toList());
    }

}
