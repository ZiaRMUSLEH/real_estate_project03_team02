package com.project.real_estate_project03_team02.service.business;

import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.payload.mappers.business.ReportMapper;
import com.project.real_estate_project03_team02.payload.messages.SuccessMessages;
import com.project.real_estate_project03_team02.payload.response.business.ReportResponse;
import com.project.real_estate_project03_team02.payload.response.business.TourRequestResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.ReportRepository;
import com.project.real_estate_project03_team02.service.helper.PageableHelper;
import com.project.real_estate_project03_team02.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportService {

   private final ReportRepository reportRepository;

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
