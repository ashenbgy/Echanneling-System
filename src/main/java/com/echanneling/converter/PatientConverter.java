package com.echanneling.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.echanneling.dto.PatientDTO;
import com.echanneling.entity.Patient;

@Component
public class PatientConverter {

	@Autowired
    ModelMapper modelMapper;

    public Patient dtoTOEntity(PatientDTO patientDTO){
        return  modelMapper.map(patientDTO, Patient.class);
    }
    
}
