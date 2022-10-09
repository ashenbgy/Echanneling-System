package com.echanneling.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.*;

@Getter
@Setter
@EqualsAndHashCode(callSuper = false)

@MappedSuperclass
public class Stakeholder {

	@NotNull
    @Size(min = 2, message = "Name should have atleast 2 characters")
	@Column(name="first_name")
	private String firstName;

	@NotNull
	@Size(min = 2, message = "Name should have atleast 2 characters")
	@Column(name="last_name")
	private String lastName;
	
	@NotNull
    @Size(min = 10, message = "NIC should have atleast 10 characters")
	@Column(name="nic")
	private String nic;
	
	@NotEmpty(message = "Phone number is required")
	@Pattern(regexp="^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{4})$",
	             message="Mobile number is invalid")
	@Column(name="contactno")
	private String contactNo;
	
	@NotNull
    @Size(min = 4, message = "Address should have atleast 4 characters")
	@Column(name="address")
	private String address;
	
	@NotEmpty(message = "Email is required")
	@Email
	private String email;

	@ManyToOne
	@JoinColumn(name ="user_id")
	private User user;

}
