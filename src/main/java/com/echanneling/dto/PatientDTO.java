package com.echanneling.dto;

import java.util.Objects;

import com.echanneling.entity.Stakeholder;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO extends Stakeholder{
	
	private int patientId;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PatientDTO patientDTO = (PatientDTO) o;
        return patientId == patientDTO.patientId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), patientId);
    }

}
