package com.echanneling.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorMappingDTO {

    public int 	docMapId;

    private int doctorId;

    private int centerId;

    public String startTime;

    public String endTime;

    public String chanelDay;

    private int userId;
}
