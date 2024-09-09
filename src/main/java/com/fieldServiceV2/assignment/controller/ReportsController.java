package com.fieldServiceV2.assignment.controller;

import com.fieldServiceV2.assignment.model.JobOrder;
import com.fieldServiceV2.assignment.service.JobOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/reports")
@RequiredArgsConstructor
public class ReportsController {

    private final JobOrderService jobOrderService;

//    @GetMapping("/completed-jobs")
//    public List<JobOrder> getAllCompletedJobs() {
//        return jobOrderService.getCompletedJobs();
//    }

    @GetMapping("/completed-jobs")
    public List<JobOrder> getCompletedJobsWithinTimeframe(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return jobOrderService.getCompletedJobsWithinTimeframe(startDate, endDate);
    }

    @GetMapping("/active-jobs")
    public List<JobOrder> getActiveJobsWithinTimeframe(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        return jobOrderService.getActiveJobsWithinTimeframe(startDate, endDate);
    }
}
