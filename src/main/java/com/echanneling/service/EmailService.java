package com.echanneling.service;


import com.echanneling.dto.EmailRequest;
import com.echanneling.util.CommonResponse;

public interface EmailService {

    public CommonResponse sendEmailWithoutAttachment(EmailRequest emailRequest);
}
