package com.project.real_estate_project03_team02.service.business;

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
