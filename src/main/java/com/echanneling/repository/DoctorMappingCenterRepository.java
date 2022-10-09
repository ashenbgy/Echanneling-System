package com.echanneling.repository;

import com.echanneling.entity.DoctorMappingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorMappingCenterRepository extends JpaRepository<DoctorMappingCenter, Integer> {

    @Query("From DoctorMappingCenter D WHERE D.chanelDay =?1")
    List<DoctorMappingCenter> channelcentergetByDate(String chanelDay);

    @Query("From DoctorMappingCenter D WHERE D.doctor.approve=1")
    List<DoctorMappingCenter> getAllActiveDoctors();
}
