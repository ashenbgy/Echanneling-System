package com.echanneling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.echanneling.entity.Doctor;
import com.echanneling.entity.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,Integer> {
	
	Optional<Patient> findByNic(String nic);
	
}
