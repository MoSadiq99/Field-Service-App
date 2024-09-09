package com.fieldServiceV2.assignment.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "technician")
public class Technician {

    @Id
    @GeneratedValue
    @Column(name = "technician_id", updatable = false, nullable = false)
    private UUID technicianId;

    @Column(name = "name", nullable = false)
    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Column(name = "email", unique = true, nullable = false)
    @NotBlank(message = "email cannot be blank")
    @Email(message = "email must be valid")
    private String email;

    @Column(name = "phone_number", unique = true, nullable = false)
    @NotBlank(message = "Phone number cannot be blank")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 and 15 digits")
    private String phoneNumber;

    @PrePersist
    public void onCreate() {
        if (this.technicianId == null) {
            this.technicianId = UUID.randomUUID();
        }
    }
}
