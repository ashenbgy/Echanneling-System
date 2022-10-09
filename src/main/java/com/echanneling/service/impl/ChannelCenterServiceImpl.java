package com.echanneling.service.impl;

import java.util.*;

import javax.transaction.Transactional;

import com.echanneling.dto.EmailRequest;
import com.echanneling.entity.*;
import com.echanneling.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echanneling.converter.ChannelCenterConverter;
import com.echanneling.converter.PatientConverter;
import com.echanneling.dto.ChannelCenterDTO;
import com.echanneling.exception.ResourceFoundException;
import com.echanneling.repository.ChannelCenterRepository;
import com.echanneling.repository.UserRepository;
import com.echanneling.repository.UserRoleRepository;
import com.echanneling.service.ChannelCenterService;
import com.echanneling.util.CommonResponse;
import com.echanneling.util.MassageUtils;

@Service
@Transactional
public class ChannelCenterServiceImpl implements ChannelCenterService {
	
	@Autowired
	ChannelCenterRepository centerRepository;
	
	@Autowired
	ChannelCenterConverter centerConverter;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;
	
	@Override
	public CommonResponse getAllCenters() {
		List<ChannelCenter> centerList = centerRepository.findAll();

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(centerList);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.REQUEST_SUCCESSFUL.massage()));

        return commonResponse;
	}
	
	@Override
	public CommonResponse createChannelCenter(ChannelCenterDTO centerDTO) {
		Optional<ChannelCenter> centerOptional = centerRepository.findById(centerDTO.getCenterId());
        if(centerOptional.isPresent())
            throw new ResourceFoundException("");
        User user = modelMapper.map(centerDTO.getUser(),User.class);

        Optional<UserRole> userRole = userRoleRepository.findById(4);

        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole.get());

        user.setUserRole(userRoleSet);

        User exUser =  userRepository.findByUsername(user.getUsername());
        if(exUser!=null)
            throw new ResourceFoundException("User Name Already Exist");

        User newUser = userRepository.save(user);

        ChannelCenter center = centerConverter.dtoTOEntity(centerDTO);
        center.setUser(newUser);
        center =centerRepository.save(center);

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setEmailBody("<html>" +
                "<p>Hi "+center.getFirstName()+",</p>\n" +
                "<p>Welcome to E Channeling.</p>\n" +
                "<p>Use below Credentials as your E Channeling Login Details.</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p><strong>User Name:<span style=\"color: #ff0000;\">"+user.getUsername()+"</span></strong></p>\n" +
                "<p><strong>Password:<span style=\"color: #ff0000;\">"+user.getPassword()+"</span></strong></p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p><a href=\"http://localhost:8080/echanneling/\">http://localhost:8080/echanneling/</a></p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>Thank you.</p>"
                + "</html>");
        emailRequest.setEmailSubject("E Channeling Credentials");
        emailRequest.setEmailList(Arrays.asList(center.getEmail()));

        emailService.sendEmailWithoutAttachment(emailRequest);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(center);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_CREATED.massage()));

        return commonResponse;
	}

	@Override
	public CommonResponse updateChannelCenter(ChannelCenterDTO centerDTO) {
		Optional<ChannelCenter> centerOptional = centerRepository.findById(centerDTO.getCenterId());
        if(!centerOptional.isPresent())
            throw new ResourceFoundException("");


        ChannelCenter center = centerRepository.save(centerConverter.dtoTOEntity(centerDTO));

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(center);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_UPDATED.massage()));

        return commonResponse;
	}

	@Override
	public CommonResponse findCenterById(int centerId) {
		Optional<ChannelCenter> centerOptional = centerRepository.findById(centerId);
        if(!centerOptional.isPresent())
            throw new ResourceFoundException("");

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(centerOptional.get());
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_UPDATED.massage()));

        return commonResponse;
	}

	@Override
	public CommonResponse deleteChannelCenter(int centerId) {
		Optional<ChannelCenter> centerOptional = centerRepository.findById(centerId);
        if(!centerOptional.isPresent())
            throw new ResourceFoundException("");

        centerRepository.delete(centerOptional.get());
        return new CommonResponse();
	}

    @Override
    public CommonResponse approveCenter(int centerId) {
        Optional<ChannelCenter> centerOptional = centerRepository.findById(centerId);
        if(!centerOptional.isPresent())
            throw new ResourceFoundException("");

        ChannelCenter center = centerOptional.get();
        center.setApprove(1);
        centerRepository.save(center);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(center);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList("Successfully Approved!"));

        return commonResponse;
    }

    @Override
    public CommonResponse rejectCenter(int centerId) {
        Optional<ChannelCenter> centerOptional = centerRepository.findById(centerId);
        if(!centerOptional.isPresent())
            throw new ResourceFoundException("");

        ChannelCenter center = centerOptional.get();
        center.setApprove(2);
        centerRepository.save(center);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(center);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList("Successfully Rejected!"));

        return commonResponse;
    }

    @Override
    public CommonResponse getAllActiveCenters() {
        List<ChannelCenter> centerList = centerRepository.getAllActiveCenters();

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(centerList);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.REQUEST_SUCCESSFUL.massage()));

        return commonResponse;
    }

}
