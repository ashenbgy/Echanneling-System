package com.echanneling.service.impl;

import javax.transaction.Transactional;

import com.echanneling.dto.EmailRequest;
import com.echanneling.entity.Appointment;
import com.echanneling.entity.Doctor;
import com.echanneling.entity.DoctorMappingCenter;
import com.echanneling.exception.ResourceNotFoundException;
import com.echanneling.repository.*;
import com.echanneling.service.EmailService;
import com.echanneling.util.MassageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.echanneling.dto.AppointmentDTO;
import com.echanneling.service.AppointmentService;
import com.echanneling.util.CommonResponse;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
	@Autowired
	DoctorMappingCenterRepository doctorMappingCenterRepository;

	@Autowired
	ChannelCenterRepository centerRepository;

	@Autowired
	DoctorRepository doctorRepository;

	@Autowired
	AppointmentRepository appointmentRepository;

	@Autowired
	PatientRepository patientRepository;

	@Autowired
	EmailService emailService;

	@Override
	public CommonResponse createAppointment(AppointmentDTO appointmentDTO) {
		Appointment appointment = appointmentRepository.findById(appointmentDTO.getAppId()).get();

		if(appointment==null)
			throw new ResourceNotFoundException("Select one Time Slot for Appointment");

		appointment.setComment(appointmentDTO.getComment());
		appointment.setPatient(patientRepository.findById(appointmentDTO.getPatientId()).get());

		appointment = appointmentRepository.save(appointment);

		EmailRequest emailRequest = new EmailRequest();
		emailRequest.setEmailBody("<html>" +
				"<p>Hi "+appointment.getPatient().getFirstName()+",</p>\n" +
				"<p>Welcome to E Channeling.</p>\n" +
				"<p>You are Successfully created a Appointment.</p>\n" +
				"<p>&nbsp;</p>\n" +
				"<p><strong>Doctor Name:<span style=\"color: #ff0000;\">"+appointment.getDoctorMappingCenter().getDoctor().getFirstName()+"</span></strong></p>\n" +
				"<p><strong>Doctor Specialization:<span style=\"color: #ff0000;\">"+appointment.getDoctorMappingCenter().getDoctor().getSpecialization()+"</span></strong></p>\n" +
				"<p><strong>Appointment Start Time:<span style=\"color: #ff0000;\">"+appointment.getStartTime()+"</span></strong></p>\n" +
				"<p><strong>Appointment End Time: <span style=\"color: #ff0000;\">"+appointment.getEndTime()+"</span></strong></p>\n" +
				"<p><strong>Channel Center:<span style=\"color: #ff0000;\">"+appointment.getEndTime()+"</span></strong></p>\n" +
				"<p>&nbsp;</p>\n" +
				"<p><a href=\"http://localhost:8080/echanneling/\">http://localhost:8080/echanneling/</a></p>\n" +
				"<p>&nbsp;</p>\n" +
				"<p>Thank you.</p>"
				+ "</html>");
		emailRequest.setEmailSubject("Appointment Details");
		emailRequest.setEmailList(Arrays.asList(appointment.getPatient().getEmail()));
		emailService.sendEmailWithoutAttachment(emailRequest);

		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setPayload(appointment);
		commonResponse.setStatus(true);
		commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_CREATED.massage()));

		return commonResponse;
	}

	@Override
	public CommonResponse processAppointments() {
		LocalDate date = LocalDate.now();

		LocalDate procesDate=  date.plusDays(6);
		procesDate.getDayOfWeek();

		List<Appointment> appointmentList =new ArrayList<>();
		List<DoctorMappingCenter> dMappingChannelCenters =new ArrayList<>();
		dMappingChannelCenters=doctorMappingCenterRepository.channelcentergetByDate(procesDate.getDayOfWeek().toString());

		for(DoctorMappingCenter doctorMappingChannelCenter:dMappingChannelCenters) {
			int startTime = Integer.parseInt(doctorMappingChannelCenter.getStartTime().substring(0,2).concat(doctorMappingChannelCenter.getStartTime().substring(3,5)));
			int endTime = Integer.parseInt(doctorMappingChannelCenter.getEndTime().substring(0,2).concat(doctorMappingChannelCenter.getEndTime().substring(3,5)));

			int lastTime = (endTime-startTime)%20;

			int periad = (endTime-startTime - lastTime )/20;

			int apoimentTimeStart =startTime;
			int apioimentTimeEnd =startTime;

			for(int x=1;x<=periad;x++) {
				apioimentTimeEnd= convertTime(apoimentTimeStart)+20;
				Appointment patientAppointmentModel = new Appointment();
				patientAppointmentModel.setAppDate(localDateToDate(procesDate));
				if((convertTime(apoimentTimeStart)<1000)) {

					patientAppointmentModel.setStartTime("0"+Integer.toString((convertTime(apoimentTimeStart))));
				}
				else {
					patientAppointmentModel.setStartTime(Integer.toString((convertTime(apoimentTimeStart))));
				}

				if(convertTime(apioimentTimeEnd)<1000) {
					patientAppointmentModel.setEndTime("0"+Integer.toString((convertTime(apioimentTimeEnd))));

				}else {
					patientAppointmentModel.setEndTime(Integer.toString((convertTime(apioimentTimeEnd))));
				}

				patientAppointmentModel.setDoctorMappingCenter(doctorMappingChannelCenter);

				appointmentRepository.save(patientAppointmentModel);
				appointmentList.add(patientAppointmentModel);

				System.out.println("time "+x+" "+convertTime(apoimentTimeStart)+", "+convertTime(apioimentTimeEnd));
				apoimentTimeStart=convertTime(apioimentTimeEnd);
			}
			System.out.println("lasttime" +lastTime);

		}
		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setPayload(appointmentList);
		commonResponse.setStatus(true);
		commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_CREATED.massage()));

		return commonResponse;
	}

	@Override
	public CommonResponse getAllAppointment() {
		List<Appointment> appointmentList = appointmentRepository.findAll();

		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setPayload(appointmentList);
		commonResponse.setStatus(true);
		commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_CREATED.massage()));

		return commonResponse;
	}

	@Override
	public CommonResponse findAppointmentsByDoctorId(int doctorId) {
		List<Appointment> appointmentList = appointmentRepository.findAppointmentsByDoctorId(doctorId);

		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setPayload(appointmentList);
		commonResponse.setStatus(true);
		commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_CREATED.massage()));

		return commonResponse;
	}

	@Override
	public CommonResponse findAppointmentsByCenterId(int centerId) {
		List<Appointment> appointmentList = appointmentRepository.findAppointmentsByCenterId(centerId);

		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setPayload(appointmentList);
		commonResponse.setStatus(true);
		commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_CREATED.massage()));

		return commonResponse;
	}

	@Override
	public CommonResponse findAppointmentsByDoctorMappingCenterId(int docMapId) {
		List<Appointment> appointmentList = appointmentRepository.findAppointmentsByDoctorMappingCenterId(docMapId);

		CommonResponse commonResponse = new CommonResponse();
		commonResponse.setPayload(appointmentList);
		commonResponse.setStatus(true);
		commonResponse.setMessages(Arrays.asList(MassageUtils.SUCCESSFULLY_CREATED.massage()));

		return commonResponse;
	}

	public int convertTime(int time) {
		int hours = time / 100;

		int minutes = (time - hours * 100) % 60;
		if (minutes == 0) hours++;
		if((time - hours * 100 )>60) {
			hours =hours +(time - hours * 100)/60 ;
		}
		return (hours * 100 + minutes) ;

	}
	public Date localDateToDate(LocalDate lDate) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		return (Date) Date.from(lDate.atStartOfDay(defaultZoneId).toInstant());
	}

}
