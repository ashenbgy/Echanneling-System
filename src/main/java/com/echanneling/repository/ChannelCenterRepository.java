package com.echanneling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.echanneling.entity.ChannelCenter;

import java.util.List;

@Repository
public interface ChannelCenterRepository extends JpaRepository<ChannelCenter, Integer> {

    @Query("From ChannelCenter D WHERE D.approve=1")
    List<ChannelCenter> getAllActiveCenters();
}
