package com.echanneling.controller.rest;

import com.echanneling.dto.DoctorDTO;
import com.echanneling.dto.DoctorMappingDTO;
import com.echanneling.service.DoctorService;
import com.echanneling.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "doctor")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @PostMapping("/")
    public CommonResponse createDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        return doctorService.createDoctor(doctorDTO);
    }

    @PutMapping("/updateDoctor")
    public CommonResponse updateDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        return doctorService.updateDoctor(doctorDTO);
    }

    @GetMapping("/{doctorId}")
    public CommonResponse findDoctorById(@PathVariable("doctorId") int doctorId ) {
        return doctorService.findDoctorById(doctorId);
    }

    @DeleteMapping("/{doctorId}")
    public CommonResponse deleteDoctor(@PathVariable("doctorId") int doctorId ) {
        return doctorService.deleteDoctor(doctorId);
    }

    @GetMapping("/getAll")
    public CommonResponse getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/nic/{nic}")
    public CommonResponse findDoctorByNic(@PathVariable("nic") String nic ) {
        return doctorService.findDoctorByNic(nic);
    }

    @PutMapping("/approve/{doctorId}")
    public CommonResponse approveDoctor(@PathVariable("doctorId") int doctorId ) {
        return doctorService.approveDoctor(doctorId);
    }

    @PutMapping("/reject/{doctorId}")
    public CommonResponse rejectDoctor(@PathVariable("doctorId") int doctorId ) {
        return doctorService.rejectDoctor(doctorId);
    }

    @PostMapping("/register")
    public CommonResponse registerForCenter(@Valid @RequestBody DoctorMappingDTO doctorMappingDTO) {
        return doctorService.registerForCenter(doctorMappingDTO);
    }

    @GetMapping("/getAllActive")
    public CommonResponse getAllApprovedDoctors() {
        return doctorService.getAllApprovedDoctors();
    }

}
