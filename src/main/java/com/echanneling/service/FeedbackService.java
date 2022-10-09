package com.echanneling.service;

import com.echanneling.dto.FeedbackDTO;
import com.echanneling.util.CommonResponse;

public interface FeedbackService {

	CommonResponse createFeedback(FeedbackDTO feedbackDTO);

	CommonResponse getAllFeedback();

	CommonResponse findFeedbackByDoctorId(int doctorId);

}
