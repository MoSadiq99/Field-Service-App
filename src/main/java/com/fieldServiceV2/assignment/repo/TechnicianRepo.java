package com.fieldServiceV2.assignment.repo;

import com.fieldServiceV2.assignment.model.Technician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

public interface TechnicianRepo extends JpaRepository<Technician, UUID> {

    boolean existsByEmail(String email);

    boolean existsByPhoneNumber(String phoneNumber);
}
