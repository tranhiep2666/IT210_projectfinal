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
    @PositiveOrZero(message = "Quantity must be greater than 0")
    private Integer stockQuantity;
    @PositiveOrZero(message = "Price must be greater than or equal to 0")
    private BigDecimal price;
}
