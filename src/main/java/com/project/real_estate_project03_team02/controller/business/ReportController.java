package com.project.real_estate_project03_team02.controller.business;

import com.project.real_estate_project03_team02.entity.concretes.business.Advert;
import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.concretes.business.TourRequest;
import com.project.real_estate_project03_team02.entity.concretes.user.User;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponse;
import com.project.real_estate_project03_team02.payload.response.business.AdvertResponseIdAndTitle;
import com.project.real_estate_project03_team02.payload.response.business.ReportResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.service.business.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    @GetMapping("/adverts")
    //@PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('MANAGER')")
    public List<AdvertResponse> getAdverts(@RequestParam String date1,
                                           @RequestParam String date2,
                                           @RequestParam Category category,
                                           @RequestParam AdvertType type,
                                           @RequestParam Advert status){
        return reportService.getAdverts(date1, date2, category, type, status);
    }

    @GetMapping("/most-popular-properties")
    //@PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('MANAGER')")
    public List<AdvertResponseIdAndTitle> getMostPopularProperties(@RequestParam int amount) {

        return reportService.getMostPopularProperties(amount);
    }

    @GetMapping("/users")
    //@PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('MANAGER')")
    public List<User> getUsersByRole(@RequestParam String role){
        return reportService.getUsersByRole(role);
    }



    @GetMapping("/dashboard")
    public List<TourRequest>  getTourRequest(@RequestParam String date1,
                                             @RequestParam String date2,
                                             @RequestParam TourRequest status) {
        return reportService.getTourRequest(date1, date2,status);
    }

}
