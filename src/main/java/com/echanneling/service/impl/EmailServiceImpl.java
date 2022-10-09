package com.echanneling.service.impl;

import com.echanneling.dto.EmailRequest;
import com.echanneling.service.EmailService;
import com.echanneling.util.CommonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailServiceImpl implements EmailService {

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public CommonResponse sendEmailWithoutAttachment(EmailRequest emailRequest) {

        CommonResponse commonResponse = new CommonResponse();
        try {
            emailSender.send(new MimeMessagePreparator() {

                public void prepare(MimeMessage mimeMessage) throws MessagingException {

                    MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");

                    String[] emailArray = new String[emailRequest.getEmailList().size()];
                    emailArray = emailRequest.getEmailList().toArray(emailArray);

                    message.setSubject(emailRequest.getEmailSubject());
                    message.setText(emailRequest.getEmailBody(), true);
                    message.setTo(emailArray);
                    message.setFrom(from);
                    //message.setBcc(bccArray);
                    //message.setCc(ccArray);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
           // throw new Res(ex.getMessage());
        }
        return commonResponse;
    }

}
