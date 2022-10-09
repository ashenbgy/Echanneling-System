package com.echanneling.controller.rest;

import javax.validation.Valid;

import com.echanneling.dto.SearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echanneling.dto.AppointmentDTO;
import com.echanneling.dto.DoctorDTO;
import com.echanneling.dto.PatientDTO;
import com.echanneling.service.AppointmentService;
import com.echanneling.service.PatientService;
import com.echanneling.util.CommonResponse;

@RestController
@RequestMapping(value = "patient")
public class PatientController {

	  @Autowired
	  PatientService patientService;
	  
	  @Autowired
	  AppointmentService appointmentService;
	  
	  @PostMapping("/save")
	  public CommonResponse createPatient(@Valid @RequestBody PatientDTO patientDTO) { 
		  return patientService.createPatient(patientDTO); 
	  }
	  
	  @PutMapping("/updatePatient")
	   public CommonResponse updatePatient(@Valid @RequestBody PatientDTO patientDTO) {
	      return patientService.updatePatient(patientDTO);
	  }

	  @GetMapping("/{patientId}")
	  public CommonResponse findPatientById(@PathVariable("patientId") int patientId ) {
	      return patientService.findPatientById(patientId);
	  }

	  @DeleteMapping("/{patientId}")
	  public CommonResponse deletePatient(@PathVariable("doctorId") int patientId ) {
	      return patientService.deletePatient(patientId);
	  }

	  @GetMapping("/getAll")
	  public CommonResponse getAllPatient() {
	      return patientService.getAllPatient();
	  }

	  @GetMapping("/nic/{nic}")
	  public CommonResponse findPatientByNic(@PathVariable("nic") String nic ) {
	      return patientService.findPatientByNic(nic);
	  }
	    
	  @PostMapping("/createAppointment")
	  public CommonResponse createAppointment(@Valid @RequestBody AppointmentDTO appointmentDTO) { 
		  return appointmentService.createAppointment(appointmentDTO); 
	  }

	  @PostMapping("/search")
	  public CommonResponse searchDetails(@Valid @RequestBody SearchDTO searchDTO) {
		return patientService.searchDetails(searchDTO);
	  }
	 
}
