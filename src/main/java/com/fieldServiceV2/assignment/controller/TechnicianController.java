package com.fieldServiceV2.assignment.controller;

import com.fieldServiceV2.assignment.model.Technician;
import com.fieldServiceV2.assignment.service.TechnicianService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/technicians")
@RequiredArgsConstructor
@CrossOrigin
@Tag(name = "Technician", description = "Technician API")
public class TechnicianController {

    private final TechnicianService service;

    @PostMapping
    public ResponseEntity<Technician> addTechnician(@RequestBody Technician newTechnician) {
        return ResponseEntity.ok(service.addTechnician(newTechnician));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a technician by ID")
    public ResponseEntity<Technician> getTechnicianById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getTechnicianById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Technician> updateTechnician(@PathVariable UUID id, @RequestBody Technician updateTechnician) {
        return ResponseEntity.ok(service.updateTechnician(id, updateTechnician));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTechnician(@PathVariable UUID id) {
        service.deleteTechnician(id);
        return ResponseEntity.ok("Technician deleted successfully");
    }

    @GetMapping
    public ResponseEntity<List<Technician>> getAllTechnicians() {
        return ResponseEntity.ok(service.getAllTechnicians());
    }
}
