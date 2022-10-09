package com.echanneling.service.impl;

import com.echanneling.converter.DoctorConverter;
import com.echanneling.dto.DoctorDTO;
import com.echanneling.dto.DoctorMappingDTO;
import com.echanneling.dto.EmailRequest;
import com.echanneling.entity.*;
import com.echanneling.exception.ResourceFoundException;
import com.echanneling.repository.*;
import com.echanneling.service.DoctorService;
import com.echanneling.service.EmailService;
import com.echanneling.util.CommonResponse;
import com.echanneling.util.MassageUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    DoctorConverter doctorConverter;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    EmailService emailService;

    @Autowired
    DoctorMappingCenterRepository doctorMappingCenterRepository;

    @Autowired
    ChannelCenterRepository centerRepository;

    @Override
    public CommonResponse createDoctor(DoctorDTO doctorDTO) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorDTO.getDoctorId());
        if(doctorOptional.isPresent())
            throw new ResourceFoundException("Doctor is Already Added");

        User user = modelMapper.map(doctorDTO.getUser(),User.class);

        Optional<UserRole> userRole = userRoleRepository.findById(3);

        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole.get());

        user.setUserRole(userRoleSet);

        User exUser =  userRepository.findByUsername(user.getUsername());
        if(exUser!=null)
            throw new ResourceFoundException("User Name Already Exist");

        User newUser = userRepository.save(user);

        Doctor doctor = doctorConverter.dtoTOEntity(doctorDTO);
        doctor.setUser(newUser);
        doctor =doctorRepository.save(doctor);

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setEmailBody("<html>" +
                "<p>Hi "+doctor.getFirstName()+",</p>\n" +
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
        emailRequest.setEmailList(Arrays.asList(doctor.getEmail()));

        emailService.sendEmailWithoutAttachment(emailRequest);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(doctor);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_CREATED.massage()));

        return commonResponse;
    }

    @Override
    public CommonResponse updateDoctor(DoctorDTO doctorDTO) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorDTO.getDoctorId());
        if(!doctorOptional.isPresent())
            throw new ResourceFoundException("Cannot Find Doctor");


        Doctor doctor = doctorRepository.save(doctorConverter.dtoTOEntity(doctorDTO));

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(doctor);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_UPDATED.massage()));

        return commonResponse;
    }

    @Override
    public CommonResponse findDoctorById(int doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if(!doctorOptional.isPresent())
            throw new ResourceFoundException("Cannot Find Doctor");

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(doctorOptional.get());
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_UPDATED.massage()));

        return commonResponse;
    }

    @Override
    public CommonResponse deleteDoctor(int doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if(!doctorOptional.isPresent())
            throw new ResourceFoundException("Cannot Find Doctor");

        doctorRepository.delete(doctorOptional.get());
        return new CommonResponse();
    }

    @Override
    public CommonResponse getAllDoctors() {
        List<Doctor> doctorList = doctorRepository.findAll();

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(doctorList);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.REQUEST_SUCCESSFUL.massage()));

        return commonResponse;
    }

    @Override
    public CommonResponse findDoctorByNic(String nic) {
        Optional<Doctor> doctorOptional = doctorRepository.findByNic( nic);

        CommonResponse commonResponse = new CommonResponse();

        if(!doctorOptional.isPresent()) {
            commonResponse.setPayload(new Doctor());
            commonResponse.setMessages(Arrays.asList("cannot find the doctor for NIC :"+nic));
            commonResponse.setStatus(false);

        }
        else{
            commonResponse.setPayload(doctorOptional.get());
            commonResponse.setStatus(true);
            commonResponse.setMessages(Arrays.asList(MassageUtils.REQUEST_SUCCESSFUL.massage()));
        }

        return commonResponse;
    }

    @Override
    public CommonResponse approveDoctor(int doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if(!doctorOptional.isPresent())
            throw new ResourceFoundException("Cannot Find Doctor");

        Doctor doctor = doctorOptional.get();
        doctor.setApprove(1);
        doctorRepository.save(doctor);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(doctor);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList("Successfully Approved!"));

        return commonResponse;
    }

    @Override
    public CommonResponse rejectDoctor(int doctorId) {
        Optional<Doctor> doctorOptional = doctorRepository.findById(doctorId);
        if(!doctorOptional.isPresent())
            throw new ResourceFoundException("Cannot Find Doctor");

        Doctor doctor = doctorOptional.get();
        doctor.setApprove(2);
        doctorRepository.save(doctor);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(doctor);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList("Successfully Rejected Doctor!"));

        return commonResponse;
    }

    @Override
    public CommonResponse registerForCenter(DoctorMappingDTO doctorMappingDTO) {
        Optional<DoctorMappingCenter> doctorMappingCenterOptional = doctorMappingCenterRepository.findById(doctorMappingDTO.getDocMapId());
        if(doctorMappingCenterOptional.isPresent())
            throw new ResourceFoundException("Already Existed");

        Doctor doctor = doctorRepository.findByUserId(doctorMappingDTO.getUserId());
        if(doctor ==null)
            throw new ResourceFoundException("Cannot Find Any Doctor");

        ChannelCenter center = centerRepository.findById(doctorMappingDTO.getCenterId()).get();
        if(center ==null)
            throw new ResourceFoundException("Cannot Find Any Channel Center");

        DoctorMappingCenter doctorMappingCenter = new DoctorMappingCenter();
        doctorMappingCenter.setDoctor(doctor);
        doctorMappingCenter.setCenter(center);
        doctorMappingCenter.setChanelDay(doctorMappingDTO.getChanelDay());
        doctorMappingCenter.setStartTime(doctorMappingDTO.getStartTime());
        doctorMappingCenter.setEndTime(doctorMappingDTO.getEndTime());

        doctorMappingCenter = doctorMappingCenterRepository.save(doctorMappingCenter);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(doctorMappingCenter);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_CREATED.massage()));

        return commonResponse;
    }

    @Override
    public CommonResponse getAllApprovedDoctors() {
        List<Doctor> doctorList = doctorRepository.getAllActiveDoctors();
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(doctorList);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.REQUEST_SUCCESSFUL.massage()));
        return commonResponse;
    }

}
