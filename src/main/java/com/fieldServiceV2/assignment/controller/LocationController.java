package com.fieldServiceV2.assignment.controller;

import com.fieldServiceV2.assignment.model.Location;
import com.fieldServiceV2.assignment.service.GoogleMapsService;
import com.fieldServiceV2.assignment.service.LocationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("api/jobs")
@RequiredArgsConstructor
@Slf4j
public class LocationController {

    private final LocationService locationService;
    private final GoogleMapsService googleMapsService;
//    private final Location location;

    @PostMapping("/location")
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {
        return ResponseEntity.ok(locationService.addLocation(location));
    }

//    @GetMapping("/{jobOrderId}/location")
//    public ResponseEntity<String> getJobLocation(@PathVariable UUID jobOrderId) {
//        return locationService.getLocationByJobOrderId(jobOrderId)
//                .map(location -> {
//                    String address = googleMapsService.getAddressFromCoordinates(location.getLatitude(), location.getLongitude());
//                    return ResponseEntity.ok(address);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

    @GetMapping("/{jobOrderId}/location")
    public ResponseEntity<Map<String, Object>> getJobLocation(@PathVariable UUID jobOrderId) {
        log.info("Fetching location for jobOrderId: {}", jobOrderId);
        return locationService.getLocationByJobOrderId(jobOrderId)
                .map(location -> {
                    log.info("Location found: {}, {}", location.getLatitude(), location.getLongitude());
                    String address = googleMapsService.getAddressFromCoordinates(location.getLatitude(), location.getLongitude());
                    Map<String, Object> response = new HashMap<>();
                    response.put("latitude", location.getLatitude());
                    response.put("longitude", location.getLongitude());
                    response.put("address", address);
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

//    @GetMapping("/{jobOrderId}/location")
//    public ResponseEntity<String> getJobLocation(@PathVariable UUID jobOrderId) {
//        log.info("Fetching location for jobOrderId: {}", jobOrderId);
//        return locationService.getLocationByJobOrderId(jobOrderId)
//                .map(location -> {
//                    log.info("Location found: {}, {}", location.getLatitude(), location.getLongitude());
//                    String address = googleMapsService.getAddressFromCoordinates(location.getLatitude(), location.getLongitude());
//                    return ResponseEntity.ok(address);
//                })
//                .orElse(ResponseEntity.notFound().build());
//    }

//    @GetMapping("/{jobOrderId}/location")
//    public ResponseEntity<String> getJobLocation(@PathVariable UUID jobOrderId) {
////        var location = locationService.getLocationByJobOrderId(jobOrderId);
//        var location = (Location) locationService.getLocationByJobOrderId(jobOrderId);
//        var address = googleMapsService.getAddressFromCoordinates(location.getLatitude(), location.getLongitude());
//        return ResponseEntity.ok(address);
//    }
//

}