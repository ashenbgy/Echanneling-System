package com.echanneling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name="feedback")
public class Feedback {
	
	@Id
	@Column(name="feedback_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int feedbackId;
	
	@Column(name="rate")
	private int rate;
	
	@Column(name="description")
	private String description;
	
	@Column(name="is_positive")
	private int isPositive;
	
	@ManyToOne
	@JoinColumn(name ="patient_id")
	private Patient patient;
	
	@ManyToOne
	@JoinColumn(name ="doctor_id")
	private Doctor doctor;

}
