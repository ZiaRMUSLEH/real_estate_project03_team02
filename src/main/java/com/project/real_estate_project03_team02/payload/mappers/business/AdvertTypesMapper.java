package com.project.real_estate_project03_team02.payload.mappers.business;

import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import com.project.real_estate_project03_team02.entity.enums.AdvertTypes;
import com.project.real_estate_project03_team02.payload.request.business.AdvertTypesRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertTypesResponse;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AdvertTypesMapper {





//    public AdvertType mapAdvertTypeRequestToAdvertType(AdvertTypesRequest advertTypesRequest){
//        return AdvertType.builder()
//                .title(advertTypesRequest.getTitle())
//                .build();
//    }

    public AdvertTypesResponse mapAdvertTypesToAdvertTypesResponse(AdvertType advertType) {
        return AdvertTypesResponse.builder()
                .id(advertType.getId())
                .title(String.valueOf(advertType.getTitle()))
                .build();
    }


}
