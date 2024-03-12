package com.project.real_estate_project03_team02.payload.mappers.business;


import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponseIdAndTitle;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class AdvertMapperIdAndTitle {


    public AdvertResponseIdAndTitle mapAdvertToAdvertResponseIdAndTitle(Advert advert){
        return AdvertResponseIdAndTitle.builder()
                .id(advert.getId())
                .title(advert.getTitle())
                .build();
    }

}
