package com.echanneling.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;

@Getter
@Setter

@Entity
@Table(name="doctor")
public class Doctor extends Stakeholder{
	@Id
	@Column(name="doctor_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int doctorId;

	@NotNull
	@Column(name="specialization")
	private String specialization;

	@NotNull
	@Column(name="status")
	private int status;

	@NotNull
	@Column(name="is_approve")
	public int approve;
}
