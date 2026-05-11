package com.example.it210_projectfinal.service.impl;

import com.example.it210_projectfinal.dto.AppointmentRequest;
import com.example.it210_projectfinal.entity.*;
import com.example.it210_projectfinal.repository.*;
import com.example.it210_projectfinal.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    @Override
    public void book(
            AppointmentRequest request,
            String username
    ) {
        User patient = userRepository
                .findByUsername(username)
                .orElseThrow();
        Doctor doctor = doctorRepository
                .findById(request.getDoctorId())
                .orElseThrow();
        boolean existed = appointmentRepository.existsByDoctorAndAppointmentDateAndAppointmentTime(
                doctor, request.getAppointmentDate(), request.getAppointmentTime());
        if (existed) {
            throw new RuntimeException("This slot is already booked");
        }
        Appointment appointment = Appointment.builder()
                        .patient(patient)
                        .doctor(doctor)
                        .appointmentDate(request.getAppointmentDate())
                        .appointmentTime(request.getAppointmentTime())
                        .reason(request.getReason())
                        .status(AppointmentStatus.WAITING)
                        .build();
        appointmentRepository.save(appointment);
    }
}