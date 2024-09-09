package com.fieldServiceV2.assignment.repo;

import com.fieldServiceV2.assignment.model.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface LocationRepo extends JpaRepository<Location, UUID> {

//    @Query("SELECT l FROM Location l WHERE l.jobOrder.jobOrderId = ?1")
//    Object findByJobOrderId(UUID jobOrderId);

    @Query("SELECT l FROM Location l WHERE l.jobOrder.jobOrderId = :jobOrderId")
    Optional<Location> findByJobOrderId(@Param("jobOrderId") UUID jobOrderId);
}
