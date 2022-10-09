package com.echanneling.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchDTO {
    private int doctorId;
    private String specialization;
    private int centerId;
}
