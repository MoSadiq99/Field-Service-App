package com.fieldServiceV2.assignment.service;

import com.fieldServiceV2.assignment.model.JobOrder;
import com.fieldServiceV2.assignment.model.Location;
import com.fieldServiceV2.assignment.repo.JobOrderRepo;
import com.fieldServiceV2.assignment.repo.LocationRepo;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class LocationService {

    private final LocationRepo locationRepo;
    private final JobOrderRepo jobOrderRepo;
    public Optional<Location> getLocationByJobOrderId(UUID jobOrderId) {
        return locationRepo.findByJobOrderId(jobOrderId);
    }

    @Transactional
    public Location addLocation(Location location) {
        // Fetch the existing JobOrder
        JobOrder existingJobOrder = jobOrderRepo.findById(location.getJobOrder().getJobOrderId())
                .orElseThrow(() -> new EntityNotFoundException("JobOrder not found"));

        // Create a new Location instance
        Location newLocation = new Location();
        newLocation.setLatitude(location.getLatitude());
        newLocation.setLongitude(location.getLongitude());
        newLocation.setJobOrder(existingJobOrder);

        // Save and return the new Location
        return locationRepo.save(newLocation);
    }
}
