package com.example.it210_projectfinal.service.impl;

import com.example.it210_projectfinal.entity.Appointment;
import com.example.it210_projectfinal.repository.AppointmentRepository;
import com.example.it210_projectfinal.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {
    private final AppointmentRepository appointmentRepository;
    @Override
    public List<Appointment> getHistory(String username) {
        return appointmentRepository.findHistoryByUsername(username);
    }
}