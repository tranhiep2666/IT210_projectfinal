package com.example.it210_projectfinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "prescriptions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Prescription {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    @Enumerated(EnumType.STRING)
    private PrescriptionStatus status;

    private BigDecimal totalAmount;

    private LocalDateTime issuedAt;

    @OneToMany(mappedBy = "prescription",
            cascade = CascadeType.ALL)
    private Set<PrescriptionDetail> details = new HashSet<>();


}