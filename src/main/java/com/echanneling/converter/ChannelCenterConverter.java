package com.echanneling.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.echanneling.dto.ChannelCenterDTO;
import com.echanneling.entity.ChannelCenter;

@Component
public class ChannelCenterConverter {
	
	@Autowired
    ModelMapper modelMapper;

    public ChannelCenter dtoTOEntity(ChannelCenterDTO channelCenterDTO){
        return  modelMapper.map(channelCenterDTO, ChannelCenter.class);
    }

}
