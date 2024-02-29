package com.project.real_estate_project03_team02.controller.business;

import com.project.real_estate_project03_team02.entity.concretes.business.AdvertType;
import com.project.real_estate_project03_team02.entity.concretes.business.Category;
import com.project.real_estate_project03_team02.entity.enums.AdvertStatus;
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

//    @Autowired
//    private  final ReportService reportService;


//    @GetMapping()
//    //@PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('MANAGER')")
//    public ResponseMessage<ReportResponse> getStatistics(){
//        return reportService.getStatistics();
//    }


//    @GetMapping("/adverts")
//    //@PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('MANAGER')")
//    public List<ReportResponse> getAdverts(@RequestParam String date1,
//                                           @RequestParam String date2,
//                                           @RequestParam Category category,
//                                           @RequestParam AdvertType type,
//                                           @RequestParam AdvertStatus status){
//        return reportService.getAdverts(date1, date2, category, type, status);
//    }

//    @GetMapping("/most-popular-properties")
//    //@PreAuthorize("hasAnyAuthority('ADMIN') or hasAnyAuthority('MANAGER')")
//    public List<ReportResponse> getMostPopularProperties(@RequestParam int amount){
//        return reportService.getMostPopularProperties(amount);
//    }


//
//    @GetMapping("/users")
//    public ResponseEntity<List<Report>> getUsers(@RequestParam(required = false) String query,
//                                                 @RequestParam(required = false) String role) {
//        if (query == null && role == null) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(reportService.getUsers(query, role));
//    }
//
//    @GetMapping("/most-popular-properties")
//    public ResponseEntity<List<Report>> getMostPopularProperties(@RequestParam int amount) {
//        if (amount <= 0) {
//            return ResponseEntity.badRequest().build();
//        }
//        return ResponseEntity.ok(reportService.getMostPopularProperties(amount));
//    }
//
//    @GetMapping("/dashboard")
//    public ResponseEntity<Report> getDashboard() {
//        return ResponseEntity.ok(reportService.getDashboard());
//    }

}
