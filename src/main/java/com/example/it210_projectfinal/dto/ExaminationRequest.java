package com.example.it210_projectfinal.dto;

import lombok.Data;

import java.util.List;

@Data
public class ExaminationRequest {

    private Long appointmentId;

    private String symptoms;

    private String diagnosis;

    private String notes;

    private List<PrescriptionItemRequest> medicines;
}