package com.project.real_estate_project03_team02.controller.business;

import com.project.real_estate_project03_team02.payload.response.business.ReportResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.business.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
@AllArgsConstructor
public class ReportController {

    @Autowired
    private  final ReportService reportService;


    @GetMapping()
    //@PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('MANAGER')")
    public ResponseMessage<ReportResponse> getStatistics(){
        return reportService.getStatistics();
    }






}
