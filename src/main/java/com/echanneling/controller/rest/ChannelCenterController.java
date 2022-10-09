package com.echanneling.controller.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.echanneling.dto.ChannelCenterDTO;
import com.echanneling.service.ChannelCenterService;
import com.echanneling.util.CommonResponse;

@RestController
@RequestMapping(value = "center")
public class ChannelCenterController {
	
	@Autowired
	ChannelCenterService centerService;

	@GetMapping("/getAll")
	 public CommonResponse getAllCenters() {
		return centerService.getAllCenters();
	 }
	
	@PostMapping("/save")
    public CommonResponse createCenter(@Valid @RequestBody ChannelCenterDTO centerDTO) {
        return centerService.createChannelCenter(centerDTO);
    }

    @PutMapping("/updateCenter")
    public CommonResponse updateCenter(@Valid @RequestBody ChannelCenterDTO centerDTO) {
        return centerService.updateChannelCenter(centerDTO);
    }

    @GetMapping("/{centerId}")
    public CommonResponse findCenterById(@PathVariable("centerId") int centerId ) {
        return centerService.findCenterById(centerId);
    }

    @DeleteMapping("/{centerId}")
    public CommonResponse deleteCenter(@PathVariable("centerId") int centerId ) {
        return centerService.deleteChannelCenter(centerId);
    }

    @PutMapping("/approve/{centerId}")
    public CommonResponse approveCenter(@PathVariable("centerId") int centerId ) {
        return centerService.approveCenter(centerId);
    }

    @PutMapping("/reject/{centerId}")
    public CommonResponse rejectCenter(@PathVariable("centerId") int centerId ) {
        return centerService.rejectCenter(centerId);
    }

    @GetMapping("/getAllActive")
    public CommonResponse getAllActiveCenters() {
        return centerService.getAllActiveCenters();
    }
}
