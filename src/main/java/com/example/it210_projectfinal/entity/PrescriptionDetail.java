package com.example.it210_projectfinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "prescription_details")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PrescriptionDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "prescription_id")
    private Prescription prescription;
    @ManyToOne
    @JoinColumn(name = "medicine_id")
    private Medicine medicine;
    private Integer quantity;
    private String dosage;
    private String instructions;
    private BigDecimal unitPrice;
    private BigDecimal subtotal;
}