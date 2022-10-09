package com.echanneling.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echanneling.converter.FeedbackConverter;
import com.echanneling.dto.FeedbackDTO;
import com.echanneling.entity.Feedback;
import com.echanneling.exception.ResourceFoundException;
import com.echanneling.repository.FeedbackRepository;
import com.echanneling.service.FeedbackService;
import com.echanneling.util.CommonResponse;
import com.echanneling.util.MassageUtils;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	FeedbackRepository feedbackRepository;
	
	@Autowired
	FeedbackConverter feedbackConverter;

	@Override
	public CommonResponse createFeedback(FeedbackDTO feedbackDTO) {
		Optional<Feedback> feedbackOptional = feedbackRepository.findById(feedbackDTO.getFeedbackId());
        if(feedbackOptional.isPresent())
            throw new ResourceFoundException("");

        Feedback feedback = feedbackConverter.dtoTOEntity(feedbackDTO);
        feedback = feedbackRepository.save(feedback);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(feedback);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_CREATED.massage()));

        return commonResponse;
		
	}

	@Override
	public CommonResponse getAllFeedback() {
		List<Feedback> feedbackList = feedbackRepository.findAll();

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(feedbackList);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.REQUEST_SUCCESSFUL.massage()));

        return commonResponse;
	}

	@Override
	public CommonResponse findFeedbackByDoctorId(int doctorId) {
		
		Optional<Feedback> feedbackOptional = feedbackRepository.findByDoctorId(doctorId);
        if(!feedbackOptional.isPresent())
            throw new ResourceFoundException("");

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(feedbackOptional.get());
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_UPDATED.massage()));

        return commonResponse;
	}

}
