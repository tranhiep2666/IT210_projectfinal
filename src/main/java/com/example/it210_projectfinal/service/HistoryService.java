package com.example.it210_projectfinal.service;

import com.example.it210_projectfinal.entity.Appointment;

import java.util.List;

public interface HistoryService {
    List<Appointment> getHistory(String username);
}