package com.echanneling.repository;

import com.echanneling.entity.Doctor;
import com.echanneling.entity.DoctorMappingCenter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor,Integer> {

    Optional<Doctor> findByNic(String nic);

    @Query("From Doctor D WHERE D.approve=1")
    List<Doctor> getAllActiveDoctors();

    @Query("From Doctor D WHERE D.user.userId=?1")
    Doctor findByUserId(int userId);
}
