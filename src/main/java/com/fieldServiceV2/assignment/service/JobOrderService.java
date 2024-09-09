package com.fieldServiceV2.assignment.service;

import com.fieldServiceV2.assignment.exception.CustomException;
import com.fieldServiceV2.assignment.model.Enum.Status;
import com.fieldServiceV2.assignment.model.JobOrder;
import com.fieldServiceV2.assignment.model.Technician;
import com.fieldServiceV2.assignment.repo.JobOrderRepo;
import com.fieldServiceV2.assignment.repo.TechnicianRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class JobOrderService {

    private final JobOrderRepo jobOrderRepo;
    private final TechnicianRepo technicianRepo;
    public JobOrder addJobOrder(JobOrder jobOrderRequest) {
        UUID technicianId = jobOrderRequest.getTechnicianId();
        Technician technician = technicianRepo.findById(technicianId)
                .orElseThrow(() -> new CustomException(STR."Technician not found with id: \{technicianId}"));
        return jobOrderRepo.save(jobOrderRequest);
    }

    public JobOrder getJobOrderById(UUID id) {
        return jobOrderRepo.findById(id).orElseThrow(() -> new CustomException(STR."Job order not found with id: \{id}"));
    }

    public JobOrder updateJobOrder(UUID id, JobOrder updateJobOrder) {
        JobOrder existingJobOrder = jobOrderRepo.findById(id).orElseThrow(() -> new CustomException(STR."Job order not found with id: \{id}"));
        updateJobOrderFields(existingJobOrder, updateJobOrder);
        return addJobOrder(existingJobOrder);
    }

    private void updateJobOrderFields(JobOrder existingJobOrder, JobOrder updateJobOrder) {
        if(updateJobOrder.getTitle() != null) {
            existingJobOrder.setTitle(updateJobOrder.getTitle());
        }
        if (updateJobOrder.getDescription() != null) {
            existingJobOrder.setDescription(updateJobOrder.getDescription());
        }
        if (updateJobOrder.getStatus() != null) {
            existingJobOrder.setStatus(updateJobOrder.getStatus());
        }
        if (updateJobOrder.getScheduledDate() != null) {
            existingJobOrder.setScheduledDate(updateJobOrder.getScheduledDate());
        }
        if (updateJobOrder.getTechnicianId() != null) {
            existingJobOrder.setTechnicianId(updateJobOrder.getTechnicianId());
        }
    }

    public List<JobOrder> getAllJobOrders() {
        return jobOrderRepo.findAll();
    }

    public JobOrder updateJobOrderStatus(UUID id, String status) {
        JobOrder existingJobOrder = jobOrderRepo.findById(id).orElseThrow(() -> new CustomException("Job order not found"));
        existingJobOrder.setStatus(Status.valueOf(status));
        return addJobOrder(existingJobOrder);
    }

    public List<JobOrder> getCompletedJobs() {
        return jobOrderRepo.findByStatus(Status.COMPLETED);
    }
    public List<JobOrder> getCompletedJobsWithinTimeframe(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate == null || endDate == null) {
            throw new RuntimeException("Start date and end date cannot be null");
        }
        if (startDate.isAfter(endDate)) {
            throw new RuntimeException("Start date cannot be after end date");
        }
        return jobOrderRepo.findByStatusAndScheduledDateBetween(Status.COMPLETED, startDate, endDate);
    }

    public List<JobOrder> getActiveJobsWithinTimeframe(LocalDateTime startDate, LocalDateTime endDate) {
        return jobOrderRepo.findByScheduledDateBetween(startDate, endDate);
    }
}
