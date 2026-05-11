package com.example.it210_projectfinal.service;

import com.example.it210_projectfinal.dto.AppointmentRequest;

public interface AppointmentService {
    void book(AppointmentRequest request, String username);
}