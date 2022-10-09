package com.echanneling.dto;

import java.util.Objects;

import com.echanneling.entity.Stakeholder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChannelCenterDTO extends Stakeholder {
	
	private int centerId;
	private int approve;

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ChannelCenterDTO channelCenterDTO = (ChannelCenterDTO) o;
        return centerId == channelCenterDTO.centerId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), centerId);
    }
    
}
