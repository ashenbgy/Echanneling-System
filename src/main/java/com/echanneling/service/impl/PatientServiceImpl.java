package com.echanneling.service.impl;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.echanneling.dto.EmailRequest;
import com.echanneling.dto.SearchDTO;
import com.echanneling.entity.*;
import com.echanneling.repository.*;
import com.echanneling.service.EmailService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echanneling.converter.PatientConverter;
import com.echanneling.dto.PatientDTO;
import com.echanneling.exception.ResourceFoundException;
import com.echanneling.service.PatientService;
import com.echanneling.util.CommonResponse;
import com.echanneling.util.MassageUtils;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {
	
	@Autowired
	PatientRepository patientRepository;
	
	@Autowired
	PatientConverter patientConverter;

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
	public CommonResponse createPatient(PatientDTO patientDTO) {
		Optional<Patient> patientOptional = patientRepository.findById(patientDTO.getPatientId());
        if(patientOptional.isPresent())
            throw new ResourceFoundException("");

        User user = modelMapper.map(patientDTO.getUser(),User.class);

        Optional<UserRole> userRole = userRoleRepository.findById(2);

        Set<UserRole> userRoleSet = new HashSet<>();
        userRoleSet.add(userRole.get());

        user.setUserRole(userRoleSet);

        User exUser =  userRepository.findByUsername(user.getUsername());
        if(exUser!=null)
            throw new ResourceFoundException("User Name Already Exist");

        User newUser = userRepository.save(user);

        Patient patient = patientConverter.dtoTOEntity(patientDTO);
        patient.setUser(newUser);
        patient =patientRepository.save(patient);

        EmailRequest emailRequest = new EmailRequest();
        emailRequest.setEmailBody("<html>" +
                "<p>Hi "+patient.getFirstName()+",</p>\n" +
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
        emailRequest.setEmailList(Arrays.asList(patient.getEmail()));

        emailService.sendEmailWithoutAttachment(emailRequest);

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(patient);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_CREATED.massage()));

        return commonResponse;
	}

	@Override
	public CommonResponse getAllPatient() {
		List<Patient> patientList = patientRepository.findAll();

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(patientList);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.REQUEST_SUCCESSFUL.massage()));

        return commonResponse;
	}

	@Override
	public CommonResponse updatePatient(PatientDTO patientDTO) {
		Optional<Patient> patientOptional = patientRepository.findById(patientDTO.getPatientId());
        if(!patientOptional.isPresent())
            throw new ResourceFoundException("");


        Patient patient = patientRepository.save(patientConverter.dtoTOEntity(patientDTO));

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(patient);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_UPDATED.massage()));

        return commonResponse;
	}

	@Override
	public CommonResponse findPatientById(int patientId) {
		Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(!patientOptional.isPresent())
            throw new ResourceFoundException("");

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(patientOptional.get());
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_UPDATED.massage()));

        return commonResponse;
	}

	@Override
	public CommonResponse deletePatient(int patientId) {
		Optional<Patient> patientOptional = patientRepository.findById(patientId);
        if(!patientOptional.isPresent())
            throw new ResourceFoundException("");

        patientRepository.delete(patientOptional.get());
        return new CommonResponse();
	}

	@Override
	public CommonResponse findPatientByNic(String nic) {
		Optional<Patient> patientOptional = patientRepository.findByNic( nic);

        CommonResponse commonResponse = new CommonResponse();

        if(!patientOptional.isPresent()) {
            commonResponse.setPayload(new Patient());
            commonResponse.setMessages(Arrays.asList("cannot find the patient for NIC :"+nic));
            commonResponse.setStatus(false);

        }
        else{
            commonResponse.setPayload(patientOptional.get());
            commonResponse.setStatus(true);
            commonResponse.setMessages(Arrays.asList(MassageUtils.REQUEST_SUCCESSFUL.massage()));
        }

        return commonResponse;
	}

    @Override
    public CommonResponse searchDetails(SearchDTO searchDTO) {
        List<DoctorMappingCenter> doctorMappingCenterList = doctorMappingCenterRepository.findAll();

        if(searchDTO.getDoctorId()>0 && searchDTO.getCenterId()>0 && !searchDTO.getSpecialization().equals("")){
            doctorMappingCenterList.stream().filter(c -> c.getDoctor().getDoctorId() == searchDTO.getDoctorId() && c.getCenter().getCenterId()== searchDTO.getCenterId() && c.getDoctor().getSpecialization().equals(searchDTO.getSpecialization()))
                    .collect(Collectors.toList());
        }
        else if(searchDTO.getDoctorId()>0 && searchDTO.getCenterId()>0 && searchDTO.getSpecialization().equals("")){
            doctorMappingCenterList.stream().filter(c -> c.getDoctor().getDoctorId() == searchDTO.getDoctorId() && c.getCenter().getCenterId()== searchDTO.getCenterId())
                    .collect(Collectors.toList());
        }
        else if(searchDTO.getDoctorId()==0 && searchDTO.getCenterId()>0 && !searchDTO.getSpecialization().equals("")){
            doctorMappingCenterList.stream().filter(c -> c.getCenter().getCenterId()== searchDTO.getCenterId() && c.getDoctor().getSpecialization().equals(searchDTO.getSpecialization()))
                    .collect(Collectors.toList());
        }
        else if(searchDTO.getDoctorId()>0 && searchDTO.getCenterId()==0 && searchDTO.getSpecialization().equals("")){
            doctorMappingCenterList.stream().filter(c -> c.getDoctor().getDoctorId() == searchDTO.getDoctorId()  && c.getDoctor().getSpecialization().equals(searchDTO.getSpecialization()))
                    .collect(Collectors.toList());
        }
        else if(searchDTO.getDoctorId()>0 && searchDTO.getCenterId()==0 && !searchDTO.getSpecialization().equals("")){
            doctorMappingCenterList.stream().filter(c -> c.getDoctor().getDoctorId() == searchDTO.getDoctorId())
                    .collect(Collectors.toList());
        }
        else if(searchDTO.getDoctorId()==0 && searchDTO.getCenterId()>0 && searchDTO.getSpecialization().equals("")){
            doctorMappingCenterList.stream().filter(c -> c.getCenter().getCenterId()== searchDTO.getCenterId())
                    .collect(Collectors.toList());
        }
        else if(searchDTO.getDoctorId()==0 && searchDTO.getCenterId()==0 && !searchDTO.getSpecialization().equals("")){
            doctorMappingCenterList.stream().filter(c -> c.getDoctor().getSpecialization().equals(searchDTO.getSpecialization()))
                    .collect(Collectors.toList());
        }

        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setPayload(doctorMappingCenterList);
        commonResponse.setStatus(true);
        commonResponse.setMessages(Arrays.asList("Success"));

        return commonResponse;
    }

}
