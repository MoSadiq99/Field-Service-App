package com.fieldServiceV2.assignment.model;

import com.fieldServiceV2.assignment.model.Enum.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "job_orders")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JobOrder {

    @Id
    @Column(name = "job_order_id",  updatable = false, nullable = false)
    private UUID jobOrderId;

    @Column(name = "title" , nullable = false)
    @NotBlank(message = "title cannot be blank")
    private String title;

    @Column(name = "description")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "scheduled_date")
    private LocalDateTime scheduledDate;

//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name = "technician_id", referencedColumnName = "technician_id", nullable = false)
//    private Technician technician;

    @Column(name = "technician_id", nullable = false)
    private UUID technicianId;

    @PrePersist
    protected void onCreate() {
        if (this.jobOrderId == null) {
            this.jobOrderId = UUID.randomUUID();
        }
    }

    // ? AI Suggestions
    @PreUpdate
    private void onUpdate() {
        if (this.status == Status.COMPLETED) {
            this.scheduledDate = null;
        }
    }
}
