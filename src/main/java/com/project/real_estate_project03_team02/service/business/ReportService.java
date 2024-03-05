package com.project.real_estate_project03_team02.service.business;


import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.enums.AdvertStatus;
import com.project.real_estate_project03_team02.exception.ConflictException;
import com.project.real_estate_project03_team02.payload.mappers.business.ReportMapper;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.response.business.ReportResponse;

import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.AdvertRepository;
import com.project.real_estate_project03_team02.repository.business.ReportRepository;

import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;


import com.project.real_estate_project03_team02.payload.mappers.business.ReportMapper;
import com.project.real_estate_project03_team02.payload.response.business.ReportResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReportService {


//   private final ReportRepository reportRepository;
//
//    private  final ReportMapper reportMapper;
//    private  final AdvertRepository advertRepository;
//    private final UserService userService;
//
//    private  final TourRequestService tourRequestService;
//    private  final AdvertTypesService advertTypesService;
//    private  final AdvertService advertService;






//    public ResponseMessage<ReportResponse> getStatistics() {
//        ReportResponse reportResponse=new ReportResponse();
//       Long offers =tourRequestService.getCountTourRequest();
//        reportResponse.setOffers(offers);
//       Long products =advertTypesService.getCountAdvertTypes();
//       reportResponse.setProducts(products);
//       Long brands= advertService.getCountAdvert();
//       reportResponse.setBrands(brands);
//
//                                                    //TODO kalanlari ekle dokumantasyondan bak CUSTOMER VE CATEGORIES
//        return ResponseMessage.<ReportResponse>builder()
//                .httpStatus(HttpStatus.OK)
//                .object(reportResponse)
//                .build();
//    }

//    public List<ReportResponse> getAdverts(String date1, String date2, Category category, AdvertType type, AdvertStatus status) {
//try{
//     LocalDate firstDate = LocalDate.parse(date1);
//     LocalDate secondDate = LocalDate.parse(date2);
//     //TODO category type ve statusu set etmem oazim
//    //TODO equals
//     return advertRepository.findAdvertBetweenFirstDateAndSecondDateByCategoryByTypeByStatus(firstDate, secondDate,category,type,status)
//             .stream().map(advertMapper::mapAdvertToAdvertResponse).collect(Collectors.toList());
//
//}catch(DateTimeParseException e){
//    throw new ConflictException(ErrorMessages.REPORT_WRONG_DATE_FORMAT_MESSAGE);
//}
//
//    }
//
//    public List<ReportResponse> getMostPopularProperties(int amount) {
//        reportRepository.findMostPopularProperties(amount);
//        return null;
//    }


// bu kismi duzenle yeniiiii
    private final UserService userService;
    private  final TourRequestService tourRequestService;
    private  final AdvertTypesService advertTypesService;
    private  final ReportMapper reportMapper;



    public ResponseMessage<ReportResponse> getStatistics() {
        ReportResponse reportResponse=new ReportResponse();
       Long offers =tourRequestService.getCountTourRequest();
        reportResponse.setOffers(offers);
       Long products =advertTypesService.getCountAdvertTypes();
       reportResponse.setProducts(products);

        return ResponseMessage.<ReportResponse>builder()
                .httpStatus(HttpStatus.OK)
                .object(reportResponse)
                .build();
    }

}

