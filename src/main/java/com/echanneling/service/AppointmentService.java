package com.echanneling.service;

import com.echanneling.dto.AppointmentDTO;
import com.echanneling.util.CommonResponse;

public interface AppointmentService {

	CommonResponse createAppointment(AppointmentDTO appointmentDTO);

    CommonResponse processAppointments();

    CommonResponse getAllAppointment();

    CommonResponse findAppointmentsByDoctorId(int doctorId);

    CommonResponse findAppointmentsByCenterId(int centerId);

    CommonResponse findAppointmentsByDoctorMappingCenterId(int docMapId);
}
