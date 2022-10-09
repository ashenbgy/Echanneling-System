package com.echanneling.dto;

import com.echanneling.entity.Stakeholder;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class DoctorDTO extends Stakeholder {
    private int doctorId;
    private String specialization;
    private int status;
    public int approve;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        DoctorDTO doctorDTO = (DoctorDTO) o;
        return doctorId == doctorDTO.doctorId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), doctorId);
    }
}
