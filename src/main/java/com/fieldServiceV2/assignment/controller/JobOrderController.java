package com.fieldServiceV2.assignment.controller;

import com.fieldServiceV2.assignment.model.JobOrder;
import com.fieldServiceV2.assignment.service.JobOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/jobs")
@RequiredArgsConstructor
public class JobOrderController {

    private final JobOrderService jobOrderService;

    @PostMapping
    public ResponseEntity<JobOrder> addJobOrder(@RequestBody JobOrder jobOrder) {
        return ResponseEntity.ok(jobOrderService.addJobOrder(jobOrder));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobOrder> getJobOrderById(@PathVariable UUID id) {
        return ResponseEntity.ok(jobOrderService.getJobOrderById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<JobOrder> updateJobOrder(@PathVariable UUID id, @RequestBody JobOrder updateJobOrder) {
        return ResponseEntity.ok(jobOrderService.updateJobOrder(id, updateJobOrder));
    }

    @GetMapping
    public List<JobOrder> getAllJobOrders() {
        return jobOrderService.getAllJobOrders();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<JobOrder> updateJobOrderStatus(@PathVariable UUID id, @RequestParam String status) {
        return ResponseEntity.ok(jobOrderService.updateJobOrderStatus(id, status));
    }
}
