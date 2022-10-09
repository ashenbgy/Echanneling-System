package com.echanneling.service;

import com.echanneling.dto.PatientDTO;
import com.echanneling.dto.SearchDTO;
import com.echanneling.util.CommonResponse;

public interface PatientService {
	
	CommonResponse createPatient(PatientDTO patientDTO);
	
	CommonResponse updatePatient(PatientDTO patientDTO);

    CommonResponse findPatientById(int patientId);

    CommonResponse deletePatient(int patientId);

    CommonResponse getAllPatient();

    CommonResponse findPatientByNic(String nic);

    CommonResponse searchDetails(SearchDTO searchDTO);
}
