package com.echanneling.converter;

import com.echanneling.dto.DoctorDTO;
import com.echanneling.entity.Doctor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DoctorConverter {

    @Autowired
    ModelMapper modelMapper;

    public Doctor dtoTOEntity(DoctorDTO doctorDTO){
        return  modelMapper.map(doctorDTO, Doctor.class);
    }
}
