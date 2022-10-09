package com.echanneling.dto;

import java.util.Objects;

import javax.persistence.Column;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FeedbackDTO {

	private int feedbackId;
	private int rate;
	private String description;
	private int isPositive;
	private int patientId;
	private int doctorId;
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FeedbackDTO feedbackDTO = (FeedbackDTO) o;
        return feedbackId == feedbackDTO.feedbackId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), feedbackId);
    }
}
