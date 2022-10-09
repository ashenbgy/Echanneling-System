package com.echanneling.service;


import com.echanneling.dto.DoctorDTO;
import com.echanneling.dto.DoctorMappingDTO;
import com.echanneling.util.CommonResponse;

public interface DoctorService {
    
    CommonResponse createDoctor(DoctorDTO doctorDTO);

    CommonResponse updateDoctor(DoctorDTO doctorDTO);

    CommonResponse findDoctorById(int doctorId);

    CommonResponse deleteDoctor(int doctorId);

    CommonResponse getAllDoctors();

    CommonResponse findDoctorByNic(String nic);

    CommonResponse approveDoctor(int doctorId);

    CommonResponse rejectDoctor(int doctorId);

    CommonResponse registerForCenter(DoctorMappingDTO doctorMappingDTO);

    CommonResponse getAllApprovedDoctors();
}
