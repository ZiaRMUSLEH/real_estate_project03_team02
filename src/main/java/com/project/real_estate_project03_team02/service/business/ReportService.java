package com.project.real_estate_project03_team02.service.business;


import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.entity.enums.RoleType;
import com.project.real_estate_project03_team02.exception.ConflictException;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertMapper;
import com.project.real_estate_project03_team02.payload.mappers.business.AdvertMapperIdAndTitle;
import com.project.real_estate_project03_team02.payload.messages.ErrorMessages;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import com.project.real_estate_project03_team02.payload.response.business.ReportResponse;

import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.AdvertRepository;

import com.project.real_estate_project03_team02.repository.user.UserRoleRepository;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import java.time.LocalDate;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ReportService {


//   private final ReportRepository reportRepository;
//
      private  final AdvertMapper advertMapper;
      public final AdvertMapperIdAndTitle advertMapperIdAndTitle;

      private  final AdvertRepository advertRepository;
      private final UserService userService;
//
      private  final TourRequestService tourRequestService;
      private  final AdvertTypesService advertTypesService;
      private  final AdvertService advertService;
      private  final CategoryService categoryService;
      private  final UserRoleRepository userRoleRepository;


    public ResponseMessage<ReportResponse> getStatistics() {
        ReportResponse reportResponse=new ReportResponse();
        Long offers =tourRequestService.getCountTourRequest();
        reportResponse.setOffers(offers);
        Long products =advertTypesService.getCountAdvertTypes();
        reportResponse.setProducts(products);
        Long brands= advertService.getCountAdvert();
        reportResponse.setBrands(brands);
        Long categories= categoryService.getCountCategory();
        reportResponse.setCategories(categories);

        Long customer= userService.getCountCustomer();
        reportResponse.setCustomers(customer);


        return ResponseMessage.<ReportResponse>builder()
                .httpStatus(HttpStatus.OK)
                .object(reportResponse)
                .build();
    }






    public List<AdvertResponse> getAdverts(String date1, String date2, Category category, AdvertType type, Advert status) {
try{
     LocalDate firstDate = LocalDate.parse(date1);
     LocalDate secondDate = LocalDate.parse(date2);


     return advertRepository.findAdvertBetweenFirstDateAndSecondDateByCategoryByTypeByStatus(firstDate, secondDate,category,type, status)
             .stream().map(advertMapper::mapAdvertToAdvertResponse).collect(Collectors.toList());

}catch(DateTimeParseException e){
    throw new ConflictException(ErrorMessages.REPORT_WRONG_FORMAT_MESSAGE);
}
    }

    public ResponseEntity<List<Advert>> getMostPopularProperties(int amount) {

        ResponseEntity<List<Advert>> listResponseEntity = (ResponseEntity<List<Advert>>) advertRepository
                .findMostPopularProperties(amount)
                .stream()
                .map(advertMapperIdAndTitle::mapAdvertToAdvertResponseIdAndTitle)
                .collect(Collectors.toList());
        return listResponseEntity;

    }


//    public List<User>  getUsersByRole(String userRole) {
//        //Burada user dondurmesi gerekiyor ama repoddan da role geliyor
//        //List<Role> u degistirdim  List<User>
//        return  userRoleRepository.findByRole((userRole));
//    }
}

