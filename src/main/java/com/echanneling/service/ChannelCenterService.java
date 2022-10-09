package com.echanneling.service;

import com.echanneling.dto.ChannelCenterDTO;
import com.echanneling.util.CommonResponse;

public interface ChannelCenterService {
	
	CommonResponse createChannelCenter(ChannelCenterDTO centerDTO);
	
	CommonResponse updateChannelCenter(ChannelCenterDTO centerDTO);
	
	CommonResponse getAllCenters();

	CommonResponse findCenterById(int centerId);

	CommonResponse deleteChannelCenter(int centerId);

    CommonResponse approveCenter(int centerId);

	CommonResponse rejectCenter(int centerId);

    CommonResponse getAllActiveCenters();
}
