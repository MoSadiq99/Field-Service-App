package com.fieldServiceV2.assignment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "location")
public class Location {

    @Id
    @Column(name = "location_id", updatable = false, nullable = false)
    private UUID locationId;

    @Column(name = "latitude", nullable = false)
    @NotNull(message = "latitude cannot be blank")
    private Double latitude;

    @Column(name = "longitude", nullable = false)
    @NotNull(message = "longitude cannot be blank")
    private Double longitude;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "job_order_id", referencedColumnName = "job_order_id", nullable = false)
    private JobOrder jobOrder;

    @PrePersist
    private void onCreate() {
        if (this.locationId == null) {
            this.locationId = UUID.randomUUID();
        }
    }

}