package com.echanneling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.echanneling.entity.Appointment;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    @Query("FROM Appointment ap WHERE ap.doctorMappingCenter.doctor.doctorId=?1")
    List<Appointment> findAppointmentsByDoctorId(int doctorId);

    @Query("FROM Appointment ap WHERE ap.doctorMappingCenter.center.centerId=?1")
    List<Appointment> findAppointmentsByCenterId(int centerId);

    @Query("FROM Appointment ap WHERE ap.doctorMappingCenter.docMapId=?1")
    List<Appointment> findAppointmentsByDoctorMappingCenterId(int docMapId);
}
