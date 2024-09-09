package com.fieldServiceV2.assignment.service;

import com.fieldServiceV2.assignment.exception.CustomException;
import com.fieldServiceV2.assignment.model.Technician;
import com.fieldServiceV2.assignment.repo.TechnicianRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class TechnicianService {

    private final TechnicianRepo technicianRepo;

    private static final Logger logger = LoggerFactory.getLogger(TechnicianService.class);

    public Technician addTechnician(Technician newTechnician) {
        String name = newTechnician.getName();
        String email = newTechnician.getEmail();
        String phoneNumber = newTechnician.getPhoneNumber();
        if(name == null || name.isEmpty()) {
            throw new CustomException("Name cannot be blank");
        }
        if(email == null || email.isEmpty()) {
            throw new CustomException("Email cannot be blank");
        }
        if(phoneNumber == null || phoneNumber.isEmpty()) {
            throw new CustomException("Phone number cannot be blank");
        }

        if(technicianRepo.existsByPhoneNumber(phoneNumber)) {
            throw new CustomException("Phone number already exists. Please use a different phone number");
        }
        if(technicianRepo.existsByEmail(email)) {
            throw new CustomException("Email already exists. Please use a different email");
        }
//        logger.debug("Technician before save: {}", newTechnician);
        return technicianRepo.save(newTechnician);
    }

    public List<Technician> getAllTechnicians() {
        return technicianRepo.findAll();
    }

//    @Transactional(readOnly = true)
    public Technician getTechnicianById(UUID id) {
        return technicianRepo.findById(id).orElseThrow(()-> new RuntimeException("Technician not found with id: " + id));
    }

    public Technician updateTechnician(UUID id, Technician updateTechnician) {
        Technician existingTechnician = getTechnicianById(id);
        updateTechnicianFields(existingTechnician, updateTechnician);
        return addTechnician(existingTechnician);
    }

    private void updateTechnicianFields(Technician existingTechnician, Technician updateTechnician) {
        if(updateTechnician.getName() != null) {
            existingTechnician.setName(updateTechnician.getName());
        }
        if(updateTechnician.getEmail() != null) {
            existingTechnician.setEmail(updateTechnician.getEmail());
        }
        if(updateTechnician.getPhoneNumber() != null) {
            existingTechnician.setPhoneNumber(updateTechnician.getPhoneNumber());
        }
    }

    public void deleteTechnician(UUID id) {
        if(!technicianRepo.existsById(id)) {
            throw new CustomException("Technician not found with id: " + id);
        }
        technicianRepo.deleteById(id);
    }
}
