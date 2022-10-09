package com.echanneling.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.echanneling.dto.AppointmentDTO;
import com.echanneling.entity.Appointment;
import com.echanneling.entity.Feedback;

@Component
public class AppointmentConverter {
	
	@Autowired
    ModelMapper modelMapper;

    public Appointment dtoTOEntity(AppointmentDTO appointmentDTO){
        return  modelMapper.map(appointmentDTO, Appointment.class);
    }

}
