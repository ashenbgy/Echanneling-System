package com.echanneling.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.echanneling.dto.FeedbackDTO;
import com.echanneling.entity.Feedback;

@Component
public class FeedbackConverter {
	
	@Autowired
    ModelMapper modelMapper;

    public Feedback dtoTOEntity(FeedbackDTO feedbackDTO){
        return  modelMapper.map(feedbackDTO, Feedback.class);
    }

}
