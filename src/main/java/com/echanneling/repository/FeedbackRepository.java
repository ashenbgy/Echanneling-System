package com.echanneling.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.echanneling.entity.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback,Integer>  {

	@Query("SELECT bc FROM Feedback bc WHERE bc.doctor.doctorId = ?1")
	Optional<Feedback> findByDoctorId(int doctorId);

}
