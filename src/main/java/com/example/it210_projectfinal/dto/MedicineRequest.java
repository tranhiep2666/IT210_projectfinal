package com.example.it210_projectfinal.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class MedicineRequest {
    @NotBlank
    private String name;
    private String description;
    @PositiveOrZero
    private Integer stockQuantity;
    @PositiveOrZero
    private BigDecimal price;
}
