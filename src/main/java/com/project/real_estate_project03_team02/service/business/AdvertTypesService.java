package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import com.project.real_estate_project03_team02.entity.enums.AdvertTypes;
import com.project.real_estate_project03_team02.exception.BadRequestException;
import com.project.real_estate_project03_team02.exception.ConflictException;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertTypesMapper;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.business.AdvertTypesRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertTypesResponse;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.AdvertTypesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertTypesService {

    private final AdvertTypesRepository advertTypesRepository;
    private final AdvertTypesMapper advertTypesMapper;



    public List<AdvertTypesResponse> getAllAdvertTypes(){

        return advertTypesRepository.findAll()
                .stream()
                .map(advertTypesMapper::mapAdvertTypesToAdvertTypesResponse)
                .collect(Collectors.toList());
    }

    public ResponseMessage<AdvertTypesResponse> getAdvertTypesById(Long id) {
       AdvertType advertType=isAdvertTypeById(id);

        return ResponseMessage.<AdvertTypesResponse>builder()
                .object(advertTypesMapper.mapAdvertTypesToAdvertTypesResponse(advertType))
                .build();
    }

    private AdvertType isAdvertTypeById(Long id) {

        return advertTypesRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(ErrorMessages.Advert_Type_NOT_FOUND_MESSAGE,id)));
    }

        public  ResponseMessage<AdvertTypesResponse> saveAdvertTypes(HttpServletRequest httpServletRequest) {
            //todo  --------------------------------------------
            //yeni gelen advert type a eklemm gerekmiyor mu?
           // enum da title string degil adverttype olarak oldugu icin geleni nasil enum a ekleyecegim
            //todo  --------------------------------------------


            AdvertType advertType= new AdvertType();
          String addedTitle=  httpServletRequest.getParameter("title");

            advertType.setTitle(AdvertTypes.valueOf(addedTitle));

                    AdvertType savedAdvertTypes = advertTypesRepository.save(advertType);
            return ResponseMessage.<AdvertTypesResponse>builder()
                    .object(advertTypesMapper.mapAdvertTypesToAdvertTypesResponse(savedAdvertTypes))
                    .message(SuccessMessages.ADVERT_TYPES_CREATED)
                    .httpStatus(HttpStatus.CREATED)
                    .build();
        }



    public ResponseMessage<AdvertTypesResponse> updateAdvertTypesById(Long id) {
        AdvertType advertType = isAdvertTypeById(id);
        if (Objects.equals(AdvertTypes.RENT,advertType.getTitle()) )
               {
                   advertType.setTitle(AdvertTypes.SALE);

        }
        else if (Objects.equals(AdvertTypes.SALE,advertType.getTitle())) advertType.setTitle(AdvertTypes.RENT);

        AdvertType savedAdvertType=advertTypesRepository.save(advertType);


        return ResponseMessage.<AdvertTypesResponse>builder()
                .message(SuccessMessages.ADVERT_TYPE_UPDATE)
                .httpStatus(HttpStatus.OK)
                .object(advertTypesMapper.mapAdvertTypesToAdvertTypesResponse(savedAdvertType))
                .build();



    }

    public ResponseMessage<AdvertTypesResponse> deleteAdvertTypeById(Long id) {
        AdvertType deletedAdvertType= isAdvertTypeById(id);
        advertTypesRepository.deleteById(id);
        return ResponseMessage.<AdvertTypesResponse>builder()
                .message(SuccessMessages.ADVERT_TYPES_DELETED)
                .object(advertTypesMapper.mapAdvertTypesToAdvertTypesResponse(deletedAdvertType))
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public long getCountAdvertTypes() {
        return  advertTypesRepository.count();

    }

    public AdvertType findById(Long id) {
        return advertTypesRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(ErrorMessages.Advert_Type_NOT_FOUND_MESSAGE,id)));
    }
}
