package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import com.project.real_estate_project03_team02.exception.ResourceNotFoundException;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertTypesMapper;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.request.business.AdvertTypesRequest;
import com.project.real_estate_project03_team02.payload.response.business.AdvertTypesResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.AdvertTypesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdvertTypesService {

    private final AdvertTypesRepository advertTypesRepository;
    private final AdvertTypesMapper advertTypesMapper;
//    private final AdvertTypesService advertTypesService;


    public List<AdvertTypesResponse> getAllAdvertTypes(){

        return advertTypesRepository.findAll()
                .stream()
                .map(advertTypesMapper::mapAdvertTypesToAdvertTypesResponse)
                .collect(Collectors.toList());
    }



//    public  ResponseMessage<AdvertTypesResponse> saveAdvertTypes(AdvertTypesRequest advertTypesRequest) {
//        AdvertType advertType= advertTypesMapper.mapAdvertTypeRequestToAdvertType(advertTypesRequest);
//
//        AdvertType savedAdvertTypes = advertTypesRepository.save(advertType);
//        return ResponseMessage.<AdvertTypesResponse>builder()
//                .message(SuccessMessages.ADVERT_TYPES_CREATED)
//                .object(advertTypesMapper.mapAdvertTypesToAdvertTypesResponse(savedAdvertTypes))
//                .httpStatus(HttpStatus.CREATED)
//                .build();
//    }
//
//    public ResponseMessage<AdvertTypesResponse> getAdvertTypesById(Long id) {
//       AdvertType advertType=isAdvertTypeById(id);
//
//        return ResponseMessage.<AdvertTypesResponse>builder()
//                .object(advertTypesMapper.mapAdvertTypesToAdvertTypesResponse(advertType))
//                .build();
//    }
//
//    private AdvertType isAdvertTypeById(Long id) {
//        return advertTypesRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException(String.format(ErrorMessages.Advert_Type_NOT_FOUND_MESSAGE,id)));
//    }
}
