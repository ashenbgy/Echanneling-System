package com.echanneling.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name="appointment")
public class Appointment {
	@Id
	@Column(name="appointment_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int appId;
	
	@Column(name="appointment_date")
	private Date appDate;
	
	@Column(name="start_time")
	private String startTime;
	
	@Column(name="end_time")
	private String endTime;

	@ManyToOne
	@JoinColumn(name ="doc_map_id")
	private DoctorMappingCenter doctorMappingCenter;

	@ManyToOne
	private Patient patient;

	@Column(name="comment")
	private String comment;
}
