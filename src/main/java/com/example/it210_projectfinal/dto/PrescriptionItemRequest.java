package com.example.it210_projectfinal.dto;

import lombok.Data;

@Data
public class PrescriptionItemRequest {
    private Long medicineId;
    private Integer quantity;
    private String dosage;
    private String instructions;
}