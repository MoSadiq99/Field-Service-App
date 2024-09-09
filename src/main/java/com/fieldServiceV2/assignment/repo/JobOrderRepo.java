package com.fieldServiceV2.assignment.repo;

import com.fieldServiceV2.assignment.model.Enum.Status;
import com.fieldServiceV2.assignment.model.JobOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface JobOrderRepo extends JpaRepository<JobOrder, UUID> {
    List<JobOrder> findByStatus(Status status);
    List<JobOrder> findByScheduledDateBetween(LocalDateTime startDate, LocalDateTime endDate);
    List<JobOrder> findByStatusAndScheduledDateBetween(Status status, LocalDateTime startDate, LocalDateTime endDate);
}
