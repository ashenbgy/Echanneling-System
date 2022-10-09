package com.echanneling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name="patient")
public class Patient extends Stakeholder {
	
	@Id
	@Column(name="patient_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int patientId;

}
