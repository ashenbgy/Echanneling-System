package com.echanneling.controller.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echanneling.dto.FeedbackDTO;
import com.echanneling.dto.PatientDTO;
import com.echanneling.service.FeedbackService;
import com.echanneling.util.CommonResponse;

@RestController
@RequestMapping(value = "feedback")
public class FeedbackController {

	@Autowired
	FeedbackService feedbackService;
	
	@PostMapping("/createFeedback")
	public CommonResponse createFeedback(@Valid @RequestBody FeedbackDTO feedbackDTO) { 
		return feedbackService.createFeedback(feedbackDTO); 
	}
	
	@GetMapping("/getAll")
	  public CommonResponse getAllFeedback() {
	      return feedbackService.getAllFeedback();
	  }
	
	@GetMapping("/{doctorId}")
	  public CommonResponse findFeedbackByDoctorId(@PathVariable("doctorId") int doctorId ) {
	      return feedbackService.findFeedbackByDoctorId(doctorId);
	  }
}
