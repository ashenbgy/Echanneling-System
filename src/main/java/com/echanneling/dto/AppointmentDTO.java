package com.echanneling.dto;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppointmentDTO {
	
	private int appId;
	private Date appDate;
	private String startTime;
	private String endTime;
	private int docMapId;
	private int patientId;
    private int userId;
    private String comment;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AppointmentDTO appointmentDTO = (AppointmentDTO) o;
        return appId == appointmentDTO.appId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), appId);
    }

}
