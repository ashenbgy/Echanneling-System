package com.echanneling.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

@Entity
@Table(name="channel_center")
public class ChannelCenter extends Stakeholder{
	@Id
	@Column(name="center_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int centerId;

	@NotNull
	@Column(name="is_approve")
	public int approve;
}
