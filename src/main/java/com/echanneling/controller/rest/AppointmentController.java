package com.echanneling.controller.rest;

import com.echanneling.service.AppointmentService;
import com.echanneling.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "appointment")
public class AppointmentController {

    @Autowired
    AppointmentService appointmentService;

    @GetMapping ("/process")
    public CommonResponse processAppointments() {
        return appointmentService.processAppointments();
    }

    @GetMapping("/getAll")
    public CommonResponse getAllAppointment() {
        return appointmentService.getAllAppointment();
    }

    @GetMapping("doctor/{doctorId}")
    public CommonResponse findAppointmentsByDoctorId(@PathVariable("doctorId") int doctorId ) {
        return appointmentService.findAppointmentsByDoctorId(doctorId);
    }

    @GetMapping("center/{doctorId}")
    public CommonResponse findAppointmentsByCenterId(@PathVariable("centerId") int centerId ) {
        return appointmentService.findAppointmentsByCenterId(centerId);
    }

    @GetMapping("viewMapping/{docMapId}")
    public CommonResponse findAppointmentsByDoctorMappingCenterId(@PathVariable("docMapId") int docMapId ) {
        return appointmentService.findAppointmentsByDoctorMappingCenterId(docMapId);
    }
}
