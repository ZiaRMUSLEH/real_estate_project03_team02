package com.project.real_estate_project03_team02.controller.business;

import com.project.real_estate_project03_team02.payload.response.business.ReportResponse;
import com.project.real_estate_project03_team02.payload.response.message.ResponseMessage;
import com.project.real_estate_project03_team02.repository.business.ReportRepository;
import com.project.real_estate_project03_team02.service.business.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class ReportController {

    //@Autowired
    //private  final ReportService reportService;


   // public ResponseEntity<ReportResponse>



//
//    @Autowired
//    private ReportService reportService;
//
//    @GetMapping
//    public ResponseEntity<Report> getStatistics() {
//        return ResponseEntity.ok(reportService.getReportStatistics());
//    }
//
//    @GetMapping("/adverts")
//    public ResponseEntity<List<Report>> getAdverts(@RequestParam String query) {
//        return ResponseEntity.ok(reportService.getAdverts(query));
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
